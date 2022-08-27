import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Patient} from '../model/patient';


const API_URL = `${environment.apiUrl}`;

@Injectable({
  providedIn: 'root'
})
export class PatientService {

  constructor(private http: HttpClient) {
  }

  getAll(): Observable<Patient[]> {
    return this.http.get<Patient[]>(API_URL + '/api/v1/patient');
  }

  save(patient: Patient): Observable<Patient> {
    return this.http.post<Patient>(API_URL + '/api/v1/patient', patient);
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

  find(keyword: string, keyword2: string): Observable<Patient[]> {
    return this.http.get<Patient[]>(API_URL + '/patient?doctor_like=' + keyword + '&patienter.name_like=' + keyword2);
  }
}
