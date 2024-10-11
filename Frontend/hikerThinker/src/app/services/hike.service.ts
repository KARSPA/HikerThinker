import { inject, Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { UserInfos } from '../interfaces/userInfos';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Hike } from '../interfaces/hike';
import { map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HikeService {

  private ALL_HIKES_URL = 'http://localhost:8000/hikes/';

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
      .pipe(map(data => data.map(apiHike => this.mapToHike(apiHike)))) //Transforme les données réceptionnées pour fiter l'interface 'Hike' et enlève les modèles !
  }



  mapToHike(hike : any) : Hike{
    return {
      id : hike.id,
      date : new Date(hike.date),
      distance : hike.distanceInKm,
      days : hike.durationInDays,
      title : hike.hikeTitle,
      negative : hike.negativeVerticalInMeters,
      positive : hike.positiveVerticalInMeters,
      model : hike.model
    }
  }

}
