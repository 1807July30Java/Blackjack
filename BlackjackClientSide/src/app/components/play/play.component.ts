import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { PlayService } from '../../services/play.service';

@Component({
  selector: 'app-play',
  templateUrl: './play.component.html',
  styleUrls: ['./play.component.css']
})
export class PlayComponent implements OnInit {


  readyToPlay: Observable<boolean>;
  playing: Observable<boolean>;

  constructor(private router: Router, private playService: PlayService) { }

  ngOnInit() {
    this.playing = this.playService.isPlaying;
    this.readyToPlay = this.playService.isReadyToPlay;
  }
  startPlaying(){
    this.playService.startGame();
  }
  readyUp(){
    console.log("about to run Players in game");
    this.playService.playersInGame(JSON.parse(localStorage.getItem("currentUser")));
  }
}
