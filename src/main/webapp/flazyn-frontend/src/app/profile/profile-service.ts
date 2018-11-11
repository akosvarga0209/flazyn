import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs/Observable";
import {Profile} from "./modell/profile";

@Injectable()
export class ProfileService {

  private GET_PROFILE: string = "/getUserProfile";
  private SAVE_PROFILE: string = "/saveUserProfile";

  constructor(private http: HttpClient){}

  public getUserProfile(): Observable<any> {
    return this.http.get(environment.SERVERURL + this.GET_PROFILE);
  }

  public saveUserProfile(profile: Profile): Observable<any> {
    return this.http.post(environment.SERVERURL + this.SAVE_PROFILE, profile);
  }
}
