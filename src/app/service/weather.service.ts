import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Weather } from '../model/weather';

@Injectable({
  providedIn: 'root'
})
export class WeatherService {

  constructor(private http: HttpClient) { }

  apiUrl: string = 'http://localhost:8080/api/search';

  getWeather(q: string) : Observable<Weather[]> {

    const params = new HttpParams()
      .set('q', q);
    const headers = new HttpHeaders()
      .set('content-type', 'application/json');

    return this.http.get<Weather[]>(this.apiUrl, {params, headers});
  }
}
