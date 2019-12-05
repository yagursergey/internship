import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Realty } from 'src/app/model/Realty';
import { RealtyService } from 'src/app/serivce/realty.service';

@Component({
  selector: 'app-realties-all-list',
  templateUrl: './realties.all.list.component.html',
  styleUrls: ['./realties.all.list.component.css']
})
export class RealtiesAllListComponent implements OnInit {

  realties: Realty[];
  displayedColumns: string[] = ['id', 'price', 'square', 'type', 'overview'];
  isVisibleForUser: boolean;
  isVisibleForAdmin: boolean;
  role: string;


  constructor(
    private router: Router,
    private realtyService: RealtyService
    ) {
      this.isVisibleForUser = false;
      this.isVisibleForAdmin = false;
      this.role = localStorage.getItem('role');
  }

  ngOnInit() {
    this.realtyService.getAllRealties().subscribe( data => {
      this.realties = data;
    });
    console.log(this.realties);
    this.checkRoleAndRenderElements();
  }

  checkRoleAndRenderElements() {
    if (this.role == "USER") {
      this.isVisibleForUser = true;
    } else {
      this.isVisibleForAdmin = true;
    }
    console.log(this.isVisibleForAdmin + ' \\\ ' + this.isVisibleForUser);
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
}
