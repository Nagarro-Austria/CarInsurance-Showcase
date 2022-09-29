import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {Coverage} from "../model/coverage";

@Injectable({
  providedIn: 'root'
})
export class CoverageService {
  coverageURL = 'http://localhost:8080/coverage';

  constructor(private http: HttpClient) {
  }

  public readOptions(): Observable<Coverage[]> {
    /*
    return this.http.get(this.coverageURL);
     */
    return of([
      {id: 1, description: "motor vehicle liability 2022", validFrom: 2022},
      {id: 2, description: "motor vehicle liability 2023", validFrom: 2023}
    ]);
  }
}
