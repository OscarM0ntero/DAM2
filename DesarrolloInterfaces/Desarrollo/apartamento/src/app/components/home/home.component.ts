import { NgImageSliderModule } from 'ng-image-slider';
 
import { Component, ElementRef, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [NgImageSliderModule, CommonModule],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  
  
	imageObject: Array<object> = [
		{ image: '/assets/IMG_01.jpg', thumbImage: '/assets/IMG_01.jpg', alt: 'Imagen 1'},
		{ image: '/assets/IMG_02.jpg', thumbImage: '/assets/IMG_02.jpg', alt: 'Imagen 2'},
		{ image: '/assets/IMG_03.jpg', thumbImage: '/assets/IMG_03.jpg', alt: 'Imagen 3'},
		{ image: '/assets/IMG_04.jpg', thumbImage: '/assets/IMG_04.jpg', alt: 'Imagen 4'},
		{ image: '/assets/IMG_05.jpg', thumbImage: '/assets/IMG_05.jpg', alt: 'Imagen 5'},
		{ image: '/assets/IMG_06.jpg', thumbImage: '/assets/IMG_06.jpg', alt: 'Imagen 6'},
		{ image: '/assets/IMG_07.jpg', thumbImage: '/assets/IMG_07.jpg', alt: 'Imagen 7'},
		{ image: '/assets/IMG_08.jpg', thumbImage: '/assets/IMG_08.jpg', alt: 'Imagen 8'},
		{ image: '/assets/IMG_09.jpg', thumbImage: '/assets/IMG_09.jpg', alt: 'Imagen 9'},
		{ image: '/assets/IMG_10.jpg', thumbImage: '/assets/IMG_10.jpg', alt: 'Imagen 10'},
		{ image: '/assets/IMG_11.jpg', thumbImage: '/assets/IMG_11.jpg', alt: 'Imagen 11'},
		{ image: '/assets/IMG_12.jpg', thumbImage: '/assets/IMG_12.jpg', alt: 'Imagen 12'},
		{ image: '/assets/IMG_13.jpg', thumbImage: '/assets/IMG_13.jpg', alt: 'Imagen 13'},
		{ image: '/assets/IMG_14.jpg', thumbImage: '/assets/IMG_14.jpg', alt: 'Imagen 14'},
		{ image: '/assets/IMG_15.jpg', thumbImage: '/assets/IMG_15.jpg', alt: 'Imagen 15'},
		{ image: '/assets/IMG_16.jpg', thumbImage: '/assets/IMG_16.jpg', alt: 'Imagen 16'},
		{ image: '/assets/IMG_17.jpg', thumbImage: '/assets/IMG_17.jpg', alt: 'Imagen 17'},
		{ image: '/assets/IMG_18.jpg', thumbImage: '/assets/IMG_18.jpg', alt: 'Imagen 18'}
	];
	

  @ViewChild('carousel', { static: false }) carousel!: ElementRef;

  images: string[] = [
    '/assets/IMG_01.jpg', '/assets/IMG_02.jpg', '/assets/IMG_03.jpg',
    '/assets/IMG_04.jpg', '/assets/IMG_05.jpg', '/assets/IMG_06.jpg',
    '/assets/IMG_07.jpg', '/assets/IMG_08.jpg', '/assets/IMG_09.jpg',
    '/assets/IMG_10.jpg', '/assets/IMG_11.jpg', '/assets/IMG_12.jpg',
    '/assets/IMG_13.jpg', '/assets/IMG_14.jpg', '/assets/IMG_15.jpg',
    '/assets/IMG_16.jpg', '/assets/IMG_17.jpg', '/assets/IMG_18.jpg'
  ];

  visibleImages: string[] = [];
  currentIndex = 0;
  translateValue = 0;

  constructor() {
    this.updateVisibleImages();
  }

  updateVisibleImages() {
    this.visibleImages = this.images.slice(this.currentIndex, this.currentIndex + 6);
    
    // Si llega al final, tomar imágenes del inicio para mantener 6 visibles
    if (this.visibleImages.length < 6) {
      this.visibleImages = [
        ...this.visibleImages,
        ...this.images.slice(0, 6 - this.visibleImages.length)
      ];
    }
  }

  nextSlide() {
    this.translateValue -= 100; // Mueve a la izquierda con animación
    setTimeout(() => {
      this.currentIndex = (this.currentIndex + 6) % this.images.length;
      this.updateVisibleImages();
      this.translateValue = 0; // Resetear posición para efecto loop
    }, 500);
  }

  prevSlide() {
    this.translateValue += 100; // Mueve a la derecha con animación
    setTimeout(() => {
      this.currentIndex = (this.currentIndex - 6 + this.images.length) % this.images.length;
      this.updateVisibleImages();
      this.translateValue = 0; // Resetear posición para efecto loop
    }, 500);
  }
}
