import { Component, OnInit, ViewChild } from '@angular/core';
import { Realty } from 'src/app/model/Realty';
import { Router, ActivatedRoute } from '@angular/router';
import { RealtyService } from 'src/app/serivce/realty.service';
import { MatTableDataSource, MatPaginator } from '@angular/material';
import { Pageable } from 'src/app/model/Pageable';
import { ParamSet } from 'src/app/model/ParamSet';

@Component({
  selector: 'app-realties.deleted.list',
  templateUrl: './realties.deleted.list.component.html',
  styleUrls: ['./realties.deleted.list.component.css']
})
export class RealtiesDeletedListComponent implements OnInit {

  id: string;
  realties: Realty[];
  displayedColumns: string[] = ['id', 'price', 'square', 'type', 'overview', 'undelete'];
  isASC = true;
  dataSource: MatTableDataSource<Realty>;
  pageable: Pageable;
  params: ParamSet;

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  constructor(
    private router: Router,
    private service: RealtyService,
    private route: ActivatedRoute
  ) {
    this.route.paramMap.subscribe( params => this.id = params.get('id'));
    this.params = new ParamSet();
    this.setDefaultParams();
  }

  ngOnInit() {
    this.service.getAllRealties(this.params).subscribe( data => {
      this.setData(data);
    });
  }

  logout() {
    localStorage.removeItem('token');
    this.router.navigate(['/login']);
  }

  goToRealtyOverview(id: string) {
    this.router.navigate(['/realties/' + id]);
  }

  goToUsersAll() {
    this.router.navigate(['/users/all']);
  }

  goToRealtiesAll() {
    this.router.navigate(['/realties/all']);
  }

  undelete(id: string) {
      this.service.undelete(id).subscribe( res => console.log(res));
      window.location.reload();
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
