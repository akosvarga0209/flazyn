export class Nature {
  id: number;
  description: string;


  constructor(json: any) {
    this.id = json.id;
    this.description = json.description;
  }
}
