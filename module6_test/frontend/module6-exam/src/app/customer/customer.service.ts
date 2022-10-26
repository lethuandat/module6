import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Customer} from "./customer";
import {environment} from "../../environments/environment";

const API_URL = `${environment.apiUrl}`;

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(private http: HttpClient) {
  }

  findAll(page: number, keyword: string, size: number): Observable<Customer[]> {
    return this.http.get<Customer[]>(API_URL + '/api/v1/customer/list?page=' + page + '&keyword=' + keyword + '&size=' + size);
  }

  findById(id: number): Observable<Customer> {
    return this.http.get<Customer>(`${API_URL}/api/v1/customer/${id}`);
  }

  delete(id: number): Observable<Customer> {
    return this.http.delete<Customer>(`${API_URL}/api/v1/customer/${id}`);
  }

  save(customer): Observable<Customer> {
    return this.http.post<Customer>(`${API_URL}/api/v1/customer/create`, customer);
  }

  update(id: number, customer: Customer): Observable<Customer> {
    return this.http.put<Customer>(`${API_URL}/api/v1/customer/${id}`, customer);
  }
}
