import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import moment from 'moment';

@Component({
  selector: 'app-booking',
  templateUrl: './booking.component.html',
  styleUrls: ['./booking.component.css']
})
export class BookingComponent implements OnInit {
  currentMonth: moment.Moment;
  nextMonth: moment.Moment;
  bookedDates: Set<string> = new Set();
  checkInDate: string | null = null;
  checkOutDate: string | null = null;

  constructor(private http: HttpClient) {
    this.currentMonth = moment().startOf('month');
    this.nextMonth = moment().add(1, 'month').startOf('month');
  }

  ngOnInit(): void {
    this.fetchCalendarData();
  }

  fetchCalendarData(): void {
    const icalUrl = 'https://ical.booking.com/v1/export?t=afbef337-0326-414c-8e9d-e40858b927d7';
    this.http.get(icalUrl, { responseType: 'text' }).subscribe(
      data => {
        this.parseICalData(data);
      },
      error => {
        console.error('Error fetching iCal data', error);
      }
    );
  }

  parseICalData(icalData: string): void {
    const lines = icalData.split('\n');
    let startDate: string | null = null;
    let endDate: string | null = null;
    
    lines.forEach(line => {
      if (line.startsWith('DTSTART')) {
        startDate = moment(line.split(':')[1]).format('YYYY-MM-DD');
      }
      if (line.startsWith('DTEND')) {
        endDate = moment(line.split(':')[1]).format('YYYY-MM-DD');
      }
      if (startDate && endDate) {
        let date = moment(startDate);
        while (date.isBefore(moment(endDate))) {
          this.bookedDates.add(date.format('YYYY-MM-DD'));
          date.add(1, 'day');
        }
        startDate = null;
        endDate = null;
      }
    });
  }

  isBooked(date: moment.Moment): boolean {
    return this.bookedDates.has(date.format('YYYY-MM-DD'));
  }

  isSelected(date: moment.Moment): boolean {
    const dateString = date.format('YYYY-MM-DD');
    return this.checkInDate === dateString || this.checkOutDate === dateString;
  }

  selectDate(date: moment.Moment): void {
    const dateString = date.format('YYYY-MM-DD');
    if (this.isBooked(date)) return;
    
    if (!this.checkInDate) {
      this.checkInDate = dateString;
    } else if (!this.checkOutDate && moment(dateString).isAfter(this.checkInDate)) {
      this.checkOutDate = dateString;
    } else {
      this.checkInDate = dateString;
      this.checkOutDate = null;
    }
  }

  prevMonth(): void {
    this.currentMonth.subtract(1, 'month');
    this.nextMonth.subtract(1, 'month');
  }

  nextMonthView(): void {
    this.currentMonth.add(1, 'month');
    this.nextMonth.add(1, 'month');
  }

  resetSelection(): void {
    this.checkInDate = null;
    this.checkOutDate = null;
  }
}
