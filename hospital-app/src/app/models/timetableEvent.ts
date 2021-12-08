export interface timetableEvent{
  type: string;
  date: Date | null;
  dateEnd: Date | null;
  doctor: string;
  spec: string;
  description:string;
}
