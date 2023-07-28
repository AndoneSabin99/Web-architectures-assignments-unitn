import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'name'
})
export class NamePipe implements PipeTransform {

  transform(fullname: string) {
    let result = fullname.split(', ');
    return result[0] + ' ' + result[1];
  }

}
