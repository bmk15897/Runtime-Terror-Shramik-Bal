import { Component, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { UniversalSharedService} from '../services/shared-data-services/universal-shared.service'

@Component({
  selector: 'app-tab1',
  templateUrl: 'tab1.page.html',
  styleUrls: ['tab1.page.scss']
})
export class Tab1Page {

    public noneSelected : boolean=true;
    public profileSelected : boolean=false;
    public historySelected : boolean=false;
    public settingsSelected : boolean=false;
    public language = "";

public options: any = [];

  constructor(private router: Router, private universalSharedService: UniversalSharedService) {
    this.options = [{name:'My Profile',icon:'person-circle-outline'},{name:'Activity History',icon:'walk'},{name:'Settings',icon:'settings-outline'},{name:"Change Language",icon:'logo-twitch'},{name:'Logout',icon:'arrow-redo-outline'}];
    this.language=this.universalSharedService.language;
  }

  navigateToScreen(option) {
    if(option.name=="My Profile") {
      this.router.navigate(['/tabs/tab1/profile'])
      console.log(option);
    }

    if(option.name=='Activity History') {
      this.router.navigate(['/tabs/tab1/history'])
      console.log(option);
    }
    
    if(option.name=="Settings") {
      this.router.navigate(['/tabs/tab1/settings'])
      console.log(option);
    }
    
    if(option.name=="Logout") {
      this.router.navigate(['/tabs/login'])
      this.universalSharedService.userLoggedIn=false;
      this.universalSharedService.userObject=null;
      console.log(option);
    }
    
    if(option.name=="Change Language") {
      if(this.universalSharedService.language=="HI") {
        this.universalSharedService.language="EN";
      }
      else {
        this.universalSharedService.language="HI";
      }
      console.log(option);
    }
  }

  getTranslation(text) {
    
    if(this.universalSharedService.language=='HI') {
      switch(text) {
        case "My Profile" : {
          return "प्रोफ़ाइल";
        }
        case "Activity History" : {
          return "इतिहास";
        }
        case "Settings" : {
          return "सेटिंग्स";
        }
        case "Logout" : {
          return "लोग आउट";
        }
        case "Change Language" : {
          return "भाषा बदलें";
        }
        case "Shramik Bal" : {
          return "श्रमिक बल";
        }
    }
    }
    return text;
  }

}
