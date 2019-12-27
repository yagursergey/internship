import { Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-lights',
  templateUrl: './lights.component.html',
  styleUrls: ['./lights.component.css']
})
export class LightsComponent implements OnInit {

  isVisible: boolean;

  constructor() {
    this.isVisible = false;
  }

  ngOnInit() {}
 
  turn() {
    this.isVisible = !this.isVisible;
    console.log(this.isVisible);
  }
}
