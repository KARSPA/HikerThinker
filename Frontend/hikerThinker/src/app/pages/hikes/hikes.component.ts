import { Component, inject, OnInit } from '@angular/core';
import { HikeService } from '../../services/hike.service';
import { Hike } from '../../interfaces/hike';
import { HikeCardComponent } from "../../components/hike-card/hike-card.component";

@Component({
  selector: 'app-hikes',
  standalone: true,
  imports: [HikeCardComponent],
  templateUrl: './hikes.component.html'
})
export class HikesComponent implements OnInit{

  private hikeService : HikeService = inject(HikeService);


  hikes : Hike[] = [];
  
  ngOnInit(): void {
      this.hikeService.fetchHikes().subscribe({
        next : (data)=>{
          this.hikes = data;
          console.log(data, this.hikes)
        },
        error : error => console.log(error)
      })
  }

}
