import { Component, Input } from '@angular/core';
import { Equipment } from '../../interfaces/equipment';

@Component({
  selector: 'app-equipment-card',
  standalone: true,
  imports: [],
  templateUrl: './equipment-card.component.html'
})
export class EquipmentCardComponent {

  @Input() equipment : Equipment|null = null;

}
