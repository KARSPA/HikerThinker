import { Injectable } from '@angular/core';
import { Hike } from '../interfaces/hike';
import { Equipment } from '../interfaces/equipment';

@Injectable({
  providedIn: 'root'
})
export class FiltersService {

  constructor() { }


  getTotalWeight(hike : Hike|null) : number{
    if(hike === null || hike.equipments === undefined) return 0;    
    return hike.equipments?.reduce((acc, cur)=> acc + cur.weight, 0)/1000 ;
  }


  getPartialWeight(equipments : Equipment[]) : number{
    return equipments.reduce((acc, cur)=> acc + cur.weight, 0)/1000;
  }


  filterByCategory(equipments : Equipment[] = []) : Map<string, Equipment[]>{
    let globalHashmap = new Map<string, Equipment[]>();

    equipments.forEach(equipment => {
      let categoryName = equipment.category.name;
      if(!globalHashmap.has(categoryName)){
        globalHashmap.set(categoryName, [equipment]);
      }else{
        let categoryArr = globalHashmap.get(categoryName);

        if(categoryArr !== undefined){ //Pour éviter l'erreur si undefined (ce qui ne peut pas arriver mais TS est ch)
          categoryArr.push(equipment);
          globalHashmap.set(categoryName, categoryArr);
        }

      }
    })


    return globalHashmap;


  }


}
