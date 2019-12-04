import { Component, OnInit } from '@angular/core';
import { Realty } from 'src/app/model/Realty';
import { Router } from '@angular/router';
import { RealtyService } from 'src/app/serivce/realty.service';
import { FormControl, FormGroupDirective, FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';

@Component({
  selector: 'app-create-realties',
  templateUrl: './create-realties.component.html',
  styleUrls: ['./create-realties.component.css']
})
export class CreateRealtiesComponent implements OnInit {

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
    ) {
    // this.realty = new Realty();
   }

   onFormSubmit(form: NgForm) {
     this.realtyService.save(form).subscribe( res => {
      this.goToRealtiesMy();
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

  goToRealtiesMy() {
    this.router.navigate(['/realties/my']);
  }

  goToRealtiesAll() {
    this.router.navigate(['/realties/all'])
  }
  
  logout() {
    localStorage.removeItem('token');
    this.router.navigate(['/login'])
  }
}
