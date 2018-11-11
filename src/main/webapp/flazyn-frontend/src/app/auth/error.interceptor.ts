import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { OauthService } from './oauth.service';
import { CookieService } from 'ngx-cookie-service';
import { ModalService } from '../shared/modal/modal-service';
import { TranslateService } from '@ngx-translate/core';
import { ModalTemplateData } from '../shared/modal/modal-template-data';
import { AuthComponent } from './components/auth/auth.component';

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {
    private LOGIN_TITLE = 'login.title';

    constructor(private oauthService: OauthService,
        private cookie: CookieService,
        private modalService: ModalService,
        private translateService: TranslateService
    ) {

    }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        return next.handle(req).pipe(catchError(err => {
            if (err.status === 401) {
                if (this.cookie.get('refreshtoken')) {
                    this.oauthService.signInRefreshToken().subscribe((response) => {
                        this.oauthService.saveUserAndTokens(response);
                    }, (error) => {
                        this.oauthService.clearTokensAndUser();
                        this.openLogin();
                    });
                } else {
                    this.openLogin();
                }
            }
            console.log(err);
            return throwError(err);
        }));
    }

    openLogin() {
        this.modalService.openTopDialog(
            new ModalTemplateData(AuthComponent, this.translateService.instant(this.LOGIN_TITLE))
        );
    }
}
