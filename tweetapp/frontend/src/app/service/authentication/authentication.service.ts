import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  authToken:string;

  saveToken(token:string){
    sessionStorage.setItem('token',token);
  }

  getToken():string{
    this.authToken=sessionStorage.getItem('token');
    return this.authToken;
  }
}
