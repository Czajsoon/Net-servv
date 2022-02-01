import { Component, OnInit } from '@angular/core';
import {ViewDoctorsServiceService} from "../services/view-doctors-service.service";
import {Specialization} from "../models/specialization";
import {ReceptionistService} from "../services/receptionist.service";
import {Doctor} from "../models/Doctor";

@Component({
  selector: 'app-viewdoctors',
  templateUrl: './viewdoctors.component.html',
  styleUrls: ['./viewdoctors.component.scss']
})
export class ViewdoctorsComponent implements OnInit {

  doc_list:Doctor[]=[];

  spec_list:Specialization[]=[];
  constructor(private doctors_service:ViewDoctorsServiceService,
              private specialization_service:ReceptionistService) { }

  ngOnInit(): void {
    this.specialization_service.getSpecialisations().then(result=>{
      // @ts-ignore
      this.spec_list=result;
    })
  }

  getDoctors() {
    console.log()


  }


}
