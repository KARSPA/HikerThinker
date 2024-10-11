import { Component, inject, OnInit } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { UserInfos } from '../../interfaces/userInfos';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './header.component.html'
})
export class HeaderComponent implements OnInit{

  private authService : AuthService = inject(AuthService);
  private router : Router = inject(Router);

  isLogged : boolean = false;
  userInfos : UserInfos|null = null;

  //On s'abonne aux évènements de routing pour vérifier si on est connecté ou non.
  ngOnInit(): void {
    
    this.authService.isLogged.subscribe(isLogged => {
      this.isLogged = isLogged;
    })

    this.authService.userInfos.subscribe(userInfos => {
      this.userInfos = userInfos;
    })
    
  }

}
