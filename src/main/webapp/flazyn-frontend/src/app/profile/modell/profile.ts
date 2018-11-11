import {Gender} from "../components/global-form/global-form.component";
import {ProfileGlobal} from "./profile-global";
import {ProfileCommunity} from "./profile-community";
import {ProfileFlat} from "./profile-flat";
import {Nature} from "./nature";

export class Profile {
  roomMateSearch: boolean;
  age: number;
  gender: Gender;
  pet: boolean;
  smoke: boolean;
  priceMin: number;
  priceMax: number;
  location: string;
  nature: Nature[];
  sharedRoom: boolean;
  maxNumberOfRoomMates: number;
  furnished: boolean;

  public static fromParts(global: ProfileGlobal, community:ProfileCommunity, flat:ProfileFlat): Profile {
    let profile = new Profile();
    profile.roomMateSearch = global.roomMateSearch;
    profile.age = global.age;
    profile.gender = global.gender;
    profile.pet = global.pet;
    profile.smoke = global.smoke;
    profile.priceMin = global.priceMin;
    profile.priceMax = global.priceMax;
    profile.location = global.location;
    profile.nature = community.personality;
    profile.sharedRoom = community.sharedRoom;
    profile.maxNumberOfRoomMates = community.maxNumberOfRoomMates;
    profile.furnished = flat.furnished;
    return profile;
  }

  public static fromJson(json: any): Profile {
    let profile = new Profile();
    profile.roomMateSearch = json.roomMateSearch;
    profile.age = json.age;
    profile.gender = json.gender;
    profile.pet = json.pet;
    profile.smoke = json.smoke;
    profile.priceMin = json.priceMin;
    profile.priceMax = json.priceMax;
    profile.location = json.location;
    profile.nature = json.nature;
    profile.sharedRoom = json.sharedRoom;
    profile.maxNumberOfRoomMates = json.maxNumberOfRoomMates;
    profile.furnished = json.furnished;
    return profile;
  }

  public getGlobal(): ProfileGlobal {
    return {
      "roomMateSearch": this.roomMateSearch,
      "age": this.age,
      "gender": this.gender,
      "pet": this.pet,
      "smoke": this.smoke,
      "priceMin": this.priceMin,
      "priceMax": this.priceMax,
      "location": this.location
    };
  }

  public getFlat(): ProfileFlat {
    return {
      "furnished": this.furnished
    };
  }

  public getCommunity(): ProfileCommunity {
    return {
      "personality": this.nature,
      "sharedRoom": this.sharedRoom,
      "maxNumberOfRoomMates": this.maxNumberOfRoomMates
    };
  }

}
