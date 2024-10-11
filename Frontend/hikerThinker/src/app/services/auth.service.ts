import { inject, Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Credentials } from '../interfaces/credentials';
import { UserInfos } from '../interfaces/userInfos';
import { RegisterCredentials } from '../interfaces/register-credentials';
import { RegisterInfos } from '../interfaces/register-infos';
import { TokenService } from './token.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private LOGIN_URL : string = 'http://localhost:8000/auth/login';
  private REGISTER_URL : string = 'http://localhost:8000/auth/register';

  private httpClient : HttpClient = inject(HttpClient);
  private tokenService : TokenService = inject(TokenService);

  //Des observables auquels on pourra s'abonner dans l'application pour récupérer les informations de connexion.
  //On initialise avec ce qu'il y a dans le localStorage.
  //On mettra à jour lors d'un succès de connexion.
  private isLoggedSubject : BehaviorSubject<boolean> = new BehaviorSubject<boolean>(this.tokenService.isLogged());
  private userInfosSubject : BehaviorSubject<UserInfos|null> = new BehaviorSubject<UserInfos|null>(this.tokenService.getUserInfos());



  login(credentials : Credentials) : Observable<UserInfos>{
    return this.httpClient.post<UserInfos>(this.LOGIN_URL,credentials);
  }

  //Méthode pour stocké les infos ET mettre à jour nos observables !
  handleLoginSuccess(userInfos : UserInfos) : void{
    this.tokenService.saveUserInfos(userInfos);

    this.isLoggedSubject.next(true);
    this.userInfosSubject.next(userInfos);
  }
  
  register(registerCredentials : RegisterCredentials) : Observable<RegisterInfos>{
    return this.httpClient.post<RegisterInfos>(this.REGISTER_URL, registerCredentials)
  } 
  
  logout(){
    this.tokenService.deleteUserInfos();

    this.isLoggedSubject.next(false);
    this.userInfosSubject.next(null);
  }

  get isLogged() : Observable<boolean>{
    return this.isLoggedSubject.asObservable();
  }

  get userInfos() : Observable<UserInfos | null>{
    return this.userInfosSubject.asObservable();
  }


}
