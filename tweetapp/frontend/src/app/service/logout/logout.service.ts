import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from 'src/app/class/user';
import { AuthenticationService } from '../authentication/authentication.service';

@Injectable({
  providedIn: 'root'
})
export class LogoutService {
  
  constructor(
    private authService:AuthenticationService,
    private httpClient:HttpClient
  ) { }

  onLogout(): Observable<User> {
    let email = sessionStorage.getItem('loggedInUserEmail');
    let authToken = 'Bearer ' + this.authService.getToken();
    const headers = new HttpHeaders().set('content-type','application/json').set('Access-Control-Allow-Origin','*').set('Authorization',authToken);
    return this.httpClient.post<User>(
      `http://54.160.186.203:8081/api/v1.0/tweets/${email}/logout`,
      {},
      {'headers':headers}
    );
  }

}
