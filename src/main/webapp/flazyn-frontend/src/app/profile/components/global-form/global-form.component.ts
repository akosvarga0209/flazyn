import {Component, EventEmitter, forwardRef, OnInit, ViewChild} from '@angular/core';
import {FormBuilder, NG_VALUE_ACCESSOR, Validators} from "@angular/forms";
import {LocationService} from "../../../shared/google/location-service";
import {numberedTextValidator} from "../../../shared/form/validator/numbered-text-validator";
import {ProfileGlobal} from "../../modell/profile-global";
import {SubForm} from "../../../shared/form/sub-form";


export enum Gender {
  MALE = "MALE", FEMALE = "FEMALE"
}

@Component({
  selector: 'app-global-form',
  templateUrl: './global-form.component.html',
  styleUrls: ['./global-form.component.css'],
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => GlobalFormComponent),
      multi: true
    }
  ]
})
export class GlobalFormComponent extends SubForm<ProfileGlobal> implements OnInit {
  @ViewChild("autocomplete") autocompleteInput;
  Gender: typeof Gender = Gender;

  gender: Gender;

  constructor(private formBuilder: FormBuilder,
              private locationService: LocationService) {
    super();
    this.onSubmit = new EventEmitter<ProfileGlobal>();
  }

  ngOnInit() {
    this.subscribeForProfileSubmit();
    this.buildForm();
    this.locationAutoCompleteSubscribe();
  }

  private buildForm() {
    this.form = this.formBuilder.group({
      roomMateSearch: [],
      age: [null, [numberedTextValidator, Validators.max(100)]],
      gender: [Gender.MALE, Validators.required],
      pet: [],
      smoke: [],
      priceMin: [null, [numberedTextValidator, Validators.min(1)]],
      priceMax: [null, [numberedTextValidator, Validators.min(1)]],
      location: []
    });
  }

  private locationAutoCompleteSubscribe() {
    this.locationService.initAutocomplete(this.autocompleteInput.nativeElement)
      .subscribe((location)  => {
          this.form.get('location').setValue(location.formattedAddres);
        },
        (error) => {
          console.log(error);
        });
  }

  get f() { return this.form.controls; }

  protected setValue(value: ProfileGlobal): void {
    this.form.get('roomMateSearch').setValue(value.roomMateSearch);
    this.form.get('age').setValue(value.age);
    this.form.get('gender').setValue(value.gender);
    this.form.get('pet').setValue(value.pet);
    this.form.get('smoke').setValue(value.smoke);
    this.form.get('priceMin').setValue(value.priceMin);
    this.form.get('priceMax').setValue(value.priceMax);
    this.form.get('location').setValue(value.location);
  }

}
