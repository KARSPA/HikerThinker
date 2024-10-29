import { inject, Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { UserInfos } from '../interfaces/userInfos';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Hike } from '../interfaces/hike';
import { map, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HikeService {

  private ALL_HIKES_URL = 'http://localhost:8000/hikes/';
  private CREATE_HIKE_URL = 'http://localhost:8000/hikes/create';
  private HIKE_BY_ID = 'http://localhost:8000/hikes/hike?hikeId=';

  private httpClient : HttpClient = inject(HttpClient);

  private authService : AuthService = inject(AuthService);


  userInfos : UserInfos|null = this.authService.userInfosValue;


  fetchHikes(){
    return this.httpClient.get<Hike[]>(this.ALL_HIKES_URL,
      {
        headers : {
          'Authorization' : `Bearer ${this.userInfos?.jwtToken}`
        }
      })
      // .pipe(map(data => data.map(apiHike => this.mapToHike(apiHike)))) //Transforme les données réceptionnées pour fiter l'interface 'Hike' et enlève les modèles !
  }


  fetchHikeById(hikeId : string = '0') : Observable<Hike>{
    const URL_WITH_ID = this.HIKE_BY_ID.concat(hikeId);
    return this.httpClient.get<Hike>(URL_WITH_ID,
      {
        headers : {
          'Authorization' : `Bearer ${this.userInfos?.jwtToken}`
        }
      })
  }


  addAHike(hike : Hike) : Observable<Hike>{
    return this.httpClient.post<Hike>(this.CREATE_HIKE_URL, 
      hike,
      {
        headers : {
          'Authorization' : `Bearer ${this.userInfos?.jwtToken}`
        }
    }
  )
  }



  // mapToHike(hike : any) : Hike{
  //   return {
  //     id : hike.id,
  //     date : new Date(hike.date),
  //     distance : hike.distanceInKm,
  //     duration : hike.durationInDays,
  //     title : hike.hikeTitle,
  //     negative : hike.negativeVerticalInMeters,
  //     positive : hike.positiveVerticalInMeters,
  //     model : hike.model
  //   }
  // }

}
