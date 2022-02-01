import { Component, OnInit } from '@angular/core';
import {AuthService} from "../services/auth.service";

@Component({
  selector: 'app-doctor-panel',
  templateUrl: './doctor-panel.component.html',
  styleUrls: ['./doctor-panel.component.scss']
})
export class DoctorPanelComponent implements OnInit {

  constructor(auth:AuthService) { }

  ngOnInit(): void {
  }

}
