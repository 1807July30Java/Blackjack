import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { BehaviorSubject } from 'rxjs';
import { User } from '../models/user';


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private loggedIn = new BehaviorSubject<boolean>(false);

  get isLoggedIn() {
    return this.loggedIn.asObservable();
  }
  constructor(private http: HttpClient) { }

  login(username: string, password: string){
    return this.http.post<any>('${config.apiUrl}/checkAuthentication',{ username: username, password: password}).pipe(map(user=> {
      if(user){
        this.loggedIn.next(true);
        localStorage.setItem('currentUser', JSON.stringify(user));
      } 
      return user;
    }));
  }
  logout(){
    this.loggedIn.next(false);
    localStorage.removeItem('currentUser')
  }

}
