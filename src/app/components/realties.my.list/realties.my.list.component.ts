import { Component, OnInit, ViewChild } from '@angular/core';
import { Realty } from 'src/app/model/Realty';
import { Router } from '@angular/router';
import { RealtyService } from 'src/app/serivce/realty.service';
import { MatTableDataSource, MatPaginator } from '@angular/material';

@Component({
  selector: 'app-realties-my-list',
  templateUrl: './realties.my.list.component.html',
  styleUrls: ['./realties.my.list.component.css']
})
export class RealtiesMyListComponent implements OnInit {

  dataSource: MatTableDataSource<Realty>;
  realties: Realty[];
  displayedColumns: string[] = ['id', 'price', 'square', 'type', 'overview'];
  isASC: boolean;

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  constructor(private router: Router, private realtyService: RealtyService) {
    this.isASC = false;
  }

  ngOnInit() {
    this.realtyService.getMyRealties().subscribe( data => {
      this.realties = data;
      this.updateTable();
    });
    console.log(this.realties);
  }

  sort(value: string) {
    console.log(value);
    if (this.isASC) {
      this.realtyService.sortByValueMy(value, 'ASC').subscribe( data => {
        this.realties = data;
      })
    } else {
      this.realtyService.sortByValueMy(value, 'DESC').subscribe( data => {
        this.realties = data;
      })
    }
    this.updateTable();
    this.isASC = !this.isASC;
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

updateTable() {
  this.dataSource = new MatTableDataSource(this.realties);
  this.dataSource.paginator = this.paginator;
}

applyFilter(filterValue: string) {
  this.dataSource.filter = filterValue.trim().toLowerCase();
}

}
