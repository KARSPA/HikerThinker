import { Component, inject, OnInit } from '@angular/core';
import { TokenService } from '../../services/token.service';
import { NavigationEnd, Router, RouterLink } from '@angular/router';
import { UserInfos } from '../../interfaces/userInfos';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './home.component.html'
})
export class HomeComponent implements OnInit{

  private authService : AuthService = inject(AuthService);
  private router : Router = inject(Router);

  isLogged : boolean = false;
  userInfos : UserInfos|null = null;


  //On s'abonne aux attributs observables de AuthService pour savoir si on est connecté ou non.
  ngOnInit(): void {

    this.authService.isLogged.subscribe(isLogged => {
      this.isLogged = isLogged;
    })

    this.authService.userInfos.subscribe(userInfos => {
      this.userInfos = userInfos;
    })
  }

}
