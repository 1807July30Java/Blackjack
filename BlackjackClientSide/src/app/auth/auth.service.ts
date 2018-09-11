import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  login(username: string, password: string){
    return this.http.post<any>('${config.apiUrl}/checkAuthentication',{ username: username, password: password}).pipe(map(user=> {
      if(user){
        localStorage.setItem('currentUser', JSON.stringify(user));
      }
      return user;
    }));
  }
  logout(){
    localStorage.removeItem('currentUser')
  }

}
