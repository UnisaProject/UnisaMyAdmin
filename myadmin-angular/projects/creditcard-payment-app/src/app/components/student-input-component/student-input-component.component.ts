import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {CreditCardFormService} from "../../services/creditcard-form.service";

@Component({
  selector: 'unisa-student-input-component',
  templateUrl: './student-input-component.component.html',
  styleUrls: ['./student-input-component.component.scss']
})
export class StudentInputComponentComponent implements OnInit {

  studentInputForm: FormGroup;

  constructor(private router:Router,
              private formBuilder: FormBuilder,
              private creditCardFormService: CreditCardFormService) {

    this.studentInputForm = this.formBuilder.group({
      studentNumber : [
        null,
        Validators.compose([
          Validators.required,
          Validators.maxLength(8),
          Validators.minLength(7),
          Validators.pattern("([0-9])*")
        ])
      ]
    });
  }



  ngOnInit() {
  }

  onSubmit(){
    // Update the session with the student number
    this.creditCardFormService.creditCardPaymentForm = {
      studentNumber : this.studentInputForm.value.studentNumber
    };

    // Change page to prompt for qualification
    this.router.navigateByUrl('/qualInput')
  }

}
