import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Patienter} from '../model/patienter';

const API_URL = `${environment.apiUrl}`;

@Injectable({
  providedIn: 'root'
})
export class PatienterService {

  constructor(private http: HttpClient) {
  }

  getAll(): Observable<Patienter[]> {
    return this.http.get<Patienter[]>(API_URL + '/api/v1/patienter');
  }

  findById(id: number): Observable<Patienter> {
    return this.http.get<Patienter>(`${API_URL}/api/v1/patienter/${id}`);
  }

  update(id: number, patienter: Patienter): Observable<Patienter> {
    return this.http.put<Patienter>(`${API_URL}/api/v1/patienter/${id}`, patienter);
  }
}
