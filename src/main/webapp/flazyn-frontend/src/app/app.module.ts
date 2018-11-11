import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { LoginComponent } from './auth/components/login/login.component';
import { RegisterComponent } from './auth/components/register/register.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import { CookieService } from 'ngx-cookie-service';
import {
  MatChipsModule,
  MatDialog,
  MatDialogModule,
  MatProgressSpinnerModule,
  MatRadioModule,
  MatTabsModule
} from "@angular/material";
import {BaseModalComponent} from "./components/base-modal/base-modal.component";
import {ModalService} from "./shared/modal/modal-service";
import {TranslateLoader, TranslateModule} from "@ngx-translate/core";
import {HttpClient, HttpClientModule, HTTP_INTERCEPTORS} from "@angular/common/http";
import {TranslateHttpLoader} from "@ngx-translate/http-loader";
import { FlatMainSearchComponent } from './flat-search/flat-main-search/flat-main-search.component';
import {LocationService} from "./shared/google/location-service";
import { OauthService } from './auth/oauth.service';
import { AuthInterceptor } from './auth/auth.interceptor';
import { AuthComponent } from './auth/components/auth/auth.component';
import { ErrorInterceptor } from './auth/error.interceptor';
import { ActivationComponent } from './auth/activation/activation.component';
import {RouterModule} from "@angular/router";
import {routes} from "./app.routes";
import { HomeComponent } from './components/home/home.component';
import {GrowlModule} from "primeng/growl";
import {NotificationService} from "./shared/notification/notification-service";
import { ImageButtonComponent } from './components/image-button/image-button.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { CheckboxComponent } from './components/checkbox/checkbox.component';
import {MatSliderModule} from "@angular/material/slider";
import { TabNavigationService } from './auth/tab-navigation.service';
import { UserService } from './auth/user/user.service';
import {ProfilePageComponent} from "./profile/components/profile-page/profile-page.component";
import {GlobalFormComponent} from "./profile/components/global-form/global-form.component";
import {FlatFormComponent} from "./profile/components/flat-form/flat-form.component";
import {CommunityFormComponent} from "./profile/components/community-form/community-form.component";
import {NatureService} from "./profile/nature-service";
import { FileUploadComponent } from './components/file-upload/file-upload.component';
import { FlatAdComponent } from './advertisement/components/flat-ad/flat-ad.component';
import { SocialLoginModule, AuthServiceConfig } from 'angularx-social-login';
import { GoogleLoginProvider, FacebookLoginProvider, LinkedInLoginProvider} from 'angularx-social-login';
import {ProfileService} from "./profile/profile-service";
import { LoggedInComponent } from './auth/components/logged-in/logged-in.component';

export function createTranslateLoader(http: HttpClient) {
  return new TranslateHttpLoader(http, './assets/i18n/', '.json');
}

let config = new AuthServiceConfig([
  {
    id: FacebookLoginProvider.PROVIDER_ID,
    provider: new FacebookLoginProvider("294434444628607")
  }
]);

export function provideConfig() {
  return config;
}

@NgModule({
  declarations: [
    AppComponent,
    BaseModalComponent,
    LoginComponent,
    RegisterComponent,
    FlatMainSearchComponent,
    AuthComponent,
    ActivationComponent,
    HomeComponent,
    ImageButtonComponent,
    ProfilePageComponent,
    HeaderComponent,
    FooterComponent,
    CheckboxComponent,
    GlobalFormComponent,
    FlatFormComponent,
    CommunityFormComponent,
    FileUploadComponent,
    FlatAdComponent,
    LoggedInComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MatDialogModule,
    MatTabsModule,
    FormsModule,
    ReactiveFormsModule,
    GrowlModule,
    HttpClientModule,
    RouterModule.forRoot(routes),
    MatProgressSpinnerModule,
    MatSliderModule,
    MatRadioModule,
    MatChipsModule,
    SocialLoginModule,
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: (createTranslateLoader),
        deps: [HttpClient]
      }
    })
  ],
  providers: [
    ModalService,
    MatDialog,
    OauthService,
    UserService,
    LocationService,
    CookieService,
    {
      provide: AuthServiceConfig,
      useFactory: provideConfig
    },
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },
    NotificationService,
    TabNavigationService,
    NatureService,
    ProfileService
  ],
  entryComponents: [AuthComponent, BaseModalComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }
