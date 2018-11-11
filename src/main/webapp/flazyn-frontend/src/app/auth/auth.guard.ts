import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { ModalService } from '../shared/modal/modal-service';
import { ModalTemplateData } from '../shared/modal/modal-template-data';
import { LoginComponent } from './components/login/login.component';

@Injectable()
export class AuthGuard implements CanActivate {
  constructor(private router : Router, private modalService: ModalService){}
  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot):  boolean {
      if (JSON.parse(sessionStorage.getItem('user')) != null)
      return true;
      this.openLoginModal();
      return false;
  }

  private openLoginModal() {
      this.modalService.openTopDialog(
        new ModalTemplateData(LoginComponent, "Bejelentkezés")
      );
  }
}
