import { Component, OnInit } from '@angular/core';
import { TabNavigationService } from '../../tab-navigation.service';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css']
})
export class AuthComponent implements OnInit {

  selectedTabIndex;

  constructor(private tabNavigationService: TabNavigationService) { }

  ngOnInit() {
    this.tabNavigationService.navigateToLogin.subscribe(() => {
      this.toLogin();
    });
  }

  toLogin() {
    this.selectedTabIndex = 0;
  }

}
