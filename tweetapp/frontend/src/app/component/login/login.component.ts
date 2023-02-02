import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/service/authentication/authentication.service';
import { LoginService } from 'src/app/service/login/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  hide = true;
  authToken:string;
  isLoggedIn = false;

  loginForm = new FormGroup({
    'email': new FormControl(null,Validators.required),
    'password': new FormControl(null, Validators.required)
  })

  constructor(
    private authService: AuthenticationService,
    private loginService:LoginService,
    private router:Router
  ){}

   onLogin(){
    this.loginService.onLogin(this.loginForm).subscribe({
      next : (response) =>{
        console.log(response);
        this.authToken=response.token;
        this.authService.saveToken(this.authToken);
        this.router.navigate(['loginLandingPage']);
        this.loginService.loggedinStateSubject.next(true);
      },
      error : (error) =>{
        console.error(error);
        alert('Something went wrong. Please enter valid inputs.')
        this.loginForm.reset();
      }
    })
  }
}
