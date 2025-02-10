import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { CalendarOptions, EventInput } from '@fullcalendar/core';
import iCalendar from 'ical.js';
import dayGridPlugin from '@fullcalendar/daygrid';
import { FullCalendarModule } from '@fullcalendar/angular';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-booking2',
  standalone: true,
  imports: [CommonModule, FullCalendarModule, HttpClientModule], // <-- Agregar HttpClientModule
  templateUrl: './booking2.component.html',
  styleUrls: ['./booking2.component.css']
})
export class Booking2Component implements OnInit {
  calendarOptions: CalendarOptions = {
    plugins: [dayGridPlugin],
    initialView: 'dayGridMonth',
    views: {
      multiMonth: {
        type: 'multiMonthYear',
        duration: { months: 2 }
      }
    },
    headerToolbar: {
      left: 'prev',
      center: 'title',
      right: 'next'
    },
    events: [] as EventInput[],
    dayCellDidMount: this.highlightUnavailableDays.bind(this)
  };

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.loadICalData();
  }

  loadICalData() {
	const icalUrl = 'http://localhost:3000/api/ical'; // URL del backend intermedio
  
	this.http.get(icalUrl, { responseType: 'text' }).subscribe(data => {
	  
	  try {
		const jcalData = iCalendar.parse(data);
		const comp = new iCalendar.Component(jcalData);
		const vevents = comp.getAllSubcomponents('vevent');
		let busyDays: string[] = [];
  
		vevents.forEach((event: any) => {
		  const startRaw = event.getFirstPropertyValue('dtstart');
		  const endRaw = event.getFirstPropertyValue('dtend');
  
		  if (!startRaw || !endRaw) return;
  
		  // Convertir fechas del formato YYYYMMDD a YYYY-MM-DD
		  const startDateStr = startRaw.toString().replace(/(\d{4})(\d{2})(\d{2})/, '$1-$2-$3');
		  const endDateStr = endRaw.toString().replace(/(\d{4})(\d{2})(\d{2})/, '$1-$2-$3');
  
		  const startDate = new Date(startDateStr);
		  const endDate = new Date(endDateStr);
  
  
		  let currentDate = new Date(startDate);
		  while (currentDate < endDate) {
			busyDays.push(currentDate.toISOString().split('T')[0]);
			currentDate.setDate(currentDate.getDate() + 1);
		  }
		});
    
		this.calendarOptions = {
		  ...this.calendarOptions,
		  events: busyDays.map(date => ({
			title: 'No disponible',
			start: date,
			display: 'background',
			color: 'red'
		  }))
		};
	  } catch (error) {
	  }
	});
  }
  
  

  highlightUnavailableDays(info: any) {
    if (Array.isArray(this.calendarOptions.events)) {
      const isReserved = (this.calendarOptions.events as any[]).some(
        (event: any) => event.start === info.dateStr
      );
      if (isReserved) {
        info.el.classList.add("fc-day-reserved");
      }
    }
  }
}