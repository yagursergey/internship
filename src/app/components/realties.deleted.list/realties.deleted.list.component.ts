import { Component, OnInit, ViewChild } from '@angular/core';
import { Realty } from 'src/app/model/Realty';
import { Router, ActivatedRoute } from '@angular/router';
import { RealtyService } from 'src/app/serivce/realty.service';
import { MatTableDataSource, MatPaginator } from '@angular/material';

@Component({
  selector: 'app-realties.deleted.list',
  templateUrl: './realties.deleted.list.component.html',
  styleUrls: ['./realties.deleted.list.component.css']
})
export class RealtiesDeletedListComponent implements OnInit {

  id: string;
  realties: Realty[];
  displayedColumns: string[] = ['id', 'price', 'square', 'type', 'overview', 'undelete'];
  isASC: boolean;
  dataSource: MatTableDataSource<Realty>;

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  constructor(
    private router: Router,
    private realtyService: RealtyService,
    private route: ActivatedRoute
  ) {
    this.route.paramMap.subscribe( params => this.id = params.get('id'));
    this.isASC = false;
  }

  ngOnInit() {
    this.realtyService.findAllDeleted().subscribe( data => {
      this.realties = data;
      this.updateTable();
    });
    console.log(this.realties);
  }

  sort(value: string) {
    console.log(value);
    if (this.isASC) {
      this.realtyService.sortByValueDel(value, 'ASC').subscribe( data => {
        this.realties = data;
      })
    } else {
      this.realtyService.sortByValueDel(value, 'DESC').subscribe( data => {
        this.realties = data;
      })
    }
    this.updateTable();
    this.isASC = !this.isASC;
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
      this.realtyService.undelete(id).subscribe( res => console.log(res));
      window.location.reload();
  }

  updateTable() {
    this.dataSource = new MatTableDataSource(this.realties);
    this.dataSource.paginator = this.paginator;
  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

}
