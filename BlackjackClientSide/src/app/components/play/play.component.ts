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
  playahHandValue: number;
  dealahHandValue: number;
  winner: Observable<string>;

  constructor(private router: Router, private playService: PlayService) { }

  ngOnInit() {
    this.playing = this.playService.isPlaying;
    this.readyToPlay = this.playService.isReadyToPlay;
  }
  startPlaying() {
    console.log("start Playing");
    this.loading = new BehaviorSubject<boolean>(true);
    this.playService.dealPlayer(JSON.parse(localStorage.getItem("currentplayer"))).subscribe(
      data => {
        console.log("this is what was logged as playerHand=" + this.playerHand);
        this.playerHand = this.playService.yourHand;
        this.playService.dealDealer(JSON.parse(localStorage.getItem("currentdealer"))).subscribe(
          data => {
            console.log("this is what was logged as playerHand=" + this.dealerHand);
            this.dealerHand = this.playService.theirHand;
            this.playService.yourHandValue(JSON.parse(localStorage.getItem("currentplayer"))).subscribe(
              data => {
                this.playahHandValue = this.playService.yaHandVal;
                console.log(this.playahHandValue);
              }, error => {

              });
          },
          error => {
            //This is where i'd put my alert service... IF I HAD ONE!
          });
      },
      error => {
        //This is where i'd put my alert service... IF I HAD ONE!
      });

    this.loading = new BehaviorSubject<boolean>(false);
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
  hit() {
    if (this.playahHandValue <= 21) {
      console.log("hitting");
      this.playService.showMeTheMoney(JSON.parse(localStorage.getItem("currentplayer"))).subscribe(
        data => {
          this.playerHand = this.playService.yourHand;
          //Get hand value
          this.playService.yourHandValue(JSON.parse(localStorage.getItem("currentplayer"))).subscribe(
            data => {
              this.playahHandValue = this.playService.yaHandVal;
              console.log(this.playahHandValue);
              if (this.playahHandValue > 21) {
                this.playService.endGame(JSON.parse(localStorage.getItem("currentdealer"))).subscribe(
                  data => {
                    this.winner = this.playService.winnah;
                    this.endThatGame();
                  }, error => {

                  });
              }
            }, error => {

            });
        },
        error => {
          //This is where i'd put my alert service... IF I HAD ONE!
        });
    }
  }
  stay() {
    console.log("staying");
    this.playService.stayHereBoyo(JSON.parse(localStorage.getItem("currentdealer"))).subscribe(
      data => {
        this.dealerHand = this.playService.theirHand;
        //get their hand value
        this.playService.theirHandValue(JSON.parse(localStorage.getItem("currentdealer"))).subscribe(
          data => {
            this.dealahHandValue = this.playService.theyHandVal;
            //Dealer busts (you stayed so you're under 21)
            if (this.dealahHandValue > 21) {
              this.playService.endGame(JSON.parse(localStorage.getItem("currentplayer"))).subscribe(
                data => {
                  this.winner = this.playService.winnah;
                  this.endThatGame();
                }, error => {

                });
            } else {
              if (this.dealahHandValue > this.playahHandValue) {
                this.playService.endGame(JSON.parse(localStorage.getItem("currentdealer"))).subscribe(
                  data => {
                    this.winner = this.playService.winnah;
                    this.endThatGame();
                  }, error => {

                  });
              } else {
                this.playService.endGame(JSON.parse(localStorage.getItem("currentplayer"))).subscribe(
                  data => {
                    this.winner = this.playService.winnah;
                    this.endThatGame();
                  }, error => {

                  });
              }
            }
          }, error => {

          });
      },
      error => {
        //This is where i'd put my alert service... IF I HAD ONE!
      });
  }
  endThatGame(){
    console.log("Ending Game");
    this.playing = this.playService.isPlaying;
    this.readyToPlay = this.playService.isReadyToPlay;
  }
  resetti() {
    this.playService.reset();
  }
}
