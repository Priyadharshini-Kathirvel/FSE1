import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Observable } from 'rxjs';
import { User } from 'src/app/class/user';
import { AuthenticationService } from '../authentication/authentication.service';

@Injectable({
  providedIn: 'root'
})
export class ForgotPasswordService {


  constructor(
    private httpClient: HttpClient,
    private authService: AuthenticationService
  ) { }

  onResetPassword(forgotPasswordForm: FormGroup): Observable<User> {
    let email = forgotPasswordForm.get('email').value;
    let password = forgotPasswordForm.get('password').value;
    let authToken = 'Bearer ' + this.authService.getToken();
    const headers = new HttpHeaders()
      .set('content-type', 'application/json')
      .set('Access-Control-Allow-Origin', '*')
      .set('Authorization', authToken);
    return this.httpClient.post<User>(
      `http://54.160.186.203:8081/api/v1.0/tweets/${email}/forgot`,
      password,
      { 'headers': headers }
    )

  }
}
