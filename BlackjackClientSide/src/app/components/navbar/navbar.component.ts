import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthService } from '../../auth/auth.service';


@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  isLoggedIn: boolean;


  constructor(private authservice: AuthService) { }

  ngOnInit() {
    if(localStorage.getItem('currentUser')){
      this.isLoggedIn = true;
    }
    else{
      this.isLoggedIn = false;
    }
  }

}
