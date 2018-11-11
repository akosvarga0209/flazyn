import {Component, EventEmitter, forwardRef, Input, OnInit, Output, ViewChild} from '@angular/core';
import {CustomFormControl} from "../../shared/form/custom-form-control";
import {NG_VALUE_ACCESSOR} from "@angular/forms";

@Component({
  selector: 'app-file-upload',
  templateUrl: './file-upload.component.html',
  styleUrls: ['./file-upload.component.css'],
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => FileUploadComponent),
      multi: true
    }
  ]
})
export class FileUploadComponent extends CustomFormControl<File[] | File> implements OnInit {

  @Input() btnCaption: string;
  @Input() multiUpload: boolean;
  @Input() accept: string;

  @Output() getFiles: EventEmitter<File | File[]>;

  @ViewChild('input') i;
  files: File[];
  file: File;
  constructor() {
    super();
    this.files = [];
    this.getFiles = new EventEmitter<File|File[]>();
  }

  ngOnInit() {
    this.getAccept();
  }

  private onUpload() {
    this.getFile();
    this.getFiles.emit(this.getValue());
    this.writeValue(this.getValue());
  }

  private getFile() {
    let file: File = this.i.nativeElement.files[0];

    if(file !== undefined && file !== null) {
      this.addFile(file);
    }
  }

  private addFile(file: File) {
    if (this.multiUpload) {
      this.files.push(file);
    } else {
      this.file = file;
    }
  }

  private getAccept() {
    if (this.accept == undefined || this.accept === null) {
      this.accept = "*";
    }
  }

  protected getValue(): File | File[] {

    if (this.multiUpload) {
      return this.files;
    } else {
      return this.file;
    }
  }

  protected setValue(value: File[] | File): void {
  }

}
