import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthenticationService } from '../authentication/authentication.service';

@Injectable({
  providedIn: 'root'
})
export class UpdateTweetService {
 
  constructor(
    private httpClient:HttpClient,
    private authService: AuthenticationService
  ) {}

  onUpdateTweet(tweetId : any, tweetContent:string):Observable<any> {
    let email = sessionStorage.getItem('loggedInUserEmail');
    let authToken = 'Bearer ' + this.authService.getToken();
    const headers = new HttpHeaders().set('content-type','application/json').set('Access-Control-Allow-Origin','*').set('Authorization',authToken);
    return this.httpClient.put(
      `http://54.160.186.203:8081/api/v1.0/tweets/${email}/update/${tweetId}`,
      tweetContent,
      {'headers':headers}
    )
  }

}
