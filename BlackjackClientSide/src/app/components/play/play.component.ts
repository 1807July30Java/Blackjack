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
  
  playing: Observable<boolean>;

  constructor(private router: Router, private playService: PlayService) { }

  ngOnInit() {
    this.playing = this.playService.isPlaying;
    console.log(this.playing);
    console.log("you are "+ this.playing);
  }
  startPlaying(){
    this.playService.startGame();
  }
}
