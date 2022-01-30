import {VisitType} from "./VisitType";
import {Doctor} from "./Doctor";
import {Patient} from "./Patient";


export interface Visit{
  id: number;
  startDate:Date;
  description:string;
  visitType:VisitType;
  doctor:Doctor;
  user:Patient;

}
