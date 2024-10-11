import { Component, Input } from '@angular/core';
import { Hike } from '../../interfaces/hike';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-hike-card',
  standalone: true,
  imports: [DatePipe],
  templateUrl: './hike-card.component.html'
})
export class HikeCardComponent {

  @Input() hike : Hike | null = null;

}
