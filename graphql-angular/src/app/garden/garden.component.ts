import {Component, Input, OnInit} from '@angular/core';
import {Garden} from "../model/Garden";
import {GardenService} from "../garden.service";
import {PlantService} from "../plant.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-garden',
  templateUrl: './garden.component.html',
  styleUrls: ['./garden.component.css']
})
export class GardenComponent implements OnInit {

  @Input()
  garden: Garden;

  showAddPlant = false;
  addPlantForm: FormGroup;

  constructor(private gardenService: GardenService,
              private plantService: PlantService,
              private formBuilder: FormBuilder) {
  }

  ngOnInit() {
    this.addPlantForm = this.formBuilder.group({
      plantType: [null, Validators.required]
    })
  }

  isAddPlantButtonDisabled(): boolean {
    return this.addPlantForm.invalid;
  }

  addPlant() {
    console.log(this.addPlantForm.get('plantType').value);
    this.plantService.createPlant(this.garden.id, this.addPlantForm.get('plantType').value);
    this.showAddPlant = false;
    this.addPlantForm.reset();
  }

}
