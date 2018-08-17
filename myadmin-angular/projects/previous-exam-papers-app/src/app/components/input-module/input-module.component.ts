import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {BlockUI, NgBlockUI} from "ng-block-ui";
import {Router} from "@angular/router";

@Component({
  selector: 'unisa-input-module',
  templateUrl: './input-module.component.html',
  styleUrls: ['./input-module.component.scss']
})
export class InputModuleComponent implements OnInit {

  inputForm: FormGroup;

  /**
   * Reference to blockUI
   */
  @BlockUI()
  private blockUI: NgBlockUI;

  constructor(private formBuilder: FormBuilder,
              private router: Router) {
    this.initForm();
  }


  private initForm(): void{
    this.inputForm = this.formBuilder.group({
      moduleCode : [null, Validators.required]
    });
  }

  ngOnInit() {
  }

  onSubmit() {
    const moduleCode = this.inputForm.value.moduleCode;
    this.blockUI.start("Loading exam papers...");
    this.router.navigate(['examPapers', moduleCode]);
  }
}
