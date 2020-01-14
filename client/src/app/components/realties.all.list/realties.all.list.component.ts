import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { Realty } from 'src/app/model/Realty';
import { RealtyService } from 'src/app/serivce/realty.service';
import { MatPaginator, MatTableDataSource } from '@angular/material';

@Component({
  selector: 'app-realties-all-list',
  templateUrl: './realties.all.list.component.html',
  styleUrls: ['./realties.all.list.component.css']
})
export class RealtiesAllListComponent implements OnInit {

  dataSource: MatTableDataSource<Realty>;
  realties: Realty[];
  displayedColumns: string[] = ['id', 'price', 'square', 'type', 'overview'];
  isVisible = false;

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  constructor(
    private router: Router,
    private service: RealtyService
    ) {
  }

  ngOnInit() {
    this.getAll();
  }

  getAll() {
    this.service.getAllRealties().subscribe( data => {
      console.log(data);
      this.realties = data;
      this.dataSource.data = this.realties;
    });
  }

  goToCreateRealty() {
    this.router.navigate(['realties/creating']);
  }

}
