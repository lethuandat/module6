import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Patient} from '../model/patient';


const API_URL = `${environment.apiUrl}`;

@Injectable({
  providedIn: 'root'
})
export class PatientService {
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private http: HttpClient) {
  }

  getAll(page: number): Observable<Patient[]> {
    return this.http.get<Patient[]>(API_URL + '/api/v1/patient/list?page=' + page);
  }

  getAllNoPagination(): Observable<Patient[]> {
    return this.http.get<Patient[]>(API_URL + '/api/v1/patient/list-no-pagination');
  }

  save(patient: Patient): Observable<Patient> {
    return this.http.post<Patient>(API_URL + '/api/v1/patient', JSON.stringify(patient), this.httpOptions);
  }

  findById(id: number): Observable<Patient> {
    return this.http.get<Patient>(`${API_URL}/api/v1/patient/${id}`);
  }

  update(id: number, patient: Patient): Observable<Patient> {
    return this.http.put<Patient>(`${API_URL}/api/v1/patient/${id}`, patient);
  }

  delete(id: number): Observable<Patient> {
    return this.http.delete<Patient>(`${API_URL}/api/v1/patient/${id}`);
  }

  find(page: number, doctor: string, name: string, reason: string, method: string): Observable<Patient[]> {
    return this.http.get<Patient[]>(API_URL + '/api/v1/patient/search?page=' + page + '&doctor=' + doctor + '&name=' + name + '&reason=' + reason + '&method=' + method);
  }
}
