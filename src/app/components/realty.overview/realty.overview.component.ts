import { Component, OnInit } from '@angular/core';
import { RealtyService } from 'src/app/serivce/realty.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Realty } from 'src/app/model/Realty';

@Component({
  selector: 'app-realty-overview',
  templateUrl: './realty.overview.component.html',
  styleUrls: ['./realty.overview.component.css']
})
export class RealtyOverviewComponent implements OnInit {

  id: string;
  realty: Realty;
  isVisibleForUser: boolean;
  isVisibleForAdmin: boolean;
  role: string;
  isOwner: boolean = false;

  constructor(
    private realtyService: RealtyService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.route.paramMap.subscribe( params => this.id = params.get('id'));
    this.isVisibleForUser = false;
    this.isVisibleForAdmin = false;
    this.role = localStorage.getItem('role');
    if (localStorage.getItem('isOwner') == 'true') {
      this.isOwner = true;
    }
  }

  ngOnInit() {
    this.realtyService.findById(this.id).subscribe( data => {
      this.realty = data;
    });
    console.log(this.realty);
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
    localStorage.removeItem('isOwner');
    this.router.navigate(['/login']);
  }

  delete(id: string) {
    this.realtyService.deleteById(id).subscribe( data => {
      console.log('success');
    });
    localStorage.removeItem('isOwner');
    this.goToRealtyAll();
  }

  goToRealtyMy() {
    this.router.navigate(['/realties/my']);
    localStorage.removeItem('isOwner');
  }

  goToRealtyAll() {
    this.router.navigate(['/realties/all']);
    localStorage.removeItem('isOwner');
  }

  goToRealtyEdit() {
    this.router.navigate(['/realties/editing/' + this.id]);
    localStorage.removeItem('isOwner');
  }

}
