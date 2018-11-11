import { Injectable, EventEmitter } from '@angular/core';


@Injectable()
export class TabNavigationService {

    public navigateToLogin: EventEmitter<string> = new EventEmitter();

    constructor() {
    }

}
