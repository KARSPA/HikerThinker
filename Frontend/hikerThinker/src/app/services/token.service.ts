import { Injectable } from '@angular/core';
import { UserInfos } from '../interfaces/userInfos';

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  constructor() { }


  saveUserInfos(userInfos : UserInfos) : void {
    localStorage.setItem('currentUser', JSON.stringify(userInfos));
  }
}
