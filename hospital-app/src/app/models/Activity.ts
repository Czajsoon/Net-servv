import {Doctor} from "./Doctor";
import {VisitType} from "./VisitType";

export interface Activity{
  doctor:Doctor;
  startDate: Date;
  endDate: Date | null;
  description:string;
  visitType:VisitType;
}
