import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Mancala } from '../mancala-model';
import { PlayerCommand } from '../player-command-model';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MancalaService {

  constructor(private http: HttpClient) { }

  private api_url = 'http://localhost:8080/api/v1/game';
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' , 'Access-Control-Allow-Origin' : '*'})
  };

  initGame(): Observable<Mancala> {
    return this.http.post<Mancala>(this.api_url, this.httpOptions);
  }

  resetGame(id): Observable<Mancala> {
    return this.http.post<Mancala>(this.api_url + "/reset?id=" + id, this.httpOptions);
  }

  updateGame(requestBody: PlayerCommand) :  Observable<Mancala> {
    return this.http.post<Mancala>(this.api_url + "/move", requestBody, this.httpOptions);
  }

}
