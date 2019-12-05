import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RegisterComponent } from './auth/register/register.component';
import { LoginComponent } from './auth/login/login.component';
import { AuthGuard } from './auth/auth.guard';
import { RealtyOverviewComponent} from './components/realty.overview/realty.overview.component';
import { RealtiesAllListComponent } from './components/realties.all.list/realties.all.list.component';
import { RealtiesMyListComponent } from './components/realties.my.list/realties.my.list.component';
import { RealtyEditingComponent } from './components/realty.editing/realty.editing.component';
import { RealtyCreatingComponent } from './components/realty.creating/realty.creating.component';
import { UsersAllComponent } from './components/users.all/users.all.component';
import { UserOverviewComponent } from './components/user.overview/user.overview.component';
import { RealtiesDeletedListComponent } from './components/realties.deleted.list/realties.deleted.list.component';



const routes: Routes = [
  {
    path: 'deleted',
    canActivate: [AuthGuard],
    component: RealtiesDeletedListComponent,
    data: { title: 'RealEstate' }
  },
  {
    path: 'realties/all',
    canActivate: [AuthGuard],
    component: RealtiesAllListComponent,
    data: { title: 'RealEstate' }
  },
  {
    path: 'users/all',
    canActivate: [AuthGuard],
    component: UsersAllComponent,
    data: { title: 'RealEstate' }
  },
  {
    path: 'realties/my',
    canActivate: [AuthGuard],
    component: RealtiesMyListComponent,
    data: { title: 'RealEstate' }
  },
  {
    path: 'realties/new',
    canActivate: [AuthGuard],
    component: RealtyCreatingComponent,
    data: { title: 'RealEstate' }
  },
  {
    path: 'realties/editing/:id',
    canActivate: [AuthGuard],
    component: RealtyEditingComponent,
    data: { title: 'RealEstate' }
  },
  {
    path: 'realties/:id',
    canActivate: [AuthGuard],
    component: RealtyOverviewComponent,
    data: { title: 'Overview' }
  },
  {
    path: 'users/:id',
    canActivate: [AuthGuard],
    component: UserOverviewComponent,
    data: { title: 'Overview' }
  },
  {
    path: 'login',
    component: LoginComponent,
    data: { title: 'Login' }
  },
  {
    path: '',
    component: LoginComponent,
    data: { title: 'Login' }
  },
  {
    path: 'register',
    component: RegisterComponent,
    data: { title: 'Register' }
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})



export class AppRoutingModule { }
