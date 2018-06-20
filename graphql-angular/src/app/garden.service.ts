import {Injectable} from '@angular/core';
import {Garden} from "./model/Garden";
import {Observable} from "rxjs/internal/Observable";
import {EMPTY} from "rxjs/internal/Observable/empty";

@Injectable({
  providedIn: 'root'
})
export class GardenService {

  constructor() { }

  public getGardens(): Observable<Garden[]> {
    return EMPTY;
  }
}
