import {Component, OnInit} from '@angular/core';
import {TranslateService} from "@ngx-translate/core";
import {NotificationService} from "./shared/notification/notification-service";
import {Message} from "primeng/api";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  messages: Message[];

  constructor(private notificationService: NotificationService,
              private translateService: TranslateService) {
    this.translateService.setDefaultLang("en");
  }

  ngOnInit(): void {
    this.messages = [];
    this.notificationSubscription();
  }

  private notificationSubscription() {
    this.notificationService.message().subscribe(message => {
      this.messages.push(message);
    })
  }



}
