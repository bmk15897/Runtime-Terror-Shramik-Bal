import { Component, OnInit } from '@angular/core';
import {UniversalSharedService} from '../../services/shared-data-services/universal-shared.service'
import { Router } from '@angular/router';

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.scss'],
})
export class SettingsComponent implements OnInit {

  constructor(private universalSharedService: UniversalSharedService, private router: Router) { }

  public options=[{name:"Update your profile",icon:"checkmark-done-outline"},{name:"Change Password",icon:"keypad-outline"}];

  ngOnInit() {}

  generateTranslation(text) {
    if(this.universalSharedService.language=='HI') {
      switch(text) {
        case "Update your profile" : {
          return "प्रोफाइल अपडेट करें";
        }
        case "Change Password" : {
          return "पासवर्ड बदलें";
        }
        case "Settings" : {
          return "सेटिंग्स";
        }
      }
    }
    return text;
  }

  navigateToScreen(option) {
    console.log('Button click');
    switch(option) {
      case "Update your profile" : {
      this.router.navigate(['/tabs/tab1/updateProfile']);
      break;
      }
      case "Change Password" : {
        this.router.navigate(['/tabs/tab1/changePassword']); 
        break;
        }
      }
    }
}
