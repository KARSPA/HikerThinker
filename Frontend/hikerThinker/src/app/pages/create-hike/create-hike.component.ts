import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-create-hike',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './create-hike.component.html'
})
export class CreateHikeComponent {

  // distanceRegPattern : string = "/^(([1-9]{1}[\d]{0,5}){1}(?:[\.\,]\d{1,2})?)$/"; // Nombre décimal jusqu'au centième max, séparateur décimal '.' ou ',', plus petit que 999 999.99

  // wholeNumberRegPattern : string = "/^([1-9]{1}[\d]{1,5}|0)$/"; //Pour dénivelé positif et négatif (jusqu'à 999 999) ET 0


  hikeForm : FormGroup = new FormGroup({
    title : new FormControl('',[Validators.required]),
    distance : new FormControl('',[Validators.required, Validators.min(0), Validators.max(100000)]), //On formattera le résultat plus tard.
    negative : new FormControl('',[Validators.required, Validators.min(0), Validators.max(100000)]),
    positive : new FormControl('',[Validators.required, Validators.min(0), Validators.max(100000)]),
    duration : new FormControl('',[Validators.required, Validators.min(0), Validators.max(100000)]),
    date : new FormControl(new Date())
  })


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

    }

    return errorMessage;
  }
}
