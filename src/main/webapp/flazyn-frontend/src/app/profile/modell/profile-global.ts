import {Gender} from "../components/global-form/global-form.component";

export interface ProfileGlobal{
  roomMateSearch: boolean;
  age: number;
  gender: Gender;
  pet: boolean;
  smoke: boolean;
  priceMin: number;
  priceMax: number;
  location: string;
}
