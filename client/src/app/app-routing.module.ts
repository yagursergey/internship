import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RealtiesAllListComponent } from './components/realties.all.list/realties.all.list.component';
import { RealtyCreatingComponent } from './components/realty.creating/realty.creating.component';

const routes: Routes = [
  {
    path: 'realties/all',
    // canActivate: [AuthGuard],
    component: RealtiesAllListComponent,
    data: { title: 'RealEstate' }
  },
  
  {
    path: '',
    component: RealtiesAllListComponent,
    data: { title: 'RealEstate' }
  },

  {
    path: 'realties/creating',
    component: RealtyCreatingComponent,
    data: { title: 'RealEstate' }
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})



export class AppRoutingModule { }
