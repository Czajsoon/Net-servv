export interface timetableEvent{
  type: string;
  activity:Activity;
}

export interface Activity{
  doctor:Doctor;
  startDate: Date;
  endDate: Date | null;
  description:string;
  visitType:VisitType;
}

export interface VisitType{
  name:string;
}

export interface Doctor{
  name:string;
  surname:string;
}
