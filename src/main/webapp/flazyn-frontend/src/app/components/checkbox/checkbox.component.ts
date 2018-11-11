import {Component, EventEmitter, forwardRef, Input, OnInit, Output, ViewChild} from '@angular/core';
import {NG_VALUE_ACCESSOR} from "@angular/forms";
import {CustomFormControl} from "../../shared/form/custom-form-control";


@Component({
  selector: 'app-checkbox',
  templateUrl: './checkbox.component.html',
  styleUrls: ['./checkbox.component.css'],
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => CheckboxComponent),
      multi: true
    }
  ]
})
export class CheckboxComponent extends CustomFormControl<boolean> implements OnInit {
  @ViewChild("check") check;
  checked: boolean;
  @Input() disabled = false;
  @Input() label;
  @Output() onClick: EventEmitter<boolean>;

  constructor() {
    super();
    this.checked = false;
    this.onClick = new EventEmitter<boolean>();
  }


  ngOnInit() {
  }

  clicked():void {
    this.checked = !this.checked;
    this.writeValue(this.checked);
    this.onClick.emit(this.checked);

  }

  protected setValue(value: boolean) {
    this.checked = value;
  }
}
