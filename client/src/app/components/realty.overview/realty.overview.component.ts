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

  constructor(
    private realtyService: RealtyService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.route.paramMap.subscribe( params => this.id = params.get('id'));
  }

  ngOnInit() {
    this.realtyService.findById(this.id).subscribe( data => {
      this.realty = data;
    });
    console.log(this.realty);
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
    this.router.navigate(['/realties']);
  }

  goToRealtyEdit() {
    this.router.navigate(['/realties/editing/' + this.id]);
  }

}
