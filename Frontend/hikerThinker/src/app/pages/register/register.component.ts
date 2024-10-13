import { Component, inject } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { RegisterCredentials } from '../../interfaces/register-credentials';
import { AuthService } from '../../services/auth.service';
import { RegisterInfos } from '../../interfaces/register-infos';
import { Router } from '@angular/router';
import { samePasswordValidator } from '../../_helpers/validators/same-password';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './register.component.html'
})
export class RegisterComponent {

  private authService : AuthService = inject(AuthService)
  private router : Router = inject(Router);

  registerHttpError : string = '';

  isSubmitClicked : boolean = false;

  registerForm: FormGroup = new FormGroup({
    username : new FormControl('',[Validators.required, Validators.minLength(3)]),
    email : new FormControl('',[Validators.required, Validators.email]),
    password : new FormControl('',Validators.required),
    confirmPassword : new FormControl('',Validators.required)
  },{validators : samePasswordValidator})


  get username(){
    return this.registerForm.get('username');
  }
  get email(){
    return this.registerForm.get('email');
  }
  get password(){
    return this.registerForm.get('password');
  }
  get confirmPassword(){
    return this.registerForm.get('confirmPassword');
  }

  onSubmit() : void{
    console.log(this.registerForm);

    if(this.registerForm.invalid){
      this.toggleErrorMessages();
    }

    if(this.registerForm.valid){
      this.isSubmitClicked = false;

      const registerCredentials : RegisterCredentials = this.registerForm.value;

      console.log(registerCredentials);

      //Check si les mots de passes sont égaux.
      if(registerCredentials.password === registerCredentials.confirmPassword) {
        
        this.authService.register(registerCredentials).subscribe({
          next: (value : RegisterInfos) => {
            console.log(value);
            this.router.navigate(['login']);
          },
          error : error => {
            console.log(error);
            this.registerHttpError = "Erreur lors de l'enregistrement. Pseudo ou email non disponible."
          }
        })
      }
    }
  }



  toggleErrorMessages() : void{
    this.isSubmitClicked = true;
  }

  getErrorMessage(reason : string, length : number = 0) : string{

    let errorMessage : string = '';

    switch(reason){
      case 'required':
        errorMessage = 'Champ requis.';
        break;
      case 'minlength':
        errorMessage = `Au moins ${length} caractères.`;
        break;
      case 'email':
        errorMessage = 'Entrez un email valide.';
        break;
      case 'notSamePassword':
        errorMessage = 'Les deux mots de passe ne sont pas égaux.';
        break;

    }

    return errorMessage;
  }

}
