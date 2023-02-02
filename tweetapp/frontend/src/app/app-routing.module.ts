import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddTweetComponent } from './component/add-tweet/add-tweet.component';
import { DeleteTweetComponent } from './component/delete-tweet/delete-tweet.component';
import { ForgotPasswordComponent } from './component/forgot-password/forgot-password.component';
import { GetAllTweetsComponent } from './component/get-all-tweets/get-all-tweets.component';
import { GetAllUsersComponent } from './component/get-all-users/get-all-users.component';
import { GetTweetsByUsernameComponent } from './component/get-tweets-by-username/get-tweets-by-username/get-tweets-by-username.component';
import { HomeComponent } from './component/home/home.component';
import { LikeTweetComponent } from './component/like-tweet/like-tweet.component';
import { LoginLandingPageComponent } from './component/login-landing-page/login-landing-page/login-landing-page.component';
import { LoginComponent } from './component/login/login.component';
import { LogoutComponent } from './component/logout/logout/logout.component';
import { RegisterComponent } from './component/register/register.component';
import { ReplyToTweetComponent } from './component/reply-to-tweet/reply-to-tweet.component';
import { SearchUserByUsernameComponent } from './component/search-user-by-username/search-user-by-username.component';
import { UpdateTweetComponent } from './component/update-tweet/update-tweet.component';

const routes: Routes = [
  {path:'', component:HomeComponent},
  {path:'home', component:HomeComponent},
  {path:'register',component:RegisterComponent},
  {path:'login',component:LoginComponent},
  {path:'loginLandingPage', component:LoginLandingPageComponent},
  {path:'forgotPassword',component:ForgotPasswordComponent},
  {path:'getAllTweets',component:GetAllTweetsComponent},
  {path:'getAllUsers',component:GetAllUsersComponent},
  {path:'getTweetsByUsername',component:GetTweetsByUsernameComponent},
  {path:'searchUserByUsername',component:SearchUserByUsernameComponent},
  {path:'addTweet',component:AddTweetComponent},
  {path:'updateTweet',component:UpdateTweetComponent},
  {path:'deleteTweet',component:DeleteTweetComponent},
  {path:'likeTweet',component:LikeTweetComponent},
  {path:'replyToTweet',component:ReplyToTweetComponent},
  {path:'logout', component:LogoutComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
