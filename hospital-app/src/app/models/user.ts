import {Roles} from "./Roles";

export interface User{
  id:number;
  name:string;
  surname:string;
  username:string;
  sex:string;
  identification:string;
  bornDate:Date;
  token:string;
  roles:[Roles];
}
