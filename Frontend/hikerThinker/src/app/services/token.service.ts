import { Injectable } from '@angular/core';
import { UserInfos } from '../interfaces/userInfos';

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  saveUserInfos(userInfos : UserInfos) : void {
    localStorage.setItem('currentUser', JSON.stringify(userInfos));
  }

  deleteUserInfos() : void{
    localStorage.removeItem('currentUser');
  }

  isLogged() : boolean{
    return !!localStorage.getItem('currentUser');
  }

  getUserInfos() : UserInfos|null{
    const userInfosSTRING = localStorage.getItem("currentUser");
    return userInfosSTRING ? JSON.parse(userInfosSTRING) : null;
  }
}
