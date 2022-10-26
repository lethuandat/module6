import {Injectable} from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Placement} from "./placement";

const API_URL = `${environment.apiUrl}`;

@Injectable({
  providedIn: 'root'
})
export class PlacementService {

  constructor(private http: HttpClient) { }

  findById(id: number): Observable<Placement> {
    return this.http.get<Placement>(`${API_URL}/api/placement/${id}`);
  }

  getAll(): Observable<Placement[]> {
    return this.http.get<Placement[]>(API_URL + '/api/placement/list');
  }
}
