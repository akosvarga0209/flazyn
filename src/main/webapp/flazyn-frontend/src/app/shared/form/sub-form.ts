
import {EventEmitter, Input, Output} from "@angular/core";
import {FormGroup} from "@angular/forms";
import {CustomFormControl} from "./custom-form-control";

export abstract class SubForm<T> extends CustomFormControl<T> {
  @Output() onSubmit: EventEmitter<T>;
  @Input() mainFormSubmitted: EventEmitter<void>;
  form: FormGroup;
  disabled: boolean;

  protected constructor() {
    super();
    this.onSubmit = new EventEmitter<T>();
  }

  protected subscribeForProfileSubmit() {
    this.mainFormSubmitted.subscribe(() => {
      this.submit();
    });
  }

  protected submit() {
    if (this.form.valid) {
      this.onSubmit.emit(this.form.value);
      this.writeValue(this.form.value);
    } else {
      this.onSubmit.emit(null);
      this.writeValue(this.form.value);
    }
  }


}
