import { Component, inject, Input, OnInit } from '@angular/core';
import { HikeService } from '../../services/hike.service';
import { Hike } from '../../interfaces/hike';
import { ActivatedRoute } from '@angular/router';
import { DatePipe } from '@angular/common';
import { FiltersService } from '../../services/filters.service';
import { Equipment } from '../../interfaces/equipment';
import { CategoryContainerComponent } from "../../components/category-container/category-container.component";

@Component({
  selector: 'app-hike-details',
  standalone: true,
  imports: [DatePipe, CategoryContainerComponent],
  templateUrl: './hike-details.component.html'
})
export class HikeDetailsComponent implements OnInit{

  private hikeService : HikeService = inject(HikeService);
  private filterService : FiltersService = inject(FiltersService);
 
  constructor(private route : ActivatedRoute){

  }

  hike : Hike|null = null;

  totalWeight = 0;

  groupedEquipments :  Map<string, Equipment[]> = new Map<string, Equipment[]>();

  ngOnInit(): void {
    const hikeId = this.route.snapshot.paramMap.get('hikeId');
    console.log(hikeId);

    if(hikeId !== null){
      this.hikeService.fetchHikeById(hikeId).subscribe({
          next: (value)=>{
            console.log(value);
            this.hike = value;
            this.totalWeight = this.filterService.getTotalWeight(this.hike);

            this.groupedEquipments = this.filterService.filterByCategory(this.hike.equipments);
            console.log(this.groupedEquipments)
          },
          error : (err) => console.log(err)
        })
    }
  }
}
