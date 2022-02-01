import {Specialization} from "./specialization";

export interface Doctor{
  id:number;
  name:string;
  surname:string;
  specialisation:[Specialization];
  room:number;
}
