import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {environment} from "../../environments/environment";

@Injectable()
export class NatureService {
  constructor(private http: HttpClient) {}

  private GET_NATURES: string = "/getNatures";

  public getNatures(): Observable<any> {
    return this.http.get(environment.SERVERURL + this.GET_NATURES);
  }
}
