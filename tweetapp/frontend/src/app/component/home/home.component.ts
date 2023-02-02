import { Component } from '@angular/core';
import { HomeService } from 'src/app/service/home/home.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent  {

  constructor(private homeService:HomeService){}

  onRegister(){
    this.homeService.registerUser();
  }

  onLogin(){
    this.homeService.loginUser();
  }

}
