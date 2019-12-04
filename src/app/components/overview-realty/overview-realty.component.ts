import { Component, OnInit } from '@angular/core';
import { RealtyService } from 'src/app/serivce/realty.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Realty } from 'src/app/model/Realty';

@Component({
  selector: 'app-overview-realty',
  templateUrl: './overview-realty.component.html',
  styleUrls: ['./overview-realty.component.css']
})
export class OverviewRealtyComponent implements OnInit {

  id: string;
  realty: Realty;
  isVisible: boolean;

  constructor(
    private realtyService: RealtyService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.route.paramMap.subscribe( params => this.id = params.get('id'));
    this.isVisible = true;
  }

  ngOnInit() {
    this.realtyService.findById(this.id).subscribe( data => {
      this.realty = data;
      console.log(this.realty);
    });
  }

  isOwner() {
    // this.realtyService.
  }

  delete(id: string) {
    this.realtyService.deleteById(id).subscribe( data => {
      console.log('success');
    });
    this.goToRealtyMy();
  }

  goToRealtyMy() {
    this.router.navigate(['/realties/my']);
  }

  goToRealtyAll() {
    this.router.navigate(['/realties/all']);
  }

  goToRealtyEdit() {
    this.router.navigate(['/realties/edit/' + this.id]);
  }

}
