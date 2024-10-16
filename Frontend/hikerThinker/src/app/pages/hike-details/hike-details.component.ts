import { Component, inject, Input, OnInit } from '@angular/core';
import { HikeService } from '../../services/hike.service';
import { Hike } from '../../interfaces/hike';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-hike-details',
  standalone: true,
  imports: [],
  templateUrl: './hike-details.component.html'
})
export class HikeDetailsComponent implements OnInit{

  private hikeService : HikeService = inject(HikeService);
 
  constructor(private route : ActivatedRoute){

  }

  hike : Hike|null = null;


  ngOnInit(): void {
    const hikeId = this.route.snapshot.paramMap.get('hikeId');
    console.log(hikeId);

    if(hikeId !== null){
      this.hikeService.fetchHikeById(hikeId).subscribe({
          next: (value)=>{
            console.log(value);
            this.hike = value;
          },
          error : (err) => console.log(err)
        })
    }
  }
}
