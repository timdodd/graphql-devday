import {Component, OnDestroy, OnInit} from '@angular/core';
import {GardenService} from "./garden.service";
import {Garden} from "./model/Garden";
import {Subscription} from "rxjs/internal/Subscription";
import {PlantService} from "./plant.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, OnDestroy {

  gardens: Garden[] = [];
  private gardenSub: Subscription;

  constructor(private gardenService: GardenService) {
  }

  ngOnInit(): void {
    this.gardenSub = this.gardenService.getGardens().subscribe((gardens) => this.gardens = gardens);
  }

  ngOnDestroy(): void {
    this.gardenSub.unsubscribe();
  }


}
