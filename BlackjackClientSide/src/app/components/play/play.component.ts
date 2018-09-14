import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, BehaviorSubject } from 'rxjs';
import { PlayService } from '../../services/play.service';
import { Card } from '../../models/card';

@Component({
  selector: 'app-play',
  templateUrl: './play.component.html',
  styleUrls: ['./play.component.css']
})
export class PlayComponent implements OnInit {

  loading = new BehaviorSubject<boolean>(false);
  readyToPlay: Observable<boolean>;
  playing: Observable<boolean>;
  playerHand: Observable<Array<Card>>;
  dealerHand: Observable<Array<Card>>;

  constructor(private router: Router, private playService: PlayService) { }

  ngOnInit() {
    this.playing = this.playService.isPlaying;
    this.readyToPlay = this.playService.isReadyToPlay;
  }
  startPlaying() {
    this.playService.startGame(JSON.parse(localStorage.getItem("currentplayer"))).subscribe(
      data => {
        this.playerHand = this.playService.yourHand;
        this.dealerHand = this.playService.theirHand;
      },
      error => {
        //This is where i'd put my alert service... IF I HAD ONE!
      });
  }
  readyUp() {
    console.log("about to run Players in game");
    this.loading = new BehaviorSubject<boolean>(true);
    this.playService.playersInGame(JSON.parse(localStorage.getItem("currentUser"))).subscribe(
      data => {
        this.loading = new BehaviorSubject<boolean>(false);
      },
      error => {
        //This is where i'd put my alert service... IF I HAD ONE!

      });
  }
}
