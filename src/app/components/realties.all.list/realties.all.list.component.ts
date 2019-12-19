import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { Realty } from 'src/app/model/Realty';
import { RealtyService } from 'src/app/serivce/realty.service';
import { MatPaginator, MatTableDataSource } from '@angular/material';
import { Pageable } from 'src/app/model/Pageable';
import { ParamSet } from 'src/app/model/ParamSet';

@Component({
  selector: 'app-realties-all-list',
  templateUrl: './realties.all.list.component.html',
  styleUrls: ['./realties.all.list.component.css']
})
export class RealtiesAllListComponent implements OnInit {

  dataSource: MatTableDataSource<Realty>;
  realties: Realty[];
  pageable: Pageable;
  params: ParamSet;
  displayedColumns: string[] = ['id', 'price', 'square', 'type', 'overview'];
  isVisible = false;
  isASC = true;

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  constructor(
    private router: Router,
    private service: RealtyService
    ) {
      this.params = new ParamSet();
      this.setDefaultParams();
      this.checkRole();
  }

  ngOnInit() {
    this.service.getAllRealties(this.params).subscribe( data => {
      this.setData(data);
    });
  }


  checkRole() {
    let role = localStorage.getItem('role');
    if(role == 'ADMIN') {
      this.isVisible = true;
    }
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

  goToRealtiesDeletedList() {
    this.router.navigate(['/deleted']);
  }

  getRealties(value: string) {
    this.isASC = !this.isASC;
    this.setQueryParams(null, value);
    this.service.getAllRealties(this.params).subscribe( data => {
      this.setData(data);
    });
  }

  handlePage(event) {
    this.setQueryParams(event,null);
    this.service.getAllRealties(this.params).subscribe( data => {
      this.setData(data);
    });
  }

  private setQueryParams(event, value: string) {
    if(event != null) {
      this.params.pageNum = event.pageIndex;
    }
    if(value != null) {
      this.params.value = value;
    }
    if(this.isASC) {
      this.params.with = "ASC";
    } else {
      this.params.with = "DESC";
    }

  }

  private setDefaultParams() {
      this.params.pageNum = "0";
      this.params.value = "id";
      this.params.with = "ASC";
  }

  private setData(data) {
    this.pageable = data;
    this.realties = data.content;
    this.dataSource = new MatTableDataSource(this.realties);
    this.paginator.length = this.pageable.totalElements;
  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

}
