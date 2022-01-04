import {Specialization} from "./specialization";

export interface Doctor{
  name:string;
  surname:string;
  specialisation:[Specialization]
}
