import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {User} from "../models/user";
import {UserRegister} from "../models/user-register";

@Injectable({
  providedIn: 'root'
})
export class UserService {


  constructor(private httpClient: HttpClient) { }
  private baseUrl = 'http://localhost:8080/api/auth';


  login(user: User) {

    let body = new URLSearchParams();
    body.set('email', user.username);
    body.set('password', user.password);
    const options = {
      headers: new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded'),
      withCredentials: true
    };

    return this.httpClient
      .post('http://localhost:8080/api/auth/login', body.toString(), options);
  }

  register(userRegister: UserRegister) {
    const options = {
      withCredentials: true
    }
    return this.httpClient.post(`${this.baseUrl}/register`, userRegister, options);
  }
}
