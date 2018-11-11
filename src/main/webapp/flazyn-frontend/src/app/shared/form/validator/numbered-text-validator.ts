import {AbstractControl} from "@angular/forms";

export function numberedTextValidator(control: AbstractControl) {
  if(isNaN(control.value) || (control.value === undefined || control.value === null) || control.value === "") {
    return {'numberedTextValidator': ''};
  } else {
    return null;
  }

}
