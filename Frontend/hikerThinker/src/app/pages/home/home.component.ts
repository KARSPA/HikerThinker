import { Component, inject, OnInit } from '@angular/core';
import { TokenService } from '../../services/token.service';
import { NavigationEnd, Router, RouterLink } from '@angular/router';
import { UserInfos } from '../../interfaces/userInfos';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './home.component.html'
})
export class HomeComponent implements OnInit{

  private tokenService : TokenService = inject(TokenService);
  private router : Router = inject(Router);

  isLogged : boolean = false;
  userInfos : UserInfos|null = null;


  //On s'abonne aux évènements de routing pour vérifier si on est connecté ou non.
  ngOnInit(): void {
    this.isLogged = this.tokenService.isLogged();
    this.userInfos = this.tokenService.getUserInfos();
    
    this.router.events.subscribe((event)=>{
      if(event instanceof NavigationEnd){
        this.isLogged = this.tokenService.isLogged();
        this.userInfos = this.tokenService.getUserInfos();
      }
    })
  }

}
