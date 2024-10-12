/** An actor's name can't match the actor's role */

import { AbstractControl, ValidationErrors, ValidatorFn } from "@angular/forms";

export const samePasswordValidator: ValidatorFn = (
    control: AbstractControl,
  ): ValidationErrors | null => {
    const password = control.get('password');
    const confirm = control.get('confirmPassword');
    return password && confirm && password.value === confirm.value ? null :{arePwdEqual: true};
  };