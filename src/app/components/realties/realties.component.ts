import { Component, OnInit } from '@angular/core';
import { Realty } from '../../model/Realty';
import { RealtyService } from '../../serivce/realty.service';
import { AuthService } from '../../serivce/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-realties',
  templateUrl: './realties.component.html',
  styleUrls: ['./realties.component.css']
})
export class RealtiesComponent implements OnInit {

  constructor(private realtyService: RealtyService, private authService: AuthService, private router: Router) { }

  data: Realty[] = [];
  displayedColumns: string[] = ['id', 'price', 'square', 'dateOfCreation', 'type'];
  isLoadingResults = true;

  getMyRealties(): void {
    this.realtyService.getMyRealties()
      .subscribe(realties => {
        this.data = realties;
        console.log(this.data);
        this.isLoadingResults = false;
      }, err => {
        console.log(err);
        this.isLoadingResults = false;
      });
  }

  ngOnInit() {
    this.getMyRealties();
  }

  logout() {
    localStorage.removeItem('token');
    this.router.navigate(['login']);
  }

}
