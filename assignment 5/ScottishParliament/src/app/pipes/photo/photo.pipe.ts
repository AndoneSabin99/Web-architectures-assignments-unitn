import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'photo'
})
export class PhotoPipe implements PipeTransform {

  transform(value: string, gender: number) {
    if (value === "") {
      let photoURL;
      if (gender === 1) {
        photoURL = "assets/photo_gender1.png";
      } else if (gender === 2) {
        photoURL = "assets/photo_gender2.jpg";
      }
      return photoURL;
    } else {
      return value;
    }
  }
}
