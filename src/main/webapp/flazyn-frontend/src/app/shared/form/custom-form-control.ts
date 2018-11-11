export abstract class CustomFormControl<T> {
  protected disabled: boolean;
  protected controlValue: T;

  get value(): T {
    return this.controlValue;
  }

  protected abstract setValue(value: T): void;

  protected onChange = (formValue: T) => {};

  protected onTouched = () => {};

  writeValue(value: T): void {
    if (value === null) {
      return;
    }
    
    this.onChange(value);
    this.setValue(value);
  }

  registerOnChange(fn: (formValue:T) => void): void {
    this.onChange= fn;
  }

  registerOnTouched(fn: () => void): void {
    this.onTouched = fn;
  }

  setDisabledState(isDisabled: boolean): void {
    this.disabled = isDisabled;
  }
}
