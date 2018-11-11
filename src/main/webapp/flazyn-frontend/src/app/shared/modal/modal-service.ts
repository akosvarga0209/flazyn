import {BaseModalComponent} from "../../components/base-modal/base-modal.component";
import { Injectable, Type} from "@angular/core";
import {MatDialog, MatDialogRef} from "@angular/material";
import { MatDialogConfig} from "@angular/material/typings/dialog";
import {ModalTemplateData} from "./modal-template-data";

@Injectable()
export class ModalService extends MatDialog {

  private modalRef: MatDialogRef<BaseModalComponent>;

  public openTopDialog<T>(modalTemplateData: ModalTemplateData): MatDialogRef<BaseModalComponent> {
    this.close();

    let config = this.getDefaultConfig(modalTemplateData);
    config.position = { top: "50px"};

   return this.modalRef = this.open(BaseModalComponent, config);
  }

  public getModalRef():MatDialogRef<BaseModalComponent> {
    return this.modalRef;
  }

  public close() {
    console.log(this.modalRef);
    if (this.isActive()) {
      this.modalRef.close();
    }
  }

  private isActive(): boolean {
    return this.modalRef != null && this.modalRef != undefined;
  }

  private getDefaultConfig<T>(modalTemplateData: ModalTemplateData): MatDialogConfig {
    return {
            data: modalTemplateData,
            height: '600px',
            width: '70%'}
  }

}
