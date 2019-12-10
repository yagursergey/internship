import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/serivce/user.service';
import { User } from 'src/app/model/User';
import { MatPaginator, MatTableDataSource } from '@angular/material';

@Component({
  selector: 'app-users.all',
  templateUrl: './users.all.component.html',
  styleUrls: ['./users.all.component.css']
})
export class UsersAllComponent implements OnInit {

  users: User[];
  displayedColumns: string[] = ['id', 'email', 'firstName', 'secondName', 'role', 'overview'];
  isASC: boolean;
  dataSource: MatTableDataSource<User>;

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  constructor(
    private router: Router,
    private userService: UserService) {
      this.isASC = false;
    }

  ngOnInit() {
    this.userService.getAllUsers().subscribe( data => {
      this.users = data;
      this.updateTable();
    });
    console.log(this.users);
  }

  sort(value: string) {
    console.log(value);
    if (this.isASC) {
      this.userService.sortByValue(value, 'ASC').subscribe( data => {
        this.users = data;
      })
    } else {
      this.userService.sortByValue(value, 'DESC').subscribe( data => {
        this.users = data;
      })
    }
    this.updateTable();
    this.isASC = !this.isASC;
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

  updateTable() {
    this.dataSource = new MatTableDataSource(this.users);
    this.dataSource.paginator = this.paginator;
  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

}
