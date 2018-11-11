import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Rx';
import { map, catchError } from 'rxjs/operators';
import 'rxjs/add/observable/throw';
import { User } from './user';
import { environment } from '../../../environments/environment';

@Injectable()
export class UserService {

    constructor(private http: HttpClient) {
    }

    getUserObject(): Observable<User> {
        return this.http.get(environment.SERVERURL + '/getUserObject')
            .pipe(map(response => new User(response)));
    }

}
