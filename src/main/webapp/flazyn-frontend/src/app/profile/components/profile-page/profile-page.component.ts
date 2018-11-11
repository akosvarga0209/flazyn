import {Component, EventEmitter, OnInit} from '@angular/core';
import {Profile} from "../../modell/profile";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ProfileService} from "../../profile-service";
import {NotificationService} from "../../../shared/notification/notification-service";
import {TranslationHelper} from "../../../shared/translation-helper";
import {TranslateService} from "@ngx-translate/core";

@Component({
  selector: 'app-profile-page',
  templateUrl: './profile-page.component.html',
  styleUrls: ['./profile-page.component.css']
})
export class ProfilePageComponent implements OnInit {
  submit: EventEmitter<void>;
  profile: FormGroup;
  imagePreview: any;

  private SAVE_SUCCESS_HEADER: string = "activation.success.title";
  private SAVE_SUCCESS_MSG: string = "profile.form.success.message";

  constructor(private formBuilder:FormBuilder,
              private profileService: ProfileService,
              private notificationService: NotificationService,
              private translateService: TranslateService) {
    this.submit = new EventEmitter<void>();
  }

  ngOnInit() {
    this.getProfile();
    this.buildFormGroup();
  }

  private getProfile() {
    this.profileService.getUserProfile().subscribe((profile) => {
      let prof = Profile.fromJson(profile);
      this.profile.get('global').setValue(prof.getGlobal());
      this.profile.get('flat').setValue(prof.getFlat());
      this.profile.get('community').setValue(prof.getCommunity());
    })
  }

  private buildFormGroup() {
    this.profile = this.formBuilder.group({
      "global": [null, Validators.required],
      "flat": [],
      "community": [],
      "profilePhoto": []
    });
  }

  private onUpload(event: File) {
    let reader: FileReader = new FileReader();

    reader.onload = () => {
      this.imagePreview = reader.result;
    };

    reader.readAsDataURL(event);
  }

  private doSubmit() {
    this.submit.emit();
    if (this.profile.valid && this.profile.dirty) {
      this.profileService.saveUserProfile(this.convertToProfile()).subscribe(res => {
        new TranslationHelper(this.translateService)
          .init([this.SAVE_SUCCESS_HEADER, this.SAVE_SUCCESS_MSG])
          .whenReady(messages =>
            this.notificationService.addSuccessMessage(messages[this.SAVE_SUCCESS_HEADER],
              messages[this.SAVE_SUCCESS_MSG]));
      });
    }
  }

  private convertToProfile():Profile {
    return Profile.fromParts(this.profile.get('global').value,
      this.profile.get('community').value,
      this.profile.get('flat').value)
  }
}


