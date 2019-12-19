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
      this.realtyForm = this.formBuilder.group({
        'id': [null, Validators.required],
        'price': [null, Validators.required],
        'square': [null, Validators.required],
        'dateOfCreation': [null, Validators.required],
        'type': [null, Validators.required],
        'description': [null, Validators.required],
        'ownerFirstName': [null, Validators.required]
      });
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
      this.realtyForm.setValue(this.realty);
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
