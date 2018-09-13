import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { map, tap } from 'rxjs/operators';
import { User } from '../models/user';
import { Player } from '../models/player';

@Injectable({
  providedIn: 'root'
})
export class PlayService {

  private playin = new BehaviorSubject<boolean>(false);
  //private currPlayer = new BehaviorSubject(JSON.parse(localStorage.getItem("currentUser")));
  private ready = new BehaviorSubject<boolean>(false);

  get isPlaying() {
    return this.playin.asObservable();
  }

  get isReadyToPlay() {
    return this.ready.asObservable();
  }

  constructor(private http: HttpClient) { }

  startGame() {
    this.playin.next(true);
    //sessionStorage
  }

  playersInGame(u: User) {
    this.ready.next(true);
    console.log("Sending User: " + u);
    /*
    return this.http.post<any>('/Blackjack/play/joinRoom',u).pipe(map(players {}=> {
      console.log(list);
      if(list){
        localStorage.setItem('currentPlayer', JSON.stringify(list[0]));
        localStorage.setItem('currentDealer',JSON.stringify(list[1]));
        //this.currPlayer.next(list);
        console.log(list[0]);
        console.log(list[1]);
      }
      return list;
    }));
    */
    return this.http.post('/Blackjack/play/joinRoom',u).pipe(tap(players => console.log(players)));
  }
}
