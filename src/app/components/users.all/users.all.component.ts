import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/serivce/user.service';
import { User } from 'src/app/model/User';
import { MatPaginator, MatTableDataSource } from '@angular/material';
import { Pageable } from 'src/app/model/Pageable';
import { ParamSet } from 'src/app/model/ParamSet';

@Component({
  selector: 'app-users.all',
  templateUrl: './users.all.component.html',
  styleUrls: ['./users.all.component.css']
})
export class UsersAllComponent implements OnInit {

  pageable: Pageable;
  params: ParamSet;
  users: User[];
  displayedColumns: string[] = ['id', 'email', 'firstName', 'secondName', 'role', 'overview'];
  isASC = true;
  dataSource: MatTableDataSource<User>;

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  constructor(
    private router: Router,
    private service: UserService
    ) {
      this.params = new ParamSet();
      this.setDefaultParams();
    }

  ngOnInit() {
    this.service.getAllUsers(this.params).subscribe( data => {
      this.setData(data);
    });
  }

  logout() {
    localStorage.removeItem('token');
    this.router.navigate(['/login']);
  }

  goToRealtiesAll() {
    this.router.navigate(['/realties/all']);
  }

  goToUserOverview(id: string) {
    this.router.navigate(['/users/' + id]);
  }

  goToRealtiesDeletedList() {
    this.router.navigate(['/deleted']);
  }

  getUsers(value: string) {
    this.isASC = !this.isASC;
    this.setQueryParams(null, value);
    this.service.getAllUsers(this.params).subscribe( data => {
      this.setData(data);
    });
  }

  handlePage(event) {
    this.setQueryParams(event,null);
    this.service.getAllUsers(this.params).subscribe( data => {
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
    this.users = data.content;
    this.dataSource = new MatTableDataSource(this.users);
    this.paginator.length = this.pageable.totalElements;
  }


  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

}
