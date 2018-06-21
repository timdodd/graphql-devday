import {Injectable} from '@angular/core';
import {Garden} from "./model/Garden";
import {Observable} from "rxjs/internal/Observable";
import {EMPTY} from "rxjs/internal/observable/empty";

@Injectable({
  providedIn: 'root'
})
export class GardenService {

  constructor() {
  }

  public getGardens(): Observable<Garden[]> {
    return EMPTY;
  }
}
