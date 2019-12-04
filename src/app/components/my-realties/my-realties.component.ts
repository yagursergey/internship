import { Component, OnInit } from '@angular/core';
import { Realty } from 'src/app/model/Realty';
import { Router } from '@angular/router';
import { RealtyService } from 'src/app/serivce/realty.service';

@Component({
  selector: 'app-my-realties',
  templateUrl: './my-realties.component.html',
  styleUrls: ['./my-realties.component.css']
})
export class MyRealtiesComponent implements OnInit {

  realties: Realty[];
  displayedColumns: string[] = ['id', 'price', 'square', 'type', 'overview'];

  constructor(private router: Router, private realtyService: RealtyService) { }

  ngOnInit() {
    this.realtyService.getMyRealties().subscribe( data => {
      this.realties = data;
    });
    console.log(this.realties);
  }

logout() {
  localStorage.removeItem('token');
  this.router.navigate(['login']);
}

goToRealtiesAll() {
  this.router.navigate(['/realties/all']);
}

goToRealtiesCreate() {
  this.router.navigate(['/realties/new']);
}

goToRealtyOverview(id: string) {
  this.router.navigate(['/realties/' + id]);
}
}
