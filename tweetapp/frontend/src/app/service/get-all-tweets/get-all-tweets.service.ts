import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Tweet } from 'src/app/class/tweet';
import { AuthenticationService } from '../authentication/authentication.service';

@Injectable({
  providedIn: 'root'
})
export class GetAllTweetsService {
  
  constructor(private authService:AuthenticationService,private httpClient:HttpClient) { }

  onGetAllTweets() : Observable<Tweet[]> {
    let authToken = 'Bearer ' + this.authService.getToken();
    const headers = new HttpHeaders().set('content-type','application/json').set('Access-Control-Allow-Origin','*').set('Authorization',authToken);
    return this.httpClient.get<Tweet[]>(
      'http://54.160.186.203:8081/api/v1.0/tweets/all',
      {'headers':headers}
    )
  }

}
