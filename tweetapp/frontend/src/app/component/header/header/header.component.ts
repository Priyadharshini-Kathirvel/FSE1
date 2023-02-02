import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/service/login/login.service';
import { LogoutComponent } from '../../logout/logout/logout.component';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit{

  isLoggedIn:any;

  constructor(
    private logoutComponent:LogoutComponent,
    private loginService:LoginService,
    private router:Router
  ){}

  ngOnInit(){
    this.loginService.state.subscribe(
      state => this.isLoggedIn=state
    )
  }

  onLogout(){
    this.loginService.loggedinStateSubject.next(false);
    this.router.navigate(['home']);
    alert("User Logged Out Successfully!");
    this.logoutComponent.onLogout();
  }
}
