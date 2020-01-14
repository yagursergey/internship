import { Component, OnInit } from '@angular/core';
import { Realty } from 'src/app/model/Realty';
import { Router } from '@angular/router';
import { RealtyService } from 'src/app/serivce/realty.service';
import { FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';

@Component({
  selector: 'app-realty-creating',
  templateUrl: './realty.creating.component.html',
  styleUrls: ['./realty.creating.component.css']
})
export class RealtyCreatingComponent implements OnInit {

  realty: Realty;

  registerForm: FormGroup;

  price: string;
  square: string;
  type: string;
  description: string;

  constructor(
    private router: Router,
    private realtyService: RealtyService,
    private formBuilder: FormBuilder
    ) { }

   onFormSubmit(form: NgForm) {
     this.realtyService.save(form).subscribe( res => {
      this.goToRealtiesAll();
     });
   }

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      'price': [null, Validators.required],
      'square': [null, Validators.required],
      'type': [null, Validators.required],
      'description': [null, Validators.required]
    })
  }

  goToRealtiesAll() {
    this.router.navigate(['/realties']);
  }

}