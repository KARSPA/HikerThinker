import { Component, inject, Input, OnInit } from '@angular/core';
import { EquipmentCardComponent } from '../equipment-card/equipment-card.component';
import { FiltersService } from '../../services/filters.service';
import { PercentPipe } from '@angular/common';

@Component({
  selector: 'app-category-container',
  standalone: true,
  imports: [EquipmentCardComponent, PercentPipe],
  templateUrl: './category-container.component.html'
})
export class CategoryContainerComponent implements OnInit{

  @Input() category : any;
  @Input() totalWeight : number = 1;

  private filterService : FiltersService = inject(FiltersService);

  partialWeight : number = 0;

  ngOnInit(): void {
      this.partialWeight = this.filterService.getPartialWeight(this.category[1]);
  }

  getIconPath(categoryName : string) : string{
    let assetPath = '';
    switch(categoryName){
      case 'Couchage':
        assetPath = "/assets/cat-sleep.png";
        break;
      case 'Portage':
        assetPath = "/assets/cat-backpack.png";
        break;
      case 'Santé':
        assetPath = "/assets/cat-health.png";
        break;
      case 'Hygiène':
        assetPath = "/assets/cat-hygiene.png";
        break;
      case 'Accessoires':
        assetPath = "/assets/cat-accessories.png";
        break;
      case 'Électronique':
        assetPath = "/assets/cat-electronics.png";
        break;
      case 'Divers':
        assetPath = "/assets/cat-others.png";
        break;
      default:
        assetPath = "";
        break;
    }
    return assetPath;
  }

}
