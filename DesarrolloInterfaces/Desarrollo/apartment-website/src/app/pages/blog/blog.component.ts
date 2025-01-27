import { Component } from '@angular/core';

@Component({
  selector: 'app-blog',
  templateUrl: './blog.component.html',
  styleUrls: ['./blog.component.scss']
})
export class BlogComponent {
  posts = [
    {
      title: 'Fiestas locales de este mes',
      content: 'Descubre las festividades más populares de la región.'
    },
    {
      title: '5 lugares imprescindibles',
      content: 'Te recomendamos el casco histórico, la playa y más.'
    }
  ];
}
