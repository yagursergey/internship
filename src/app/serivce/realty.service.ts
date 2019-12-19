import { Injectable } from '@angular/core';
import { Realty } from '../model/Realty';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { Pageable } from '../model/Pageable';
import { ParamSet } from '../model/ParamSet';

const apiUrl = 'http://localhost:8080/api/v1/realties';

@Injectable({
  providedIn: 'root'
})
export class RealtyService {

  constructor(private http: HttpClient) { }

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
    return this.http.patch<any>(apiUrl + '/deleted/' + id, '');
  }

  getDeletedRealties(params: ParamSet): Observable<Pageable> {
    return this.http.get<Pageable>(apiUrl + '/deleted', {params: this.getParams(params)});
  }

  getMyRealties(params: ParamSet): Observable<Pageable> {
    return this.http.get<Pageable>(apiUrl + '/my', {params: this.getParams(params)});
  }

  getAllRealties(params: ParamSet): Observable<Pageable> {
    return this.http.get<Pageable>(apiUrl, {params: this.getParams(params)});
  }

  private getParams(params: ParamSet) {
    return new HttpParams()
    .set("sortBy", params.value)
    .set("sortWith", params.with)
    .set("pageNum", params.pageNum);
  }

}
