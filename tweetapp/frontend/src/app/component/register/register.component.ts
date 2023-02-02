import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { RegisterService } from 'src/app/service/register/register.service';
import { Router } from '@angular/router';
import { User } from 'src/app/class/user';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  hide = true;
  user : User;

  registrationForm = new FormGroup({
    'firstname': new FormControl(null, Validators.required),
    'lastname': new FormControl(null, Validators.required),
    'gender': new FormControl(null, Validators.required),
    'dateOfBirth': new FormControl(null, Validators.required),
    'email': new FormControl(null, Validators.required),
    'password': new FormControl(null, Validators.required),
    'confirmPassword': new FormControl(null, Validators.required),
    'mobileNumber': new FormControl(null, Validators.required),
  })

  constructor(
    private registerService:RegisterService,
    private router:Router
  ){}

  onRegister(){
    
    this.registerService.onRegister(this.registrationForm).subscribe({
      next : (registrationResponse) => {
        console.log('registrationResponse : ',registrationResponse);
        alert("User Registered Successfully!");
        this.router.navigate(['login']);
      },
      error : (error) => {
        console.error(error);
        alert("Something went wrong. Pleas enter valid inputs!")
      }
    })
  }
}
