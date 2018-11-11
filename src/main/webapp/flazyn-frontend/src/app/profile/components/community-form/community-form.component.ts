import {Component, forwardRef, OnInit, Predicate} from '@angular/core';
import {FormBuilder, NG_VALUE_ACCESSOR} from "@angular/forms";
import {numberedTextValidator} from "../../../shared/form/validator/numbered-text-validator";
import {ProfileCommunity} from "../../modell/profile-community";
import {SubForm} from "../../../shared/form/sub-form";
import {NatureService} from "../../nature-service";
import {Nature} from "../../modell/nature";

@Component({
  selector: 'app-community-form',
  templateUrl: './community-form.component.html',
  styleUrls: ['./community-form.component.css'],
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => CommunityFormComponent),
      multi: true
    }
  ]
})
export class CommunityFormComponent extends SubForm<ProfileCommunity> implements OnInit {

  natures: Nature[];
  selectedNatures: Nature[];

  constructor(private formBuilder: FormBuilder,
              private natureService: NatureService) {
    super();
    this.selectedNatures = [];
  }
  ngOnInit() {
    this.getNatures();
    this.subscribeForProfileSubmit();
    this.buildForm();
  }

  private getNatures() {
    this.natureService.getNatures().subscribe((natures) =>  {
      this.natures = natures;
    });
  }

  private buildForm() {
    this.form = this.formBuilder.group({
      personality: [],
      sharedRoom: [null, numberedTextValidator],
      maxNumberOfRoomMates: [null, numberedTextValidator]
    });
  }

  private getSelectedNature(nature: Nature) {
    let alreadySelected = this.selectedNatures.find(selectedNature => selectedNature.id === nature.id);

    if(!alreadySelected) {
      this.selectedNatures.push(nature);
      this.form.get('personality').setValue(this.selectedNatures);
    }
  }

  private removeNature(nature: Nature) {
    this.selectedNatures = this.selectedNatures.filter(selectedNature => selectedNature.id !== nature.id);
    this.form.get('personality').setValue(this.selectedNatures);
  }

  protected setValue(value: ProfileCommunity): void {
    this.selectedNatures = value.personality;

    this.form.get('personality').setValue(this.selectedNatures);
    this.form.get('sharedRoom').setValue(value.sharedRoom);
    this.form.get('maxNumberOfRoomMates').setValue(value.maxNumberOfRoomMates);
  }

}
