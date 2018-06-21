import {Plant} from "./Plant";

export interface Garden {
  id: number;
  title: string;
  description: string;
  plants: Plant[]
}
