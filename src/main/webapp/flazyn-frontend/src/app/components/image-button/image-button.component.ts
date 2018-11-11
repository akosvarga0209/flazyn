import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {OuterSubscriber} from "rxjs/internal-compatibility";

@Component({
  selector: 'app-image-button',
  templateUrl: './image-button.component.html',
  styleUrls: ['./image-button.component.css']
})
export class ImageButtonComponent implements OnInit {

  @Input() title: string;
  @Input() imageSrc: string;
  @Output() onClick: EventEmitter<void>;

  constructor() {
    this.onClick = new EventEmitter<void>();
  }

  ngOnInit() {
  }

  click(): void {
    this.onClick.emit();
  }

}
