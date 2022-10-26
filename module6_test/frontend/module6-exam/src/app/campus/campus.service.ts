import {Injectable} from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Campus} from "./campus";

const API_URL = `${environment.apiUrl}`;

@Injectable({
  providedIn: 'root'
})
export class CampusService {

  constructor(private http: HttpClient) { }

  findAll(page: number, campusName: string, employeeName: string, size: number): Observable<Campus[]> {
    return this.http.get<Campus[]>(API_URL + '/api/campus/list?page=' + page + '&campusName=' + campusName + '&employeeName=' + employeeName + '&size=' + size);
  }

  findById(id: number): Observable<Campus> {
    return this.http.get<Campus>(`${API_URL}/api/campus/${id}`);
  }

  delete(id: number): Observable<Campus> {
    return this.http.delete<Campus>(`${API_URL}/api/campus/${id}`);
  }

  save(campus): Observable<Campus> {
    return this.http.post<Campus>(`${API_URL}/api/campus/create`, campus);
  }

  checkName(name: string): Observable<string> {
    return this.http.get<string>(API_URL + '/checkName/' + name);
  }
}
