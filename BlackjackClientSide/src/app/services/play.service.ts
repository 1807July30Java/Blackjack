import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PlayService {

  private playin = new BehaviorSubject<boolean>(false);

  get isPlaying(){
    return this.playin.asObservable();
  }
  constructor(private http: HttpClient) { }

  startGame(){
    this.playin.next(true);
    //sessionStorage
  }
}
