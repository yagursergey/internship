import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/User';
import { UserService } from 'src/app/serivce/user.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-user.overview',
  templateUrl: './user.overview.component.html',
  styleUrls: ['./user.overview.component.css']
})
export class UserOverviewComponent implements OnInit {

  id: string;
  user: User;

  constructor(
    private userService: UserService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.route.paramMap.subscribe( params => this.id = params.get('id'));
    console.log(this.id);
  }

  ngOnInit() {
    this.userService.findById(this.id).subscribe( data => {
      this.user = data;
      console.log(this.user);
    });
  }

  goToRealtyAll() {
    this.router.navigate(['/realties/all']);
  }

  goToUserAll() {
    this.router.navigate(['/users/all']);
  }

}
