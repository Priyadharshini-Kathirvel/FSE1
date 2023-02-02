import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { BehaviorSubject, Observable } from 'rxjs';
import { AuthenticationService } from '../authentication/authentication.service';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  
  loggedinStateSubject : BehaviorSubject<Boolean> = new BehaviorSubject<Boolean>(false);
  state: Observable<Boolean> = this.loggedinStateSubject.asObservable();

  constructor(private httpClient:HttpClient) { }

  onLogin(loginForm:FormGroup) : Observable<any> {
    let email = loginForm.get('email').value;
    sessionStorage.setItem('loggedInUserEmail', email);
    let password = loginForm.get('password').value;
    return this.httpClient.post<any>('http://54.160.186.203:8081/api/v1.0/tweets/login',{email, password});
  }
}
