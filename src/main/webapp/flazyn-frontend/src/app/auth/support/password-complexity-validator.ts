import { Validator, AbstractControl } from '@angular/forms';

export function passwordComplexityValidator(control: AbstractControl) {

        let hasNumber = /\d/.test(control.value);
        let hasUpper = /[A-Z]/.test(control.value);
        let hasLower = /[a-z]/.test(control.value);
        const valid = hasNumber && hasUpper && hasLower;

        if (!valid) {

            return  control.value ;
        }
        return null;

}