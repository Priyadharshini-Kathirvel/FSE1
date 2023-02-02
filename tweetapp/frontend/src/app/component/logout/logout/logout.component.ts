import { Component } from '@angular/core';
import { LoginService } from 'src/app/service/login/login.service';
import { LogoutService } from 'src/app/service/logout/logout.service';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent {
  isLoggedIn:boolean

  constructor(
    private loginService: LoginService,
    private logoutService:LogoutService
  ){}

  onLogout(){
    this.loginService.loggedinStateSubject.next(false);
    this.logoutService.onLogout().subscribe({
      next : (response) => console.log(response),
      error: (error) => console.error(error)
      
    })
  }
}
