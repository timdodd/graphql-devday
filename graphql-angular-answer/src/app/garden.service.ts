import {Injectable} from '@angular/core';
import {Garden} from "./model/Garden";
import {Observable} from "rxjs/internal/Observable";
import {Apollo} from "apollo-angular";
import gql from 'graphql-tag';
import {map} from "rxjs/operators";

export const FIND_ALL_GARDENS_QUERY = gql`
  query findAllGardens {
    gardens {
      id
      title
      description
      plants {
        id
        plantType
        quantity
      }
    }
  }
`;

@Injectable({
  providedIn: 'root'
})
export class GardenService {

  constructor(private apollo: Apollo) {
  }

  public getGardens(): Observable<Garden[]> {
    return this.apollo.watchQuery<{ gardens: Garden[] }>({
      query: FIND_ALL_GARDENS_QUERY
    }).valueChanges.pipe(map(response => {
      console.log(response);
      return response.data.gardens
    }));
  }
}
