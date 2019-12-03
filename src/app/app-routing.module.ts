import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RegisterComponent } from './auth/register/register.component';
import { LoginComponent } from './auth/login/login.component';
import { AuthGuard } from './auth/auth.guard';
import { OverviewRealtyComponent} from './components/overview-realty/overview-realty.component';
import { AllRealtiesComponent } from './components/all-realties/all-realties.component';
import { MyRealtiesComponent } from './components/my-realties/my-realties.component';
import { EditRealtiesComponent } from './components/edit-realties/edit-realties.component';
import { CreateRealtiesComponent } from './components/create-realties/create-realties.component';



const routes: Routes = [
  {
    path: 'realties/all',
    canActivate: [AuthGuard],
    component: AllRealtiesComponent,
    data: { title: 'RealEstate' }
  },
  {
    path: 'realties/my',
    canActivate: [AuthGuard],
    component: MyRealtiesComponent,
    data: { title: 'RealEstate' }
  },
  {
    path: 'realties/new',
    canActivate: [AuthGuard],
    component: CreateRealtiesComponent,
    data: { title: 'RealEstate' }
  },
  {
    path: 'realties/edit',
    canActivate: [AuthGuard],
    component: EditRealtiesComponent,
    data: { title: 'RealEstate' }
  },
  {
    path: 'realties/:id',
    canActivate: [AuthGuard],
    component: OverviewRealtyComponent,
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
