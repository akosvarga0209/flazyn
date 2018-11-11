import { HttpInterceptor, HttpRequest, HttpHandler, HttpUserEvent, HttpEvent } from "@angular/common/http";
import { Observable } from "rxjs";
import { Injectable } from "@angular/core";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

    constructor() { }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

        if (JSON.parse(sessionStorage.getItem('user')) != null) {
            req = req.clone({
                headers: req.headers.set("Authorization", "Bearer " + JSON.parse(sessionStorage.getItem('user')).access_token)
            });
        }

      return next.handle(req);
    }
}
