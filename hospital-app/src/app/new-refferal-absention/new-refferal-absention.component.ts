import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {RefferalServiceService} from "../services/refferal-service.service";

@Component({
  selector: 'app-new-refferal-absention',
  templateUrl: './new-refferal-absention.component.html',
  styleUrls: ['./new-refferal-absention.component.scss']
})
export class NewRefferalAbsentionComponent implements OnInit {
  @ViewChild('StartDate') StartDate: ElementRef | any;
  @ViewChild('EndDate') EndDate: ElementRef | any;
  @ViewChild('description') description: ElementRef | any;
  @ViewChild('doctor') doctor: ElementRef | any;
  @ViewChild('user') user: ElementRef | any;
  @ViewChild('visit') visit: ElementRef | any;

  constructor(private refferalservice:RefferalServiceService) { }

  ngOnInit(): void {
  }

  addRefferall(){
    this.refferalservice.postRefferall(
      this.StartDate.nativeElement.value,
      this.EndDate.nativeElement.value,
      this.description.nativeElement.value,
      this.doctor.nativeElement.value,
      this.user.nativeElement.value,
      this.visit.nativeElement.value,
    ).then(r=>console.log(r));
  }

}
