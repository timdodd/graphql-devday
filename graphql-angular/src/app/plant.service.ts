import {Injectable} from '@angular/core';
import {Apollo} from "apollo-angular";
import gql from "graphql-tag";
import {FIND_ALL_GARDENS_QUERY} from "./garden.service";
import {Garden} from "./model/Garden";

const CREATE_PLANT_MUTATION = gql`
  mutation createPlant($gardenId: ID! $plantType: String! $quantity: Int!) {
    addPlant(gardenId: $gardenId plantType: $plantType quantity: $quantity) {
      id,
      plantType,
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

  public createPlant(gardenId: number, plantType: string): void {
    this.apollo.mutate({
      mutation: CREATE_PLANT_MUTATION,
      variables: {
        gardenId: gardenId,
        plantType: plantType,
        quantity: 0
      },
      update: (proxy, plantData) => {
        const allGardensData: any = proxy.readQuery({query: FIND_ALL_GARDENS_QUERY});
        allGardensData.gardens.find((garden) => garden.id === gardenId).plants.push(plantData.data.addPlant);
        proxy.writeQuery({query: FIND_ALL_GARDENS_QUERY, data: allGardensData})
      }
    }).subscribe(data => console.log('Created plant ', data));
  }

}
