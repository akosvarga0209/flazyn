import {
  Component,
  ComponentFactory,
  ComponentFactoryResolver,
  Inject,
  OnInit, Type,
  ViewChild,
  ViewContainerRef
} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {ModalTemplateData} from "../../shared/modal/modal-template-data";


@Component({
  selector: 'app-base-modal',
  templateUrl: './base-modal.component.html',
  styleUrls: ['./base-modal.component.css']
})
export class BaseModalComponent implements OnInit {

  @ViewChild('container', {read: ViewContainerRef})
  private viewContainer: ViewContainerRef;

  title: string;
  private component: any;

  constructor(@Inject(MAT_DIALOG_DATA) private modalTemplateData: ModalTemplateData,
              private componentFactoryResolver: ComponentFactoryResolver,
              private dialogRef: MatDialogRef<BaseModalComponent>) { }

  ngOnInit() {
    this.resolveData();
    this.loadComponent(this.component);
  }

  private loadComponent(component: any) {
    this.viewContainer.clear();
    this.viewContainer.createComponent(this.getComponentFactory(component));
  }

  private getComponentFactory<T>(component: Type<T>): ComponentFactory<T> {
    if (component instanceof Type) {
      return this.componentFactoryResolver.resolveComponentFactory(component);
    } else {
      throw new Error("Nem megfelelő komponens!");
    }
  }

  private resolveData() {
    this.title = this.modalTemplateData.title;
    this.component = this.modalTemplateData.component;
  }

  public close() {
   this.dialogRef.close();
  }



}
