import { Component, OnInit } from '@angular/core';
import { Realty } from 'src/app/model/Realty';
import { Router, ActivatedRoute } from '@angular/router';
import { RealtyService } from 'src/app/serivce/realty.service';

@Component({
  selector: 'app-realties.deleted.list',
  templateUrl: './realties.deleted.list.component.html',
  styleUrls: ['./realties.deleted.list.component.css']
})
export class RealtiesDeletedListComponent implements OnInit {

  id: string;
  realties: Realty[];
  displayedColumns: string[] = ['id', 'price', 'square', 'type', 'overview', 'undelete'];

  constructor(
    private router: Router,
    private realtyService: RealtyService,
    private route: ActivatedRoute
  ) {
    this.route.paramMap.subscribe( params => this.id = params.get('id'));
  }

  ngOnInit() {
    this.realtyService.findAllDeleted().subscribe( data => {
      this.realties = data;
    });
    console.log(this.realties);
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
  }

}
