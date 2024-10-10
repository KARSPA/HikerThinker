import { inject, Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Credentials } from '../interfaces/credentials';
import { UserInfos } from '../interfaces/userInfos';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private LOGIN_URL : string = 'http://localhost:8000/auth/login';

  private httpClient : HttpClient = inject(HttpClient);

  constructor() {}


  login(credentials : Credentials) : Observable<UserInfos>{
    return this.httpClient.post<UserInfos>(this.LOGIN_URL,credentials);
  }

}
