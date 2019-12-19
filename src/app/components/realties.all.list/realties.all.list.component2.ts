// import { Component, OnInit, ViewChild } from '@angular/core';
// import { Router } from '@angular/router';
// import { Realty } from 'src/app/model/Realty';
// import { RealtyService } from 'src/app/serivce/realty.service';
// import { MatPaginator, MatTableDataSource } from '@angular/material';
// import { Pageable } from 'src/app/model/Pageable';

// @Component({
//   selector: 'app-realties-all-list',
//   templateUrl: './realties.all.list.component.html',
//   styleUrls: ['./realties.all.list.component.css']
// })
// export class RealtiesAllListComponent implements OnInit {

//   dataSource: MatTableDataSource<Realty>;
//   realties: Realty[];
//   pageable: Pageable;
//   displayedColumns: string[] = ['id', 'price', 'square', 'type', 'overview'];
  // isVisibleForUser: boolean;
  // isVisibleForAdmin: boolean;
  // isASC: boolean;
  // role: string;

  // @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  // constructor(
  //   private router: Router,
  //   private realtyService: RealtyService
  //   ) {
  //     this.isVisibleForUser = false;
  //     this.isVisibleForAdmin = false;
  //     this.isASC = false;
  //     this.role = localStorage.getItem('role');
  // }

  // ngOnInit() {
    // this.realtyService.getAllRealties().subscribe( data => {
      // this.realties = data;
      // this.pageable = data;
      // this.realties = data.content;
      // this.updateTable();
      // this.toPaginator(data);
  //   });
  //   this.checkRoleAndRenderElements();
  // }

  // toPaginator(data: any) {
  //   this.paginator.length = this.pageable.totalElements;
  //   this.paginator.nextPage;
  // }

  // checkRoleAndRenderElements() {
  //   if (this.role == "USER") {
  //     this.isVisibleForUser = true;
  //   } else {
  //     this.isVisibleForAdmin = true;
  //   }
  //   console.log(this.isVisibleForAdmin + ' \\\ ' + this.isVisibleForUser);
  // }

  // sort(value: string) {
  //   console.log(value);
  //   if (this.isASC) {
  //     this.realtyService.sortByValue(value, 'ASC').subscribe( data => {
  //       this.realties = data;
  //     })
  //   } else {
  //     this.realtyService.sortByValue(value, 'DESC').subscribe( data => {
  //       this.realties = data;
  //     })
  //   }
  //   this.updateTable();
  //   this.isASC = !this.isASC;
  // }

  // logout() {
  //   localStorage.removeItem('token');
  //   this.router.navigate(['/login']);
  // }

  // goToRealtiesMy() {
  //   this.router.navigate(['/realties/my']);
  // }

  // goToRealtiesCreate() {
  //   this.router.navigate(['/realties/new']);
  // }

  // goToRealtyOverview(id: string) {
  //   this.router.navigate(['/realties/' + id]);
  // }

  // goToUsersAll() {
  //   this.router.navigate(['/users/all']);
  // }

  // goToRealtiesDeletedList() {
  //   this.router.navigate(['/deleted']);
  // }

  // updateTable() {
  //   this.dataSource = new MatTableDataSource(this.realties);
  //   this.dataSource.paginator = this.paginator;
  // }

  // applyFilter(filterValue: string) {
  //   this.dataSource.filter = filterValue.trim().toLowerCase();
  // }
// }
