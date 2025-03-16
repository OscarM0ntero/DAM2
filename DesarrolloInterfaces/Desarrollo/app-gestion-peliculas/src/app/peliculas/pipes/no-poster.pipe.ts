import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
    name: 'noPoster'
})
export class NoPosterPipe implements PipeTransform {

    transform(value: string | null | undefined): string {
        return value 
            ? `https://image.tmdb.org/t/p/w600_and_h900_bestv2${value}`
            : 'assets/no-poster.png';
    }

}
