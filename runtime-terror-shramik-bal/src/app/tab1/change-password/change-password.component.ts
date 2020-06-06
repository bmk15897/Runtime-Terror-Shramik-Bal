import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import {UniversalSharedService} from '../../services/shared-data-services/universal-shared.service'
import { Router } from '@angular/router';
import { ShramikHttpService } from 'src/app/services/http-services/shramik-http.service';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.scss'],
})
export class ChangePasswordComponent implements OnInit {

  public changePasswordForm = new FormGroup({
    currentPassword: new FormControl('',[Validators.required]),
    newPassword: new FormControl('',[Validators.required]),
    confirmNewPassword: new FormControl('',[Validators.required])});

  constructor(private universalSharedService: UniversalSharedService, private router: Router,private shramikHttpService : ShramikHttpService) { }

  ngOnInit() {}
  
  public passwordType='password';
  public passwordIcon='eye';

  generateTranslation(text) {
    if(this.universalSharedService.language=='HI') {
      switch(text) {
        case "Change Password" : {
          return "पासवर्ड बदलें";
        }
        case "Current Password" : {
          return "वर्तमान पासवर्ड";
        }
        case "New Password" : {
          return "नया पासवर्ड";
        }
        case "Confirm New Password" : {
          return "दोबारा नया पासवर्ड डालें";
        }
      }
    }
    return text;
  }

  changePassword() {
    console.log(this.changePasswordForm.value);
    let tempObject = {oldPassword:'',newPassword:'',userName:''};
    tempObject.oldPassword = this.changePasswordForm.controls.currentPassword.value;
    tempObject.newPassword = this.changePasswordForm.controls.newPassword.value;
    tempObject.userName = this.universalSharedService.userObject.loginDetails.userName;
    console.log(tempObject);
    this.shramikHttpService.updateProfilePassword(tempObject).subscribe(data => {
        console.log(data);
        this.universalSharedService.userObject = data.object;
        if(this.universalSharedService.userObject.loginDetails.type==='C') {
          this.universalSharedService.userObjectContractor = data.object;
        } else {
          this.universalSharedService.userObjectWorker = data.object;
        }
      this.router.navigate(['/tabs/tab1/settings']);
    });
  }

  checkPasswords() {
    return this.changePasswordForm.controls.newPassword.value==this.changePasswordForm.controls.confirmNewPassword.value;     
  }

  hideShowPassword() {
    this.passwordType = this.passwordType === 'text' ? 'password' : 'text';
    this.passwordIcon = this.passwordIcon === 'eye-off' ? 'eye' : 'eye-off';
}
}
