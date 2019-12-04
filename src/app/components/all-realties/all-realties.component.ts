import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { Realty } from 'src/app/model/Realty';
import { RealtyService } from 'src/app/serivce/realty.service';
import { MatPaginator } from '@angular/material';

@Component({
  selector: 'app-all-realties',
  templateUrl: './all-realties.component.html',
  styleUrls: ['./all-realties.component.css']
})
export class AllRealtiesComponent implements OnInit {

  realties: Realty[];
  displayedColumns: string[] = ['id', 'price', 'square', 'type', 'overview'];

  // resultLength = 0;

  // @ViewChild(MatPaginator, {static: false}) paginator: MatPaginator;

  constructor(
    private router: Router,
    private realtyService: RealtyService
    ) { }

  ngOnInit() {
    this.realtyService.getAllRealties().subscribe( data => {
      this.realties = data;
    });
    console.log(this.realties);
  }

logout() {
  localStorage.removeItem('token');
  this.router.navigate(['/login']);
}

goToRealtiesMy() {
  this.router.navigate(['/realties/my']);
}

goToRealtiesCreate() {
  this.router.navigate(['/realties/new']);
}

goToRealtyOverview(id: string) {
  this.router.navigate(['/realties/' + id]);
}

goToUsersAll() {
  this.router.navigate(['/users/all']);
}

}
