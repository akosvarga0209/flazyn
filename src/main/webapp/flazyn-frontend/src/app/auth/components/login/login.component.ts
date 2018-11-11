import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, EmailValidator } from '@angular/forms';
import { OauthService } from '../../oauth.service';
import { ModalService } from '../../../shared/modal/modal-service';
import { User } from '../../user/user';
import { TabNavigationService } from '../../tab-navigation.service';
import { UserService } from '../../user/user.service';
import { TranslateService } from '@ngx-translate/core';
import { NotificationService } from '../../../shared/notification/notification-service';
import { MessageObject } from '../../../shared/notification/message-object';
import { NotificationType } from '../../../shared/notification/notification-type';
import { LoginRequest } from './LoginRequest';
declare var grecaptcha: any;
import { AuthService, FacebookLoginProvider } from "angularx-social-login";
import { TranslationHelper } from "../../../shared/translation-helper";
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  private LOGIN = 'auth.sign_in_form.login';
  private ACTIVATION_LINK_RESENT = 'auth.sign_in_form.activation_link_resent';
  private EMAIL_NOT_ACTIVATED = 'auth.sign_in_form.email_not_activated';
  private BAD_CREDITENTIALS = 'auth.sign_in_form.bad_creditentials';
  private BLOCKED = 'auth.sign_in_form.blocked';

  activationLinkResentNoti: MessageObject;
  loginButton: string;

  loginForm: FormGroup;
  loading = false;
  alertObject = { open: false, link: false, message: '' };
  cachedEmail4Activation: string;
  user: User;
  badCreditentialsAppeared = false;
  captchaError = false;

  constructor(
    private notificationService: NotificationService,
    private tabNavigationService: TabNavigationService,
    private formBuilder: FormBuilder,
    private oauthService: OauthService,
    private userService: UserService,
    private modalService: ModalService,
    private translateService: TranslateService,
    private authService: AuthService,
    private cookie: CookieService
  ) { }

  ngOnInit() {

    this.tabNavigationService.navigateToLogin.subscribe((email) => {
      this.loginForm.get('email').setValue(email);
    });

    this.loginForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
      rememberMe: []
    });
    this.getMessages();
  }

  onSubmit() {
    if (!this.loginForm.invalid) {
      this.alertObject.open = false;
      this.loading = true;

      const loginRequest = new LoginRequest({ email: this.loginForm.value.email, password: this.loginForm.value.password });

      // if (this.badCreditentialsAppeared) {
      //   const response = grecaptcha.getResponse();
      //   if (response.length === 0) {
      //     this.captchaError = true;
      //     return;
      //   }
      //   loginRequest.captchaResponse = response;
      // }

      sessionStorage.clear();
      this.cookie.delete('refreshtoken');
      this.oauthService.signIn(loginRequest).subscribe(response => {
        this.user = new User();
        this.user.access_token = response.access_token;
        if (this.loginForm.get('rememberMe').value) {
          this.cookie.set('refreshtoken', response.refresh_token);
        }
        sessionStorage.setItem('user', JSON.stringify(this.user));
        this.getAndSaveUser();
      }, (error) => {
        this.loading = false;
        switch (error.status) {
          case 400:
            switch (error.error.error_description) {
              case 'Bad credentials':
                this.badCreditentialsAppeared = true;
                this.translateService.get(this.BAD_CREDITENTIALS).subscribe(m => this.alertObject = { open: true, link: false, message: m });
                break;
              case 'User is disabled':
                this.translateService.get(this.EMAIL_NOT_ACTIVATED).subscribe(m => this.alertObject = { open: true, link: true, message: m });
                this.cachedEmail4Activation = this.loginForm.get('email').value;
                break;
            }
            break;
          case 401:
            if (error.error.error_description == 'blocked') {
              this.translateService.get(this.BLOCKED).subscribe(m => this.alertObject = { open: true, link: false, message: m });
            }
            break;
        }

        this.loginForm.get('password').setValue('');
        console.log(error);
      });
    } else {
      Object.keys(this.loginForm.controls).forEach((control) => {
        this.loginForm.get(control).markAsTouched();
      });
    }
  }

  getAndSaveUser() {
    this.userService.getUserObject().subscribe((response) => {
      this.user = this.user.setWithoutToken(response);
      sessionStorage.setItem('user', JSON.stringify(this.user));
      this.oauthService.authenticated = true;
      this.closeModal();
      this.loading = false;
    });
  }

  resendActivationEmail() {
    this.oauthService.resendActivationEmail(this.cachedEmail4Activation).subscribe((response) => {
      this.closeModal();
      this.notificationService.addMessage(this.activationLinkResentNoti);
    }, (error) => {
    });
  }

  signInWithFB(): void {
    this.authService.signIn(FacebookLoginProvider.PROVIDER_ID).then((userdata) => {
      this.oauthService.facebookLogin(userdata.authToken).subscribe((response) => {
        console.log(response);
        //TODO
      });
    });
  }


kek() {
  this.getAndSaveUser();
}

  closeModal() {
    this.modalService.close();
  }

  private getMessages() {
    new TranslationHelper(this.translateService)
      .init([this.LOGIN, this.ACTIVATION_LINK_RESENT])
      .whenReady((messages) => {
        this.loginButton = messages[this.LOGIN];
        this.activationLinkResentNoti = new MessageObject(NotificationType.SUCCESS,
          messages[this.ACTIVATION_LINK_RESENT],
          null,
          'activationLinkResentNoti',
          'activationLinkResentNoti',
          4000, false, false, null);
      });
  }

  //form control helper method
  get f() { return this.loginForm.controls; }

}
