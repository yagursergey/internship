import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { User } from '../model/User';
import { catchError, tap } from 'rxjs/operators';
import { ParamSet } from '../model/ParamSet';
import { Pageable } from '../model/Pageable';

const apiUrl = 'http://localhost:8080/api/v1/users';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  getAllUsers(params: ParamSet): Observable<Pageable> {
    return this.http.get<Pageable>(apiUrl, {params: this.getParams(params)});
  }

  public findById(id: string): Observable<User> {
    return this.http.get<User>(apiUrl + '/' + id);
  }

  private getParams(params: ParamSet) {
    return new HttpParams()
    .set("sortBy", params.value)
    .set("sortWith", params.with)
    .set("pageNum", params.pageNum);
  }

}
