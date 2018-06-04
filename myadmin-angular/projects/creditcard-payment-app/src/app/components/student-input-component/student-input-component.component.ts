import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'unisa-student-input-component',
  templateUrl: './student-input-component.component.html',
  styleUrls: ['./student-input-component.component.scss']
})
export class StudentInputComponentComponent implements OnInit {

  constructor(private router:Router) { }

  ngOnInit() {
  }

  onSubmit(){
      this.router.navigateByUrl('/qualInput')
  }

}
