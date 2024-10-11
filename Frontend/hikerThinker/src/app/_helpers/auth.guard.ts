import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { inject } from '@angular/core';

export const authGuard: CanActivateFn = (route, state) => {

    const authService :  AuthService = inject(AuthService);
    const router : Router = inject(Router);


    //Vérifier si l'utilisateur est connecté.
    const isLogged : boolean = authService.isLoggedValue;

    if(isLogged) return true;

    router.navigate(['login']);
    return false;
};
