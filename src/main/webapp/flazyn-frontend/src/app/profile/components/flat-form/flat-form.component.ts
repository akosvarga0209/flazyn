import {Component, forwardRef, OnInit} from '@angular/core';
import {FormBuilder, NG_VALUE_ACCESSOR} from "@angular/forms";
import {SubForm} from "../../../shared/form/sub-form";
import {ProfileFlat} from "../../modell/profile-flat";

@Component({
  selector: 'app-flat-form',
  templateUrl: './flat-form.component.html',
  styleUrls: ['./flat-form.component.css'],
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => FlatFormComponent),
      multi: true
    }
  ]
})
export class FlatFormComponent extends SubForm<ProfileFlat> implements OnInit {

  constructor(private formBuilder: FormBuilder) {
    super();
  }
  ngOnInit() {
    this.subscribeForProfileSubmit();
    this.buildForm();
  }

  private buildForm() {
    this.form = this.formBuilder.group({
      furnished: []
    });
  }

  protected setValue(value: ProfileFlat): void {
    this.form.get('furnished').setValue(value.furnished);
  }

}
