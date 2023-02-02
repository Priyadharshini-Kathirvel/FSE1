import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ReplyToTweetService } from 'src/app/service/reply-to-tweet/reply-to-tweet.service';

@Component({
  selector: 'app-reply-to-tweet',
  templateUrl: './reply-to-tweet.component.html',
  styleUrls: ['./reply-to-tweet.component.css']
})
export class ReplyToTweetComponent {

  tweetId: any;

  replyToTweetForm = new FormGroup({
    'replyContent': new FormControl(null, Validators.required)
  })

  constructor(
    private replyToTweetService: ReplyToTweetService,
    private router: Router
  ) { }

  onReplyToTweet() {
    this.tweetId = sessionStorage.getItem('currentTweetId');
    console.log('tweetId in ReplyToTweetComponent : ', this.tweetId);
    this.replyToTweetService.onReplyToTweet(this.replyToTweetForm, this.tweetId).subscribe({
      next: (response) => {
        console.log(response);
        this.router.navigate(['loginLandingPage']);
      },
      error: (error) => {
        console.error(error);
      }
    })
  }
}
