import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Realty } from 'src/app/model/Realty';
import { RealtyService } from 'src/app/serivce/realty.service';

@Component({
  selector: 'app-all-realties',
  templateUrl: './all-realties.component.html',
  styleUrls: ['./all-realties.component.css']
})
export class AllRealtiesComponent implements OnInit {

  realties: Realty[];
  displayedColumns: string[] = ['id', 'price', 'square', 'type'];

  constructor(private router: Router, private realtyService: RealtyService) { }

  ngOnInit() {
    this.realtyService.getAllRealties().subscribe( data => {
      this.realties = data;
    });
    console.log(this.realties);
  }

goToRealtiesMy() {
  this.router.navigate(['/realties/my']);
}

goToRealtiesCreate() {
  this.router.navigate(['/realties/create']);
}

goToRealtiesOverview() {
  this.router.navigate(['/realty/{id}']);
}

}
