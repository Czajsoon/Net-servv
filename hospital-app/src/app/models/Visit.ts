import {VisitType} from "./VisitType";
import {Doctor} from "./Doctor";
import {Patient} from "./Patient";


export interface Visit{
  startDate:Date;
  description:string;
  visitType:VisitType;
  doctor:Doctor;
  user:Patient;
}
