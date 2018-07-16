import {Component, OnInit} from "@angular/core";
import {FormBuilder, FormGroup, Validators, FormControl} from "@angular/forms";
import {Router} from "@angular/router";
import {DatePipe} from "@angular/common";
import {BlockUI, NgBlockUI} from "ng-block-ui";
import {ToasterNotificationService, StudentInfo} from "myadmin-lib";
import {SearchCriteriaService, StudentService} from "../../services";

@Component({
  selector: 'unisa-forgot-student-search',
  templateUrl: './forgot-student-search.component.html',
  styleUrls: ['./forgot-student-search.component.scss']
})
export class ForgotStudentSearchComponent implements OnInit {

  studentInputForm:FormGroup;

  studentInfo:StudentInfo;

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

  get surname():FormControl {
    return <FormControl>this.studentInputForm.controls['surname'];
  }

  get firstNames():FormControl {
    return <FormControl>this.studentInputForm.controls['firstNames'];
  }

  get dateOfBirth():FormControl {
    return <FormControl>this.studentInputForm.controls['dateOfBirth'];
  }

  get searchOption():FormControl {
    return <FormControl>this.studentInputForm.controls['searchOption'];
  }

  get identityNumber():FormControl {
    return <FormControl>this.studentInputForm.controls['identityNumber'];
  }

  get passportNumber():FormControl {
    return <FormControl>this.studentInputForm.controls['passportNumber'];
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
    if (this.validateForm()) {
      this.blockUI.stop();
      return;
    }
    this.studentInfo = {...this.studentInputForm.value};

    const dateString = this.datePipe.transform(this.studentInfo.dateOfBirth, 'yyyy-MM-dd', null, null);

    this.blockUI.start("Loading information...");
    if (this.identityNumber.value) {
      this.studentService.getStudentsBySurnameAndFirstnameAndBirthDateAndIdNumber(this.studentInfo.surname, this.studentInfo.firstNames, dateString, this.studentInfo.identityNumber)
        .subscribe(students => this.studentCall(students),
          () => this.blockUI.stop());
    } else if (this.passportNumber.value) {
      this.studentService.getStudentsBySurnameAndFirstnameAndBirthDateAndPassportNumber(this.studentInfo.surname, this.studentInfo.firstNames, dateString, this.studentInfo.passportNumber)
        .subscribe(students => this.studentCall(students),
          () => this.blockUI.stop());
    }
    else {
      this.studentService.getStudentsBySurnameAndFirstnameAndBirthDate(this.studentInfo.surname, this.studentInfo.firstNames, dateString)
        .subscribe(students => this.studentCall(students),
          () => this.blockUI.stop());
    }
  }

  private validateForm():boolean {
    if (this.searchOption.value) {
      if (this.searchOption.value === 'idNumber' && !this.identityNumber.value) {
        this.toaster.error('Validation Error', 'You have selected to search by ID number. Please enter your ID number.');
        return true;
      }
      else if (this.searchOption.value === 'passport' && !this.passportNumber.value) {
        this.toaster.error('Validation Error', 'You have selected to search by passport. Please enter your passport number.');
        return true;
      } else {
        return false;
      }

    }
  }

}
