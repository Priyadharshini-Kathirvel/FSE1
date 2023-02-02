import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from 'src/app/class/user';
import { AuthenticationService } from '../authentication/authentication.service';

@Injectable({
  providedIn: 'root'
})
export class SearchUserByUsernameService {
  
  constructor(
    private authService: AuthenticationService,
    private httpClient : HttpClient
  ) { }

  onSearchUserByUsername(email:string):Observable<User> {
    let authToken = 'Bearer ' + this.authService.getToken();
    console.log('authToken in onSearchUserByUsername : ', authToken)
    const headers = new HttpHeaders()
      .set('content-type','application/json')
      .set('Access-Control-Allow-Origin','*')
      .set('Authorization',authToken);
    return this.httpClient.get<User>(
      `http://54.160.186.203:8081/api/v1.0/tweets/user/search/${email}`,
       {'headers':headers}
    )
  }
  
}
