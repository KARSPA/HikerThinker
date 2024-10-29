import { Component, inject, OnInit } from '@angular/core';
import { HikeService } from '../../services/hike.service';
import { Hike } from '../../interfaces/hike';
import { HikeCardComponent } from "../../components/hike-card/hike-card.component";
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-hikes',
  standalone: true,
  imports: [HikeCardComponent, RouterLink],
  templateUrl: './hikes.component.html'
})
export class HikesComponent implements OnInit{

  private hikeService : HikeService = inject(HikeService);


  hikes : Hike[] = [];
  
  ngOnInit(): void {
      this.hikeService.fetchHikes().subscribe({
        next : (data)=>{
          this.hikes = data.sort(this.sortByDateDesc);
          console.log(data, this.hikes)
        },
        error : error => console.log(error)
      })
  }


  private sortByDateDesc(a : Hike, b : Hike){
    let dateA = new Date(a.date).valueOf();
    let dateB = new Date(b.date).valueOf();
    return dateB - dateA;
  }

}
