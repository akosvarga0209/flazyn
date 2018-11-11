import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { OauthService } from '../../oauth.service';
import { ModalService } from '../../../shared/modal/modal-service';
import { passwordComplexityValidator } from '../../support/password-complexity-validator';
import { NotificationService } from '../../../shared/notification/notification-service';
import { TabNavigationService } from '../../tab-navigation.service';
import { MessageObject } from '../../../shared/notification/message-object';
import { NotificationType } from '../../../shared/notification/notification-type';
import { TranslateService } from '@ngx-translate/core';
import {TranslationHelper} from "../../../shared/translation-helper";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  private REGISTER_TITLE: string = 'auth.register_title';
  private THANK_YOU_FOR_REGISTERING: string = 'auth.registration_form.thank_you_for_registering';
  registerTitle;

  registrationForm: FormGroup;
  passLength = 6;

  alertMessage: string;
  openAlert = false;

  successfulRegistrationMessage: MessageObject;

  constructor(private notificationService: NotificationService,
    private tabNavigationService: TabNavigationService,
    private formBuilder: FormBuilder,
    private oauthService: OauthService,
    private modalService: ModalService,
    private translateService: TranslateService) { }

  ngOnInit() {
    this.registrationForm = this.formBuilder.group({
      fullName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(this.passLength), passwordComplexityValidator]],
      retypePassword: ['', [Validators.required]]
    });
    this.getMessages();
  }

  onSubmit() {
    this.openAlert = false;
    if (!this.registrationForm.invalid && this.f.password.value === this.f.retypePassword.value) {
      this.oauthService.registration(this.registrationForm.value).subscribe((van) => {
        this.closeModal();
        this.notificationService.addMessage(this.successfulRegistrationMessage);
      }, (error) => {
        if (error.status == 400) {
          this.alertMessage = error.error.message;
          this.openAlert = true;
        }
      });
    } else {
      Object.keys(this.registrationForm.controls).forEach((control) => {
        this.registrationForm.get(control).markAsTouched();
      });
    }
  }

  navigateToLogin() {
    this.openAlert = false;
    this.tabNavigationService.navigateToLogin.emit(this.registrationForm.get('email').value);
  }

  closeModal() {
    this.modalService.close();
  }

  private getMessages() {
    new TranslationHelper(this.translateService)
      .init([this.REGISTER_TITLE, this.THANK_YOU_FOR_REGISTERING])
      .whenReady((messages) => {
        this.registerTitle = messages[this.REGISTER_TITLE];
        this.successfulRegistrationMessage = new MessageObject(NotificationType.SUCCESS,
          messages[this.THANK_YOU_FOR_REGISTERING],
          null,
          'successfulRegistrationMessage',
          'successfulRegistrationMessage',
          4000, false, false, null);
      });
  }

  get f() { return this.registrationForm.controls; }

}
