export class User {
    id: number;
    email: string;
    fullName: string;
    profilePicture: string;
    balance: number;

    access_token: string;

    constructor(json?: any) {
        if (json) {
            this.id = json.id;
            this.email = json.email;
            this.fullName = json.fullName;
            this.profilePicture = json.profilePicture;
            this.balance = json.balance;
        }
    }

    setWithoutToken(json: any): User {
        if (json) {
            this.id = json.id;
            this.email = json.email;
            this.fullName = json.fullName;
            this.profilePicture = json.profilePicture;
            this.balance = json.balance;
        }
        return this;
    }
}