import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthService } from '../../services/auth.service';


@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  title = "Blackjack Online";

  isLoggedIn: Observable<boolean>;


  constructor(private authservice: AuthService) { }

  ngOnInit() {
    this.isLoggedIn = this.authservice.isLoggedIn;
  }

  logoutButt(){
    this.authservice.logout();
  }
}
