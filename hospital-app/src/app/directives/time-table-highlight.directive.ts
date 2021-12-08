import {Directive, ElementRef, Input, SimpleChanges} from '@angular/core';

@Directive({
  selector: '[TimeTableHighlight]',
  inputs: ['type']
})
export class TimeTableHighlightDirective{

// @ts-ignore
  type: string;

  constructor() {
    // @ts-ignore
    console.log(this.type);
  //   // @ts-ignore
  //   if(this.type=="Badanie"){
  //     el.nativeElement.setAttribute("style","background-color: #99fff3");
  //   }
  //   else { // @ts-ignore
  //     if(this.type == "Wizyta"){
  //           el.nativeElement.setAttribute("style","background-color: #63e8ff");
  //         }
  //         else {
  //           el.nativeElement.setAttribute("style","background-color: #639fff");
  //         }
  //   }
  }

}
