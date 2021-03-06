import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { BehaviorSubject } from 'rxjs';
import { Account } from '../models/account';
import { User } from '../models/user';


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private loggedIn = (localStorage.getItem("currentUser"))? new BehaviorSubject<boolean>(true) :new BehaviorSubject<boolean>(false);
  private currentUser = new BehaviorSubject(JSON.parse(localStorage.getItem("currentUser")));

  get isLoggedIn() {
    return this.loggedIn.asObservable();
  }

  get whoseCurrentUser(){
    return this.currentUser.asObservable();
  }

  constructor(private http: HttpClient) { }

  login(account:Account){
    console.log("Account = "+ account);
    return this.http.post<any>('/Blackjack/checkAuthentication',account).pipe(map(user=> {
      if(user){
        this.loggedIn.next(true);
        localStorage.setItem('currentUser', JSON.stringify(user));
        this.currentUser.next(user);
      } 
      console.log(user);
      return user;
    }));
  }
  logout(){
    this.loggedIn.next(false);
    localStorage.removeItem('currentUser');
    this.currentUser.next(null);
  }

}
