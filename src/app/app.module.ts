import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './auth/login/login.component';
import { RegisterComponent } from './auth/register/register.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { TokenInterceptor } from './interceptors/token.interceptor';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {
  MatInputModule,
  MatPaginatorModule,
  MatProgressSpinnerModule,
  MatSortModule,
  MatTableModule,
  MatIconModule,
  MatButtonModule,
  MatCardModule,
  MatFormFieldModule,
  MatTabsModule,
  MatToolbarModule,
  MatDividerModule,
  MatListModule,
  MatSelectModule,
 } from '@angular/material';
import { RealtiesAllListComponent } from './components/realties.all.list/realties.all.list.component';
import { RealtiesMyListComponent } from './components/realties.my.list/realties.my.list.component';
import { RealtyCreatingComponent } from './components/realty.creating/realty.creating.component';
import { RealtyEditingComponent } from './components/realty.editing/realty.editing.component';
import { RealtyOverviewComponent } from './components/realty.overview/realty.overview.component';
import { UsersAllComponent } from './components/users.all/users.all.component';
import { UserOverviewComponent } from './components/user.overview/user.overview.component';
import { RealtiesDeletedListComponent } from './components/realties.deleted.list/realties.deleted.list.component';
import { LightsComponent } from './components/lights/lights.component'

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    UsersAllComponent,
    UserOverviewComponent,
    RealtiesAllListComponent,
    RealtiesMyListComponent,
    RealtyCreatingComponent,
    RealtyEditingComponent,
    RealtyOverviewComponent,
    RealtiesDeletedListComponent,
    LightsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatInputModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatProgressSpinnerModule,
    MatIconModule,
    MatButtonModule,
    MatCardModule,
    MatFormFieldModule,
    MatTabsModule,
    MatToolbarModule,
    MatDividerModule,
    MatListModule,
    MatSelectModule,
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})

export class AppModule { }
