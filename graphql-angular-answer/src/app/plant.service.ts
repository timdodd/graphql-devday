import {Injectable} from '@angular/core';
import {Apollo} from "apollo-angular";
import gql from "graphql-tag";
import {FIND_ALL_GARDENS_QUERY} from "./garden.service";
import {Observable} from "rxjs/internal/Observable";
import {Plant} from "./model/Plant";
import {map} from "rxjs/operators";

const CREATE_PLANT_MUTATION = gql`
  mutation createPlant($gardenId: ID! $plantType: String! $quantity: Int!) {
    addPlant(gardenId: $gardenId plantType: $plantType quantity: $quantity) {
      id,
      plantType,
      quantity
    }
  }
`;

const INCREMENT_PLANT_QUANTITY_MUTATION = gql`
  mutation incrementPlantQuantity($plantId: ID!) {
    incrementPlantQuantity(plantId: $plantId) {
      id
      plantType
      quantity
    }
  }
`;

@Injectable({
  providedIn: 'root'
})
export class PlantService {

  constructor(private apollo: Apollo) {
  }

  public createPlant(gardenId: number, plantType: string): Observable<Plant> {
    return this.apollo.mutate({
      mutation: CREATE_PLANT_MUTATION,
      variables: {
        gardenId: gardenId,
        plantType: plantType,
        quantity: 0
      },
      update: (proxy, response) => {
        const allGardensData: any = proxy.readQuery({query: FIND_ALL_GARDENS_QUERY});
        allGardensData.gardens.find((garden) => garden.id === gardenId).plants.push(response.data.addPlant);
        proxy.writeQuery({query: FIND_ALL_GARDENS_QUERY, data: allGardensData})
      }
    }).pipe(map(response => response.data.addPlant));
  }

  public incrementPlantQuantity(plantId: number): Observable<Plant> {
    return this.apollo.mutate({
      mutation: INCREMENT_PLANT_QUANTITY_MUTATION,
      variables: {
        plantId: plantId
      }
    }).pipe(map(response => response.data.addPlant));
  }

}
