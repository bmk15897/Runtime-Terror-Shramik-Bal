import { Component, ViewChild } from '@angular/core';
import { UniversalSharedService} from '../services/shared-data-services/universal-shared.service'
import {LoginPageComponent} from './login-page/login-page.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-tabs',
  templateUrl: 'tabs.page.html',
  styleUrls: ['tabs.page.scss']
})
export class TabsPage {

  @ViewChild(LoginPageComponent) loginPageComponent: LoginPageComponent;
  public language="";

  constructor(private universalSharedService: UniversalSharedService,private router: Router) {
    this.language=this.universalSharedService.language;
  }

  getTranslation(text) {
    if(this.universalSharedService.language=='HI') {
      switch(text) {
        case "My Profile" : {
          return "प्रोफाइल";
        }
        case "Home" : {
          return "होम";
        }
        case "Requirement" : {
          return "अनुरोध बनाएँ";
        }
        case "Notification" : {
          return "सूचना";
        }
    }
  }
  return text;
}

changeLanguage() {
  console.log('Language Changing..');
  console.log(this.universalSharedService.language);
  if(this.universalSharedService.language=="EN") {
    this.universalSharedService.language="HI";
    this.language="HI";
  }
  else {
    this.universalSharedService.language="EN";
    this.language="EN";
  }
  console.log(this.universalSharedService.language);
}

  isUserContractor() {
    return this.universalSharedService.userType=="CONTRACTOR";
  }

  isUserLoggedIn() {
    return this.universalSharedService.userLoggedIn;
    console.log(this.universalSharedService.userLoggedIn);
  }
}
