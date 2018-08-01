import {Component, OnInit} from "@angular/core";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {DatePipe} from "@angular/common";
import {BlockUI, NgBlockUI} from "ng-block-ui";
import {ToasterNotificationService, StudentInfo, StudentService} from "myadmin-lib";
import {SearchCriteriaService} from "../../services/search-criteria.service";

@Component({
  selector: 'unisa-forgot-student-search',
  templateUrl: './forgot-student-search.component.html',
  styleUrls: ['./forgot-student-search.component.scss']
})
export class ForgotStudentSearchComponent implements OnInit {

  studentInputForm:FormGroup;

  /**
   * Reference to blockUI
   */
  @BlockUI()
  private blockUI:NgBlockUI;

  constructor(private router:Router,
              private formBuilder:FormBuilder,
              private searchCriteriaService:SearchCriteriaService,
              private studentService:StudentService,
              private datePipe:DatePipe,
              private toaster:ToasterNotificationService) {
    this.initForm();
  }

  private initForm() {
    this.studentInputForm = this.formBuilder.group({
      surname: [null, Validators.required],
      firstNames: [null, Validators.required],
      dateOfBirth: [null, Validators.required],
      identityNumber: [null],
      searchOption: [''],
      passportNumber: [null]
    });
  }

  ngOnInit() {
  }

  private studentCall(students:StudentInfo[]):any {
    if (students.length > 1) {
      this.toaster.error('Validation Error', 'There is more than one student number that complies with the information submitted. Please contact study-info@unisa.ac.za for assistance.');
      this.blockUI.stop();
      return;
    }
    if (students.length == 0) {
      this.toaster.error('Validation Error', 'Student number not found. Check that your details have been entered correctly. If correct, please contact study-info@unisa.ac.za for assistance.');
      this.blockUI.stop();
      return;
    }
    this.searchCriteriaService.studentInfo = {...students[0]};
    this.router.navigateByUrl('/result')
  }

  onSubmit() {
    const formValue: any = this.studentInputForm.value;
    if (this.validateForm(formValue)) {
      this.blockUI.stop();
      return;
    }

    const dateString = this.datePipe.transform(formValue.dateOfBirth, 'yyyy-MM-dd', null, null);

    this.blockUI.start("Loading information...");
    if (formValue.identityNumber) {
      this.studentService.getStudentsBySurnameAndFirstnameAndBirthDateAndIdNumber(formValue.surname, formValue.firstNames, dateString, formValue.identityNumber)
        .subscribe(students => this.studentCall(students),
          () => this.blockUI.stop());
    } else if (formValue.passportNumber) {
      this.studentService.getStudentsBySurnameAndFirstnameAndBirthDateAndPassportNumber(formValue.surname, formValue.firstNames, dateString, formValue.passportNumber)
        .subscribe(students => this.studentCall(students),
          () => this.blockUI.stop());
    }
    else {
      this.studentService.getStudentsBySurnameAndFirstnameAndBirthDate(formValue.surname, formValue.firstNames, dateString)
        .subscribe(students => this.studentCall(students),
          () => this.blockUI.stop());
    }
  }

  private validateForm(formValue:any):boolean {
    if (formValue.searchOption) {
      if (formValue.searchOption === 'idNumber' && !formValue.identityNumber) {
        this.toaster.error('Validation Error', 'You have selected to search by ID number. Please enter your ID number.');
        return true;
      }
      else if (formValue.searchOption === 'passport' && !formValue.passportNumber) {
        this.toaster.error('Validation Error', 'You have selected to search by passport. Please enter your passport number.');
        return true;
      } else {
        return false;
      }

    }
  }

}
