import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AddTweetService } from 'src/app/service/add-tweet/add-tweet.service';

@Component({
  selector: 'app-add-tweet',
  templateUrl: './add-tweet.component.html',
  styleUrls: ['./add-tweet.component.css']
})
export class AddTweetComponent {
  addTweetForm = new FormGroup({
    'tweetContent': new FormControl(null, Validators.required)
  })

  constructor(
    private addTweetService: AddTweetService,
    private router: Router
  ) { }

  onPostTweet() {
    this.addTweetService.onPostTweet(this.addTweetForm).subscribe({
      next: (response) => {
        console.log(response);
        alert("Tweet Posted Successfully");
        this.router.navigate(['loginLandingPage']);
      },
      error: (error) => console.error(error)
    })
  }
}
