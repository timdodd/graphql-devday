import {Injectable} from '@angular/core';
import {Observable} from "rxjs/internal/Observable";
import {Plant} from "./model/Plant";
import {EMPTY} from "rxjs/internal/observable/empty";

@Injectable({
  providedIn: 'root'
})
export class PlantService {

  constructor() {
  }

  public createPlant(gardenId: number, plantType: string): Observable<Plant> {
    return EMPTY;
  }

  public incrementPlantQuantity(plantId: number): Observable<Plant> {
    return EMPTY;
  }

}
