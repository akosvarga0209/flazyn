import { Component, OnInit } from '@angular/core';
import {AuthComponent} from "../../auth/components/auth/auth.component";
import {ModalTemplateData} from "../../shared/modal/modal-template-data";
import {TranslateService} from "@ngx-translate/core";
import {ModalService} from "../../shared/modal/modal-service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  private LOGIN_TITLE = "login.title";

  constructor(private modalService:ModalService,
              private translateService: TranslateService) { }

  ngOnInit() {
  }

  private openLoginModal() {
    this.modalService.openTopDialog(
      new ModalTemplateData(AuthComponent, this.translateService.instant(this.LOGIN_TITLE))
    );
  }

}
