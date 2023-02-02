import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UpdateTweetService } from 'src/app/service/update-tweet/update-tweet.service';

@Component({
  selector: 'app-update-tweet',
  templateUrl: './update-tweet.component.html',
  styleUrls: ['./update-tweet.component.css']
})
export class UpdateTweetComponent {

  tweetId:any

  updateTweetForm = new FormGroup({
    'tweetContent':new FormControl(null, Validators.required)
  })

  constructor(
    private updateTweetService:UpdateTweetService,
    private router:Router
  ){}

  onUpdateTweet(){
    this.tweetId = sessionStorage.getItem('currentTweetId');
    let tweetContent =this.updateTweetForm.get('tweetContent').value;
    this.updateTweetService.onUpdateTweet(this.tweetId, tweetContent).subscribe({
      next : (response) => {
        console.log(response);
        this.router.navigate(['loginLandingPage']);
      },
      error: (error) =>{
        console.error(error);
      }
    })
  }
}
