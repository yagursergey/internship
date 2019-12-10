import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { User } from '../model/User';
import { catchError, tap } from 'rxjs/operators';

const apiUrl = 'http://localhost:8080/api/v1/users';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  getAllUsers(): Observable<User[]> {
    return this.http.get<User[]>(apiUrl)
      .pipe(
        tap(_=> this.log('fetched User')),
        catchError(this.handleError('getAllUsers', []))
      );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      console.error(error); 
      this.log(`${operation} failed: ${error.message}`);

      return of(result as T);
    };
  }

  sortByValue(value: string, by: string): Observable<User[]> {
    let params = new HttpParams().set("orderValue",value).set("orderBy", by);
    return this.http.get<User[]>(apiUrl, {params: params});
  }

  private log(message: string) {
    console.log(message);
  }

  public findById(id: string): Observable<User> {
    return this.http.get<User>(apiUrl + '/' + id);
  }

}
