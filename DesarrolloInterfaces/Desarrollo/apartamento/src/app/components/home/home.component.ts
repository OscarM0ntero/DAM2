 
import { Component, ElementRef, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
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
