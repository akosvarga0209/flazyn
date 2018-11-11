import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {NotificationService} from "../../shared/notification/notification-service";
import {TranslateService} from "@ngx-translate/core";
import {TranslationHelper} from "../../shared/translation-helper";
import {OauthService} from "../oauth.service";

export enum ActivationProgress {
  IN_PROGRESS, FAILED, SUCCESS
}

@Component({
  selector: 'app-activation',
  templateUrl: './activation.component.html',
  styleUrls: ['./activation.component.css']
})
export class ActivationComponent implements OnInit {

  ActivationProgress: typeof ActivationProgress = ActivationProgress;

  activationProgress: ActivationProgress;
  private SUCCESS_TITLE: string = "activation.success.title";
  private SUCCESS_MESSAGE: string = "activation.success.message";
  errorMessage: string;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private notificationService: NotificationService,
              private translateService: TranslateService,
              private authService: OauthService) {
  }

  ngOnInit() {
    this.activationProgress = ActivationProgress.IN_PROGRESS;
    this.route.params.subscribe(params => {
      this.authService.activateUser(params['activation_key'])
        .subscribe(res => {
          console.log(res);
          this.getMessages();
        },
          (error => {
            this.errorMessage = error;
            console.log(this.errorMessage);
            this.activationProgress = ActivationProgress.FAILED;
          }))
    });
  }

  private getMessages() {
    this.translateService.get(this.SUCCESS_TITLE).subscribe(m => console.log(m));
    new TranslationHelper(this.translateService)
      .init([this.SUCCESS_TITLE, this.SUCCESS_MESSAGE])
      .whenReady((messages) => {
        this.notificationService.addSuccessMessage(messages[this.SUCCESS_TITLE], messages[this.SUCCESS_MESSAGE]);
        // this.router.navigateByUrl('');
      });

  }

}


