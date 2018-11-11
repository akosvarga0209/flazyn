import {TranslateService} from "@ngx-translate/core";
import {EventEmitter} from "@angular/core";

export class TranslationHelper {

  constructor(private translateService: TranslateService) {}

  private messages: string[];
  private translations: string[];
  private readyCounter: number;
  private ready: EventEmitter<void>;

  public init(translations: string[]): TranslationHelper {
    this.ready = new EventEmitter<void>();
    this.readyCounter = 0;
    this.translations = translations;
    this.messages = [];

    return this;
  }

  private doTranslation() {
    this.translations.forEach(translation => {

      this.translateService.get(translation)
        .toPromise()
        .then(message =>  this.getMessage(translation, message))
        .catch(error =>   this.getMessage(translation, translation));

    });

  }

  public whenReady(callback: (messages: string[]) => any) {
    this.doTranslation();
    this.ready.subscribe(() => {
      callback(this.messages);
    })
  }

  private isReady(): boolean {
    return this.readyCounter == this.translations.length;
  }

  private addMessage(key: string, message: string) {
    this.messages[key] = message;
    this.readyCounter++;
  }

  private getMessage(key: string, message: string) {
    this.addMessage(key, message);
    if (this.isReady()) {
      this.ready.emit();
    }
  }

}
