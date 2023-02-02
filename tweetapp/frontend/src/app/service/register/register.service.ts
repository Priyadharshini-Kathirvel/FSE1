import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Observable } from 'rxjs';
import { User } from 'src/app/class/user';
import { AddTweetService } from '../add-tweet/add-tweet.service';
import { AuthenticationService } from '../authentication/authentication.service';

@Injectable({
  providedIn: 'root'
})

export class RegisterService {
  
  constructor(private httpClient:HttpClient) { }

  onRegister(registrationForm:FormGroup): Observable<User> {
    let firstname = registrationForm.get('firstname').value;
    let lastname = registrationForm.get('lastname').value;
    let gender = registrationForm.get('gender').value;
    let dateOfBirth = registrationForm.get('dateOfBirth').value;
    let email = registrationForm.get('email').value;
    let password = registrationForm.get('password').value;
    let confirmPassword = registrationForm.get('confirmPassword').value;
    let mobileNumber = registrationForm.get('mobileNumber').value;
    return this.httpClient.post<User>(
      "http://54.160.186.203:8081/api/v1.0/tweets/register",
      {firstname, lastname, gender, dateOfBirth, email, password, confirmPassword, mobileNumber})
  }
}
