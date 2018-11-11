import {Routes} from "@angular/router";
import {ActivationComponent} from "./auth/activation/activation.component";
import {HomeComponent} from "./components/home/home.component";
import {ProfilePageComponent} from "./profile/components/profile-page/profile-page.component";
import {FlatAdComponent} from "./advertisement/components/flat-ad/flat-ad.component";

export const routes: Routes = [
  { path: '', component: HomeComponent},
  { path: 'activation/:activation_key', component: ActivationComponent },
  { path: 'profile', component: ProfilePageComponent},
  {path: 'flat-ad', component: FlatAdComponent}
];
