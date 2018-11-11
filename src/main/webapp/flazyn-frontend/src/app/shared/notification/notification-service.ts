import {EventEmitter, Injectable} from "@angular/core";
import {MessageObject} from "./message-object";
import {Observable} from "rxjs/internal/Observable";
import {Observer} from "rxjs/internal/types";
import {NotificationType} from "./notification-type";


@Injectable()
export class NotificationService {
  private messageAdded: EventEmitter<MessageObject>;

  constructor() {
    this.messageAdded = new EventEmitter<MessageObject>();
  }

  public message(): Observable<MessageObject> {
    return Observable.create((observer: Observer<MessageObject>) => {
      this.messageAdded.subscribe(message => {
        observer.next(message);
      })
    });
  }

  public addMessage(message: MessageObject) {
    this.messageAdded.emit(message);
  }

  public addSuccessMessage(label: string, text: string ) {
    this.addMessage(new MessageObject(NotificationType.SUCCESS, label, text));
  }

}
