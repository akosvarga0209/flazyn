import {Component, OnInit, ViewChild} from '@angular/core';
import {LocationService} from "../../shared/google/location-service";
import {LocationObject} from "../../shared/google/modell/location";

@Component({
  selector: 'app-flat-main-search',
  templateUrl: './flat-main-search.component.html',
  styleUrls: ['./flat-main-search.component.css']
})
export class FlatMainSearchComponent implements OnInit {

  @ViewChild("autocomplete") autocompleteInput;
  private location: LocationObject;

  constructor(private locationService: LocationService) { }

  ngOnInit() {
    this.locationAutoCompleteSubscribe();
  }

  private locationAutoCompleteSubscribe() {
    this.locationService.initAutocomplete(this.autocompleteInput.nativeElement)
      .subscribe((location)  => {
          this.location = location;
          console.log(this.location);
        },
        (error) => {
          console.log(error);
        });
  }

}
