import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class HeaderService {
  
  isLoggedIn: any

  constructor() { }

  setLoggedIn(): boolean {
    this.isLoggedIn = sessionStorage.getItem('isLoggedIn');
    console.log('isLoggedIn : ', this.isLoggedIn);
    return this.isLoggedIn;

  }
}
