import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { map, tap } from 'rxjs/operators';
import { User } from '../models/user';
import { Player } from '../models/player';
import { Card } from '../models/card';


@Injectable({
  providedIn: 'root'
})
export class PlayService {

  private playin = new BehaviorSubject<boolean>(false);
  //private currPlayer = new BehaviorSubject(JSON.parse(localStorage.getItem("currentUser")));
  private ready = new BehaviorSubject<boolean>(false);

  private playerHand = new BehaviorSubject<Array<Card>>([]);
  private dealerHand = new BehaviorSubject<Array<Card>>([]);

  get isPlaying() {
    return this.playin.asObservable();
  }

  get isReadyToPlay() {
    return this.ready.asObservable();
  }

  get yourHand() {
    return this.playerHand.asObservable();
  }
  get theirHand() {
    return this.dealerHand.asObservable();
  }

  constructor(private http: HttpClient) { }

  dealPlayer(player: Player) {
    console.log("sending player id =" + player.id);
    this.playin.next(true);
    return this.http.post<any>('/Blackjack/play/dealPlayerCards', player).pipe(map(user => {
      if (user) {
        this.playerHand.next(user);
      }
      return user;
    }));
    //sessionStorage
  }
  dealDealer(player: Player) {
    console.log("sending player id =" + player.id);
    this.playin.next(true);
    return this.http.post<any>('/Blackjack/play/dealDealerCards', player).pipe(map(user => {
      if (user) {
        this.dealerHand.next(user);
      }
      return user;
    }));
    //sessionStorage
  }

  playersInGame(u: User) {
    this.ready.next(true);
    console.log("Sending User: " + u.firstName);
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
    return this.http.post<any>('/Blackjack/play/joinRoom', u).pipe(map(user => {
      if (user) {
        console.log(user[0]);
        console.log(user[1]);
        localStorage.setItem('currentplayer', JSON.stringify(user[0]));
        localStorage.setItem('currentdealer', JSON.stringify(user[1]));
      }
      console.log(user);
      return user;
    }));
  }

  showMeTheMoney(player: Player) {
    let hand = this.playerHand.getValue();
    return this.http.post<any>('/Blackjack/play/hit', player).pipe(map(user => {
      if (user) {
        hand.push(user);
        this.playerHand.next(hand);
      }
      return user;
    }));
  }

  stayHereBoyo(player: Player) {
    let hand = this.dealerHand.getValue();
    console.log(player.id);
    return this.http.post<any>('/Blackjack/play/stay', player).pipe(map(user => {
      if (user) {
        console.log("new cards ="+user);
        hand = hand.concat(user);
        //hand.push(user);
        this.dealerHand.next(hand);
        console.log("dealerHand is now "+ this.dealerHand.getValue());

      }
      return user;
    }));
  }

}
