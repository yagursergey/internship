import { Injectable } from '@angular/core';
import { Realty } from '../model/Realty';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';

const apiUrl = 'http://localhost:8080/realties';

@Injectable({
  providedIn: 'root'
})
export class RealtyService {

  constructor(private http: HttpClient) { }

  getAllRealties(): Observable<Realty[]> {
    return this.http.get<Realty[]>(apiUrl)
      .pipe(
        tap(_ => this.log('fetched Realty')),
        catchError(this.handleError('getRealties', []))
      );
  }

  getMyRealties(): Observable<Realty[]> {
    return this.http.get<Realty[]>(apiUrl + '/my')
      .pipe(
        tap(_ => this.log('fetched Realty')),
        catchError(this.handleError('getMyRealties', []))
      );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      console.error(error); 
      this.log(`${operation} failed: ${error.message}`);

      return of(result as T);
    };
  }

  private log(message: string) {
    console.log(message);
  }

  public save(realty: any): Observable<any> {
    return this.http.post<Realty>(apiUrl, realty);
  }

  public edit(realty: any, id: string): Observable<any> {
    return this.http.patch<Realty>(apiUrl + '/' + id, realty);
  }

  public findAllDeleted(): Observable<Realty[]> {
    return this.http.get<Realty[]>(apiUrl + '/deleted');
  }

  public findById(id: string): Observable<Realty> {
    return this.http.get<Realty>(apiUrl + '/' + id);
  }

  public deleteById(id: string): Observable<Realty> {
    return this.http.delete<Realty>(apiUrl + '/' + id);
  }

  undelete(id: string): Observable<any> {
    console.log(apiUrl + '/deleted/' + id)
    return this.http.patch<any>(apiUrl + '/deleted/' + id, '');
  }

}
