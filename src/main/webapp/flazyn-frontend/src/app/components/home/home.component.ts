import { Component, OnInit } from '@angular/core';
import {AuthComponent} from "../../auth/components/auth/auth.component";
import {ModalService} from "../../shared/modal/modal-service";
import {TranslateService} from "@ngx-translate/core";
import {ModalTemplateData} from "../../shared/modal/modal-template-data";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  title = 'flazyn-frontend';
  constructor() {
    //ideiglenesen
    //this.translateService.setDefaultLang(this.translateService.getBrowserLang());
  }

  ngOnInit() {}

}
