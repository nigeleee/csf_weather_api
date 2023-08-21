import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { Subscription } from 'rxjs';
import { WeatherService } from '../service/weather.service';
import { Weather } from '../model/weather';

@Component({
  selector: 'app-weather',
  templateUrl: './weather.component.html',
  styleUrls: ['./weather.component.css']
})

export class WeatherComponent implements OnInit{
  constructor(private fb: FormBuilder, private service : WeatherService) {}

  form!: FormGroup;
  input! : string;
  sub$! : Subscription;
  weather!: Weather;

  ngOnInit(): void {
    this.form = this.initialiseForm();
  }

  initialiseForm() {
    return this.fb.group({
      input: new FormControl('')
    });
  }

  search() {
    this.sub$ = this.service.getWeather(this.form.value.input).subscribe((data:any) => {
    console.log(data);
    this.weather = data;
    })
  }
}
