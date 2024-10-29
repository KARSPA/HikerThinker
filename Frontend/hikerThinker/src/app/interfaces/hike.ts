import { Equipment } from "./equipment";

export interface Hike {
    id : number|null,
    title : string,
    distance : number,
    negative : number,
    positive : number,
    date : Date,
    duration : number,
    model : boolean,
    equipments? : Equipment[]
}
