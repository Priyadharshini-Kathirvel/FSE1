import { coerceStringArray } from '@angular/cdk/coercion';
import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ForgotPasswordService } from 'src/app/service/forgot-password/forgot-password.service';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent {
  hide=true;
  
  forgotPasswordForm = new FormGroup({
    'email': new FormControl(null, Validators.required),
    'password': new FormControl(null, Validators.required)
  })

  constructor(
    private forgotPasswordService:ForgotPasswordService,
    private router:Router
  ){}

  onResetPassword(){
    this.forgotPasswordService.onResetPassword(this.forgotPasswordForm).subscribe({
      next : (response) => {
        console.log(response);
        alert("Password Reset Successfully");
        this.router.navigate(['login']);
      },
      error : (error) => {
        console.error(error);
        alert("Something wrong. Please Try Again");
      }
    })
  }
}
