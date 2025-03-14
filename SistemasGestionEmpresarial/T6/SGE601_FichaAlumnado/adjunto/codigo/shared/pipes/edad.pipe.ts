import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'edad'
})
export class EdadPipe implements PipeTransform {

  transform(fechaNacimiento: string): number {
    if (!fechaNacimiento) return null;
    const birthdate = new Date(fechaNacimiento);
    const today = new Date();
    let edad = today.getFullYear() - birthdate.getFullYear();
    const monthDiff = today.getMonth() - birthdate.getMonth();
    if (monthDiff < 0 || (monthDiff === 0 && today.getDate() < birthdate.getDate())) {
      edad--;
    }
    return edad;
  }
}
