import { Component, inject } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { HikeService } from '../../services/hike.service';
import { Hike } from '../../interfaces/hike';
import { notBlankValidator } from '../../_helpers/validators/notBlank';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-hike',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './create-hike.component.html'
})
export class CreateHikeComponent {


  private hikeService : HikeService = inject(HikeService);
  private router : Router = inject(Router);

  // distanceRegPattern : string = "/^(([1-9]{1}[\d]{0,5}){1}(?:[\.\,]\d{1,2})?)$/"; // Nombre décimal jusqu'au centième max, séparateur décimal '.' ou ',', plus petit que 999 999.99

  // wholeNumberRegPattern : string = "/^([1-9]{1}[\d]{1,5}|0)$/"; //Pour dénivelé positif et négatif (jusqu'à 999 999) ET 0


  hikeForm : FormGroup = new FormGroup({
    title : new FormControl('',[Validators.required, Validators.minLength(3), Validators.maxLength(50), Validators.pattern("[A-Za-z0-9_\\-\\/\\.\\ ]*"), notBlankValidator()]),
    distance : new FormControl('',[Validators.required, Validators.min(0), Validators.max(100000)]), //On formattera le résultat plus tard.
    negative : new FormControl('',[Validators.required, Validators.min(0), Validators.max(100000)]),
    positive : new FormControl('',[Validators.required, Validators.min(0), Validators.max(100000)]),
    duration : new FormControl('',[Validators.required, Validators.min(0), Validators.max(100000)]),
    date : new FormControl(this.getCurrentDateInHTML())
  })

  hasBeenSubmitted : boolean = false;
  httpErrorMessage : string = "";

  get title(){
    return this.hikeForm.get('title');
  }
  get distance(){
    return this.hikeForm.get('distance');
  }
  get negative(){
    return this.hikeForm.get('negative');
  }
  get positive(){
    return this.hikeForm.get('positive');
  }
  get duration(){
    return this.hikeForm.get('duration');
  }
  get date(){
    return this.hikeForm.get('date');
  }

  onSubmit(): void{
    console.log('Fonctionne !', this.hikeForm);
    console.log(this.date)
    
    if(this.hikeForm.invalid){
      this.hasBeenSubmitted = true;
    }

    if(this.hikeForm.valid){
      const newHike : Hike = {
        id : null,
        title : this.title?.value.trim(),
        distance : this.distance?.value,
        negative : this.negative?.value,
        positive : this.positive?.value,
        date : new Date(this.date?.value),
        duration : this.duration?.value,
        model : false
      } 

      console.log(newHike);
      this.hikeService.addAHike(newHike).subscribe({
        next : (value) => {
          console.log(value) //Si retour avec id et infos ok, alors redirect vers la liste de randos (plus tard le détails de cette randonnée).
          if(value.id !== null){
            this.router.navigate(['hikes'])
          }
        }, 
        error : (err) => {
          console.log(err);
          this.httpErrorMessage = "Erreur lors de l'enregistrement. Une erreur de réseau est survenue. Essayez en vous reconnectant."
        } //Si retour avec id null alors pas créer, afficher erreur de validation
      })
    }
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
      case 'mixlength':
        errorMessage = `Au maximum ${length} caractères.`;
        break;
      case 'min':
        errorMessage = `La valeur minimum est 0.`
        break;
      case 'specialChar':
        errorMessage = `Les seuls caractères spéciaux autorisés sont '/' , '_' , '-' ou '.' .`
        break;
      case 'max':
        errorMessage = `Un peu grand quand même. 100 000 maximum.`
        break;
      case 'notBlank':
        errorMessage = `Ne doit pas être 'vide'.`
        break;

    }

    return errorMessage;
  }

  getCurrentDateInHTML() : string{
    const now = new Date();
    const year = now.getFullYear();
    const month = (now.getMonth()+1).toString().padStart(2, '0'); //Formate janvier -> 01
    const day = now.getDate().toString().padStart(2,'0');
    return `${year}-${month}-${day}`;
  }
}
