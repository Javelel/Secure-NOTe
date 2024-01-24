import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "../models/user";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpClient: HttpClient) { }
  private baseUrl = 'http://localhost:8080/api/auth';

  login(user: User) {
    return this.httpClient.post<any>(`${this.baseUrl}/login`, user);
  }
}
