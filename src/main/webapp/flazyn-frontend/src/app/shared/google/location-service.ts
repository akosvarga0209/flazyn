import {Injectable} from "@angular/core";
import {LocationObject} from "./modell/location";
import {Observable} from "rxjs/internal/Observable";
import {Observer} from "rxjs/internal/types";
import {LocationError} from "./modell/location-error";


@Injectable()
export class LocationService{

  public initAutocomplete(inputElement: HTMLInputElement): Observable<LocationObject> {
     let autocomplete = new google.maps.places.Autocomplete(inputElement);
     return Observable.create((observer: Observer<LocationObject>) => {
       autocomplete.addListener('place_changed', function() {
         let place = autocomplete.getPlace();
         //TODO mikor lehet még error
         if (!place.geometry) {
           observer.error(LocationError.PLACE_NOT_FOUND);
         }
         observer.next(new LocationObject(place.formatted_address, place.geometry));
       });
    });
  }
}
