import { Component, OnInit } from '@angular/core';
import {UniversalSharedService} from '../../services/shared-data-services/universal-shared.service';
import { ShramikHttpService } from 'src/app/services/http-services/shramik-http.service';

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.scss'],
})
export class HistoryComponent implements OnInit {

public contractorHistory=[];


public workerHistory=[];

  constructor(public universalSharedService : UniversalSharedService,private shramikHttpService : ShramikHttpService) { }

  ngOnInit() {
    if(this.isUserContractor()) {
      this.shramikHttpService.findContractorRequirementsByContractor(this.universalSharedService.userObject.loginDetails.userName).subscribe(data => {
        console.log(data);
        this.contractorHistory = data.object;
      });
    } else {
      this.shramikHttpService.findWorkerApplicationsByLabourerForHistory(this.universalSharedService.userObject.loginDetails.userName).subscribe(data => {
        console.log(data);
        this.workerHistory = data.object;
      });
     }
  }

  isUserContractor() {
    return this.universalSharedService.userType=='CONTRACTOR';
  }

  getTranslation(text) {
    
    if(this.universalSharedService.language=='HI') {
      switch(text) {
        case "Application ID" : {
          return "एप्लीकेशन आई डि";
        }
        case "Requirement" : {
          return "जरुरत";
        }
        case "Number of workers required" : {
          return "श्रमिकों की संख्या की आवश्यकता";
        }
        case "City" : {
          return "शहर";
        }
        case "Pincode" : {
          return "पिन कोड";
        }
        case "Agriculture" : {
          return "कृषि";
        }
        case "Construction" : {
          return "निर्माण";
        }
        case "Painters" : {
          return "पुताई";
        }
        case "Sewage Issues" : {
          return "नाले की सफाई";
        }
        case "House Shifting Related Help" : {
          return "घर शिफ्टिंग की मदद";
        }
        case "Public Works" : {
          return "सरकारी काम";
        }
        case "Active Status" : {
          return "एक्टिव";
        }
        case "Number of people required" : {
          return "इतने लोगों की ज़रूरत";
        }
        case "Number of people applied" : {
          return "इतने लोग मिल चुके हैं ";
        }
        case "Site Address" : {
          return "कार्यस्थल का पता";
        }
        case "Requirement ID" : {
          return "रिक्वायरमैंट आई डी";
        }
        case "Approved" : {
          return "स्वीकार";
        }
        case "Contractor Name" : {
          return " ठेकेदार का नाम";
        }
        case "Site Details" : {
          return "कार्यस्थल का पता";
        }
        case "Activity History" : {
          return "इतिहास";
        }
        case "Yes" : {
          return "हाँ";
        }
        case "No" : {
          return "नहीं";
        }
        case "Description" : {
          return "विवरण";
        }
    }
    }
    return text;
  }

  getFullform(str) {
    if(str=='Y') {
      return "Yes";
    }
    else {
      return "No";
    }
  }

  refreshNotifs() {
    this.ngOnInit();
    console.log('Notifications refreshing..');
  }

}
