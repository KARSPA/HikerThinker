import { Component, inject, Input } from '@angular/core';
import { Hike } from '../../interfaces/hike';
import { DatePipe } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-hike-card',
  standalone: true,
  imports: [DatePipe],
  templateUrl: './hike-card.component.html'
})
export class HikeCardComponent {

  @Input() hike : Hike | null = null;

  private router : Router = inject(Router);


  goToDetails(){
    console.log('click ok')
    this.router.navigate(['/hike/', this.hike?.id])
  }

}
