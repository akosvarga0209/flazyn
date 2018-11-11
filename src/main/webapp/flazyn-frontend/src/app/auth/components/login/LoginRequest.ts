export class LoginRequest {

    email: string;
    password: string;
    captchaResponse: string;

    constructor(json?: any) {
        if (json) {
            this.email = json.email;
            this.password = json.password;
            this.captchaResponse = json.captchaResponse;
        }
    }

}
