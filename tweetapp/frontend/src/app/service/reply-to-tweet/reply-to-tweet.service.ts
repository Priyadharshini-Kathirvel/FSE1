import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Observable } from 'rxjs';
import { Tweet } from 'src/app/class/tweet';
import { AuthenticationService } from '../authentication/authentication.service';

@Injectable({
  providedIn: 'root'
})
export class ReplyToTweetService {
  
  constructor(
    private authService: AuthenticationService,
    private httpClient:HttpClient
  ) {}

  onReplyToTweet(replyToTweetForm:FormGroup, tweetId:number):Observable<Tweet> {
    let replyContent = replyToTweetForm.get('replyContent').value;
    let email = sessionStorage.getItem('loggedInUserEmail');
    let authToken = 'Bearer ' + this.authService.getToken();
    const headers = new HttpHeaders()
      .set('content-type','application/json')
      .set('Access-Control-Allow-Origin','*')
      .set('Authorization',authToken);
    return this.httpClient.post<Tweet>(
      `http://54.160.186.203:8081/api/v1.0/tweets/${email}/reply/${tweetId}`,
      replyContent,
      {'headers':headers}

    )
    
  }

}
