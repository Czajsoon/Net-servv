import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {DrugServiceService} from "../services/drug-service.service";

@Component({
  selector: 'app-new-drug',
  templateUrl: './new-drug.component.html',
  styleUrls: ['./new-drug.component.scss']
})
export class NewDrugComponent implements OnInit {

  @ViewChild('drugName') drugName: ElementRef | any;
  @ViewChild('drugPrice') drugPrice: ElementRef | any;
  @ViewChild('drugNumber') drugNumber: ElementRef | any;
  constructor(
    private drugService: DrugServiceService) {
  }

  ngOnInit(): void {
  }

  addDrug(){
    this.drugService.postDrug(this.drugName.nativeElement.value,
      this.drugNumber.nativeElement.value,
      this.drugPrice.nativeElement.value).then(r => console.log(r));
  }
}
