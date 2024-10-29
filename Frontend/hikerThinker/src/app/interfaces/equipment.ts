import { Category } from "./category";


export interface Equipment{
    equipmentId : number,
    name : string,
    description : string,
    weight : number,
    category : Category
}