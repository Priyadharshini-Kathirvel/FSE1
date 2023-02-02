import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Tweet } from 'src/app/class/tweet';
import { User } from 'src/app/class/user';
import { DeleteTweetService } from 'src/app/service/delete-tweet/delete-tweet.service';
import { GetAllTweetsService } from 'src/app/service/get-all-tweets/get-all-tweets.service';
import { GetAllUsersService } from 'src/app/service/get-all-users/get-all-users.service';
import { GetTweetsByUsernameService } from 'src/app/service/get-tweets-by-username/get-tweets-by-username.service';
import { LikeTweetService } from 'src/app/service/like-tweet/like-tweet.service';
import { SearchUserByUsernameService } from 'src/app/service/search-user-by-username/search-user-by-username.service';

@Component({
  selector: 'app-login-landing-page',
  templateUrl: './login-landing-page.component.html',
  styleUrls: ['./login-landing-page.component.css']
})
export class LoginLandingPageComponent implements OnInit {

  tweets?: Tweet[];
  userTweets?: Tweet[];
  users?: User[];
  user: User;
  loggedInUser: string;

  constructor(
    private getAllTweetsService: GetAllTweetsService,
    private searchUserByUsernameService: SearchUserByUsernameService,
    private getTweetByUsernameService: GetTweetsByUsernameService,
    private likeTweetService: LikeTweetService,
    private deleteTweetService: DeleteTweetService,
    private getAllUsersService: GetAllUsersService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.onGetAllTweets();
    this.onGetAllUsers();
    this.loggedInUser = sessionStorage.getItem('loggedInUserEmail');
  }

  replyToTweetForm = new FormGroup({
    'replyContent': new FormControl(null, Validators.required)
  })

  updateTweetForm = new FormGroup({
    'tweetContent': new FormControl(null, Validators.required)
  })

  searchUserByUsernameForm = new FormGroup({
    'email': new FormControl(null, Validators.required)
  })

  getTweetByUsernameForm = new FormGroup({
    'email': new FormControl(null, Validators.required)
  })

  onReplyToTweet(tweetId: any) {
    sessionStorage.setItem('currentTweetId', tweetId);
  }

  onGetTweetsByUsername() {
    let email = this.getTweetByUsernameForm.get('email').value;
    console.log('email for searchUserByUsername : ', email);
    this.getTweetByUsernameService.onGetTweetsByUsername(email).subscribe({
      next: (response) => {
        this.userTweets = response;
        console.log(response);
      },
      error: (error) => console.log(error)
    })
  }

  onSearchUserByUsername() {
    let email = this.searchUserByUsernameForm.get('email').value;
    this.searchUserByUsernameService.onSearchUserByUsername(email).subscribe({
      next: (response) => {
        this.user = response;
        console.log(response);
      },
      error: (error) => console.log(error)
    })
  }

  onGetAllTweets() {
    this.getAllTweetsService.onGetAllTweets().subscribe({
      next: (response) => {
        this.tweets = response;
        console.log(response);
      },
      error: (error) => console.error(error)
    })
  }

  onGetAllUsers() {
    this.getAllUsersService.onGetAllUsers().subscribe({
      next: (response) => {
        this.users = response;
        console.log(response);
      },
      error: (error) => console.error(error)
    })
  }

  onLikeTweet(tweetId: any) {
    this.likeTweetService.onLikeTweet(tweetId).subscribe({
      next: (response) => {
        console.log(response);
        this.router.navigate(['loginLandingPage']);
      },
      error: (error) => console.error(error)
    })
  }

  onUpdateTweet(tweetId: any) {
    sessionStorage.setItem('currentTweetId', tweetId);
  }
 
  onDeleteTweet(tweetId: any) {
    console.log('Deleting tweet with id : ', tweetId)
    this.deleteTweetService.onDeleteTweet(tweetId).subscribe({
      next: (response) => {
        console.log(response);
        this.router.navigate(['loginLandingPage']);
      },
      error: (error) => console.error(error)
    })
  }


}
