import { Component, OnInit } from '@angular/core';
import { Realty } from 'src/app/model/Realty';
import { FormGroup, FormBuilder, NgForm, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { RealtyService } from 'src/app/serivce/realty.service';

@Component({
  selector: 'app-realty-editing',
  templateUrl: './realty.editing.component.html',
  styleUrls: ['./realty.editing.component.css']
})
export class RealtyEditingComponent implements OnInit {

 
  realty: Realty;
  id: string;
  realtyForm: FormGroup;

  price: string;
  square: string;
  type: string;
  description: string;

  constructor(
    private router: Router,
    private realtyService: RealtyService,
    private formBuilder: FormBuilder,
    private route: ActivatedRoute
    ) {
    this.route.paramMap.subscribe( params => this.id = params.get('id'));
   }

   onFormSubmit(form: NgForm) {
     console.log(form);
     this.realtyService.edit(form, this.id).subscribe( res => {
      this.goToRealtiesMy();
     });
   }

  ngOnInit() {

    this.realtyService.findById(this.id).subscribe( data => {
      this.realty = data;
      console.log(this.realty)
    })

    this.realtyForm = this.formBuilder.group({
      'price': [null, Validators.required],
      'square': [null, Validators.required],
      'type': [null, Validators.required],
      'description': [null, Validators.required]
    });

    // this.fillRealtyForm();
  }

  // fillRealtyForm() {
  //   this.realtyForm.setValue({
  //     price: this.realty.price,
  //     square: this.realty.square,
  //     type: this.realty.type,
  //     description: this.realty.description
  //   })
  // }

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
