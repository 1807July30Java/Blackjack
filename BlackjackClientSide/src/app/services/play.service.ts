import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class PlayService {

  private playin = new BehaviorSubject<boolean>(false);
  //private currPlayer = new BehaviorSubject(JSON.parse(localStorage.getItem("currentUser")));
  private ready = new BehaviorSubject<boolean>(false);

  get isPlaying(){
    return this.playin.asObservable();
  }

  get isReadyToPlay(){
    return this.ready.asObservable();
  }

  constructor(private http: HttpClient) { }

  startGame(){
    this.playin.next(true);
    //sessionStorage
  }

  playersInGame(u:User){
    this.ready.next(true);
    console.log(u);
    return this.http.post<any>('/Blackjack/play/room',u).pipe(map(list=> {
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

  }
}
