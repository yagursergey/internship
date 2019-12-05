import { Component, OnInit } from '@angular/core';
import { Realty } from 'src/app/model/Realty';
import { Router } from '@angular/router';
import { RealtyService } from 'src/app/serivce/realty.service';

@Component({
  selector: 'app-realties-my-list',
  templateUrl: './realties.my.list.component.html',
  styleUrls: ['./realties.my.list.component.css']
})
export class RealtiesMyListComponent implements OnInit {

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
  localStorage.setItem('isOwner', 'true');
  this.router.navigate(['/realties/' + id]);
}
}
