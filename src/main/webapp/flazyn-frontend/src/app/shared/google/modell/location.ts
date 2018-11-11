import PlaceGeometry = google.maps.places.PlaceGeometry;

export class LocationObject {
  formattedAddres: string;
  geometry: PlaceGeometry;

  constructor(formattedAddress: string,
              geometry: PlaceGeometry){
    this.formattedAddres = formattedAddress;
    this.geometry = geometry;
  }
}
