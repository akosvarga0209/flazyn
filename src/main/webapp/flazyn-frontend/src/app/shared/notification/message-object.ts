import {Message} from "primeng/api";
import {NotificationType} from "./notification-type";

export class MessageObject implements Message {
  severity: string;
  summary?: string;
  detail?: string;
  id?: any;
  key?: string;
  life?: number;
  sticky?: boolean;
  closable?: boolean;
  data?: any;
  constructor(type?: NotificationType,
              summary?: string,
              detail?: string,
              id?: any,
              key?: string,
              timeout?: number,
              sticky?: boolean,
              closable?: boolean,
              data?: any) {
    this.severity = type.toString();
    this.summary = summary;
    this.detail = detail;
    this.id = id;
    this.key = key;
    this.life = timeout;
    this.sticky = sticky;
    this.closable = closable;
    this.data = data;
  }
}
