import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/serivce/user.service';
import { User } from 'src/app/model/User';

@Component({
  selector: 'app-users.all',
  templateUrl: './users.all.component.html',
  styleUrls: ['./users.all.component.css']
})
export class UsersAllComponent implements OnInit {

  users: User[];
  displayedColumns: string[] = ['id', 'email', 'firstName', 'secondName', 'role', 'overview'];

  constructor(
    private router: Router,
    private userService: UserService) { }

  ngOnInit() {
    this.userService.getAllUsers().subscribe( data => {
      this.users = data;
    });
    console.log(this.users);
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

}
