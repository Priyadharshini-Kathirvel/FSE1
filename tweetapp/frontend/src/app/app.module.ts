import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegisterComponent } from './component/register/register.component';
import { HomeComponent } from './component/home/home.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LoginComponent } from './component/login/login.component';
import { ForgotPasswordComponent } from './component/forgot-password/forgot-password.component';
import { GetAllTweetsComponent } from './component/get-all-tweets/get-all-tweets.component';
import { GetAllUsersComponent } from './component/get-all-users/get-all-users.component';
import { SearchUserByUsernameComponent } from './component/search-user-by-username/search-user-by-username.component';
import { AddTweetComponent } from './component/add-tweet/add-tweet.component';
import { UpdateTweetComponent } from './component/update-tweet/update-tweet.component';
import { DeleteTweetComponent } from './component/delete-tweet/delete-tweet.component';
import { LikeTweetComponent } from './component/like-tweet/like-tweet.component';
import { ReplyToTweetComponent } from './component/reply-to-tweet/reply-to-tweet.component';
import { GetTweetsByUsernameComponent } from './component/get-tweets-by-username/get-tweets-by-username/get-tweets-by-username.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HeaderComponent } from './component/header/header/header.component';
import { MatToolbarModule} from '@angular/material/toolbar';
import { MatIconModule} from '@angular/material/icon';
import { MatFormFieldModule} from '@angular/material/form-field';
import { MatInputModule} from '@angular/material/input'
import { MatCardModule} from '@angular/material/card';
import { MatRadioModule} from '@angular/material/radio';
import { MatDatepickerModule} from '@angular/material/datepicker'
import { MatNativeDateModule } from '@angular/material/core';
import { MatButtonModule} from '@angular/material/button';
import { MatGridListModule} from '@angular/material/grid-list';
import { LogoutComponent } from './component/logout/logout/logout.component';
import { LoginLandingPageComponent } from './component/login-landing-page/login-landing-page/login-landing-page.component';
import { MatTabsModule} from '@angular/material/tabs';
import { MatSidenavModule} from '@angular/material/sidenav';
import { MatListModule} from '@angular/material/list';
import { HttpClientModule } from '@angular/common/http';
import { MatTableModule } from '@angular/material/table'

@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    HomeComponent,
    LoginComponent,
    ForgotPasswordComponent,
    GetAllTweetsComponent,
    GetAllUsersComponent,
    SearchUserByUsernameComponent,
    AddTweetComponent,
    UpdateTweetComponent,
    DeleteTweetComponent,
    LikeTweetComponent,
    ReplyToTweetComponent,
    GetTweetsByUsernameComponent,
    HeaderComponent,
    LogoutComponent,
    LoginLandingPageComponent
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    NgbModule,
    HttpClientModule,
    MatToolbarModule,
    MatIconModule,
    MatFormFieldModule,
    MatInputModule,
    MatCardModule,
    MatRadioModule,
    MatNativeDateModule,
    MatDatepickerModule,
    MatButtonModule,
    MatGridListModule,
    MatTabsModule,
    MatSidenavModule,
    MatListModule,
    MatTableModule
    
  ],
  providers: [ReplyToTweetComponent, LoginComponent,LogoutComponent],
  schemas:[CUSTOM_ELEMENTS_SCHEMA],
  bootstrap: [AppComponent]
})
export class AppModule { }
