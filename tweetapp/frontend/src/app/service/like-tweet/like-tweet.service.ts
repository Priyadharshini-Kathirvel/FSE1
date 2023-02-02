import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthenticationService } from '../authentication/authentication.service';

@Injectable({
  providedIn: 'root'
})
export class LikeTweetService {
  
  constructor(
    private httpClient: HttpClient,
    private authService: AuthenticationService
  ) {}

  onLikeTweet(tweetId:number) {
    let email = sessionStorage.getItem('loggedInUserEmail');
    let authToken = 'Bearer ' + this.authService.getToken();
    console.log('authToken in onLikeTweet : ', authToken)
    const headers = new HttpHeaders()
      .set('content-type','application/json')
      .set('Access-Control-Allow-Origin','*')
      .set('Authorization',authToken);
    return this.httpClient.put(
      `http://54.160.186.203:8081/api/v1.0/tweets/${email}/like/${tweetId}`,{},
      {'headers':headers}
    )
 }
}
