import { Component, inject } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service.service';
import { Credentials } from '../interfaces/credentials';
import { UserInfos } from '../interfaces/userInfos';
import { TokenService } from '../services/token.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  private authService: AuthService = inject(AuthService)
  private tokenService: TokenService = inject(TokenService)
  private router: Router = inject(Router)

  loginForm: FormGroup = new FormGroup({
    username: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required)
  })

  onSubmit(): void {
    console.log("dans onSubmit")
    if(this.loginForm.valid) {
      // check if exist
      const credentials : Credentials = this.loginForm.value
      console.log(credentials)

      // Todo Persist in SL
      this.authService.login(credentials).subscribe({
        next: (value : UserInfos) => {
          console.log(value);
          this.tokenService.saveUserInfos(value)
        },
        error : error => console.error()
      })
    }
  }

  
  // getErrorMessages(type: string): string {
  //   if (this.loginForm.get(type)?.hasError('required')) {
  //     return 'Le champ est obligatoire'
  //   } else if (this.loginForm.get(type)?.hasError('pattern')) {
  //     return 'Ce n\'est pas un email'
  //   }
  //   return ''
  // }

}
