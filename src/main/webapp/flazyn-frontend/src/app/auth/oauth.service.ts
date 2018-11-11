import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams, HttpErrorResponse } from '@angular/common/http';
import { environment } from '../../environments/environment.prod';
import { User } from './user/user';
import { Observable } from 'rxjs/Rx';
import { map, catchError } from 'rxjs/operators';
import 'rxjs/add/observable/throw';
import { LoginRequest } from './components/login/LoginRequest';
import { CookieService } from 'ngx-cookie-service';
import { UserService } from './user/user.service';

@Injectable()
export class OauthService {
    private _authenticated = false;
    private ACTIVATION_URL: string = '/activation/';
    private RESEND_ACTIVATION_URL: string = '/resetActivation/';
    private FACEBOOK_LOGIN_URL: string = '/fbLogin/'
    private readonly rootUrl = 'http://localhost:8080';

    private user: User;

    private readonly headers = new HttpHeaders({
        'Content-Type': 'application/x-www-form-urlencoded',
        'Authorization': 'Basic ZGV2Z2xhbi1jbGllbnQ6cGFzc3dvcmQ='
    });

    constructor(private http: HttpClient, private cookie: CookieService, private userService: UserService) {
    }

    signIn(loginRequest: LoginRequest): Observable<any> {

        const body = 'grant_type=password&username=' + loginRequest.email + '&password=' + loginRequest.password;
        return this.http.post(this.rootUrl + '/oauth/token', body, { headers: this.headers });
    }

    signInRefreshToken(): Observable<any> {
        const body = 'grant_type=refresh_token&refresh_token=' + this.cookie.get('refreshtoken');
        return this.http.post(this.rootUrl + '/oauth/token', body, { headers: this.headers });
    }

    logout() {
        this.authenticated = false;
        sessionStorage.clear();
        this.cookie.delete('refreshtoken');
    }

    registration(registrationForm) {
        const user = {
            email: registrationForm.email,
            password: registrationForm.password,
            fullName: registrationForm.fullName
        };
        return this.http.post<boolean>(environment.SERVERURL + '/register', user);
    }

    public clearTokensAndUser() {
        this.cookie.delete('refreshtoken');
        sessionStorage.clear();
    }

    public saveUserAndTokens(tokens: any) {
        if (tokens) {
            this.user = new User();
            this.user.access_token = tokens.access_token;
            this.clearTokensAndUser();
            sessionStorage.setItem('user', JSON.stringify(this.user));
            this.cookie.set('refreshtoken', tokens.refresh_token);
            this.userService.getUserObject().subscribe((response) => {
                this.user = this.user.setWithoutToken(response);
                sessionStorage.setItem('user', JSON.stringify(this.user));
                this.authenticated = true;
            });
        }
    }

    public activateUser(activationCode: string): Observable<any> {
        let param = new HttpParams().set('code', activationCode);
        return this.http.get(environment.SERVERURL + this.ACTIVATION_URL, { params: param });
    }

    public resendActivationEmail(email: string): Observable<any> {
        const param = new HttpParams().set('email', email);
        return this.http.get(environment.SERVERURL + this.RESEND_ACTIVATION_URL, { params: param });
    }

    public facebookLogin(token: string) {
        return this.http.get(environment.SERVERURL + this.FACEBOOK_LOGIN_URL + "?fbToken=" + token);
    }

    get authenticated(): boolean {
        return this._authenticated;
    }

    set authenticated(authenticated: boolean) {
        this._authenticated = authenticated;
    }
}
