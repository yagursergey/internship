import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroupDirective, FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';
import { AuthService } from '../../serivce/auth.service';
import { Router } from '@angular/router';
import { ErrorStateMatcher } from '@angular/material/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  email = '';
  password = '';
  isLoadingResults = false;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private authService: AuthService) { }

    ngOnInit() {
      this.loginForm = this.formBuilder.group({
        'email' : [null, Validators.required],
        'password' : [null, Validators.required]
      });
    }

    onFormSubmit(form: NgForm) {
      this.authService.login(form)
        .subscribe(res => {
          console.log(res);
          if (res.token) {
            localStorage.setItem('token', res.token);
            localStorage.setItem('role', res.role);
            localStorage.setItem('userFullname', res.userFullname);

            // sessionStorage.setItem('token', res.token);
            // sessionStorage.setItem('role', res.role);
            // sessionStorage.setItem('userFullname', res.userFullname);
            this.router.navigate(['realties/all']);
          }
        }, (err) => {
          console.log(err);
        });
    }

    register() {
      this.router.navigate(['register']);
    }

}
