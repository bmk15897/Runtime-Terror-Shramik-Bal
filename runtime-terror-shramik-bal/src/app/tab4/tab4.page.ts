import { Component, ViewChild } from '@angular/core';
import { UniversalSharedService} from '../services/shared-data-services/universal-shared.service'
import { ShramikHttpService } from 'src/app/services/http-services/shramik-http.service';
import {NotificationProfileViewComponent} from './notification-profile-view/notification-profile-view.component';

@Component({
  selector: 'app-tab4',
  templateUrl: 'tab4.page.html',
  styleUrls: ['tab4.page.scss']
})
export class Tab4Page {

  public language="";

  public profileDetails:any={};

  public workerNewNotifications=[];

  public workerOldNotifications=[];

  public workerNotifications = [];


  public contractorNewNotifications=[];

  public contractorOldNotifications=[];
      
  public contractorNotifications=[];

  public notificationView=true;

  public todaysNotificationsWorkerVisible=true;
  public olderNotificationsWorkerVisible=true;
  public todaysNotificationsContractorVisible=true;
  public olderNotificationsContractorVisible=true;

  public olderNotifsIcon='chevron-up-outline';
  public todaysNotifsIcon='chevron-up-outline';

  @ViewChild(NotificationProfileViewComponent) notificationProfileView : NotificationProfileViewComponent

  ngOnInit() {
    console.log(this.isUserContractor());
    if(!this.isUserContractor()) {
        this.shramikHttpService.findWorkerApplicationsByLabourer(this.universalSharedService.userObject.loginDetails.userName).subscribe(data => {
          console.log(data.message);
          console.log(data.object);
          this.workerNotifications = data.object;
          for(let w of this.workerNotifications){
            if(w.approved!='P') {
              if(w.createdDate === (new Date()).toDateString()) {
                this.workerNewNotifications.push(w);
                this.todaysNotificationsWorkerVisible = true;
              }else {
                this.workerOldNotifications.push(w);
                this.olderNotificationsWorkerVisible=true;
              }
              }
          }
        });
    } else {
        this.shramikHttpService.getWAForContractor(this.universalSharedService.userObject.loginDetails.userName).subscribe(data => {
          console.log(data.message);
          console.log(data.object);
          this.contractorNotifications = data.object;
          for(let w of this.contractorNotifications){
            if(w.approved=='P') {
              if(w.createdDate === (new Date()).toDateString()) {
                this.contractorNewNotifications.push(w);
                this.todaysNotificationsContractorVisible=true;
              }else {
                this.contractorOldNotifications.push(w);
                this.olderNotificationsContractorVisible=true;
              }
              }
          }
        });
    }
  }
  constructor(private universalSharedService: UniversalSharedService, private shramikHttpService: ShramikHttpService) {
    this.language=universalSharedService.language;
    console.log(this.universalSharedService.userType==="CONTRACTOR");
  }

  getTranslation(text) {
    if(this.universalSharedService.language=='HI') {
      switch(text) {
        case "Notifications" : {
          return "सूचना";
        }
        case "Today's Notifications" : {
          return "आज की सूचनाएं";
        }
        case "Older Notifications" : {
          return "पुरानी सूचनाएं";
        }
        case "has a requirement of" : {
          return "को ज़रूरत है";
        }
        case "workers" : {
          return "मजदूरों की";
        }
        case "Address" : {
          return "पता";
        }
        case "Field of Specialization" : {
          return "विशेष योग्यता";
        }
        case "Rating" : {
          return "रेटिंग";
        }
        case "About" : {
          return "जानकारी";
        }
        case "years old" : {
          return "वर्षीय";
        }
        case "Contact Number" : {
          return "मोबाइल नंबर";
        }
        case "Group Count" : {
          return "श्रमिकों की संख्या";
        }
        case "Number of services provided" : {
          return "एप द्वारा की गयी सेवाओं की संख्या";
        }
        case "On Shramik Bal since " : {
          return "श्रमिक बल पर इतने साल से";
        }
        case "Experience" : {
          return "अनुभव";
        }
        case "years" : {
          return "साल";
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
        case "Group" : {
          return "समूह";
        }
        case "ABC is interested in your application." : {
          return " को आपके आवेदन में दिलचस्पी है";
        }
        case "Approve" : {
          return "मंजूर करें";
        }
        case "Decline" : {
          return "अस्वीकार करें";
        }
        case "CONTRACTOR" : {
          return "ठेकेदार";
        }
        case "LABOURER" : {
          return "मजदूर";
        }
        case "Status" : {
          return "स्थिति";
        }
        case "Contractor Requirement ID" : {
          return "ठेकेदार आवश्यकता आई.डी.";
        }
        case "Approved" : {
          return "स्वीकृत";
        }
        case "Declined" : {
          return "खारिज";
        }
        case "Application" : {
          return "आवेदन";
        }
        case " is interested in your application. Approve them now?" : {
          return " को आपके आवेदन में रूचि है। उनका आवेदन अभी स्वीकार करें?";
        }
        case "Application ID" : {
          return "एप्लीकेशन आई डी  ";
        }
        case "Description" : {
          return "विवरण";
        }
        case "Applied for your application" : {
          return "आपके इस आवेदन के लिए अप्लाई किया है";
        }
        case "Visit Profile" : {
          return "प्रोफाइल पे जाएँ";
        }
        case "Pending" : {
          return "विचाराधीन";
        }
      }
    }
    return text;
  }

  isApproved(a) {
    switch(a) {
    case 'Y' : {
      return "Approved";
    }
    case 'N' : {
      return "Declined";
    }
    case 'P' : {
      return "Pending";
    }
    }
  }

  isUserContractor() {
    console.log(this.universalSharedService.userType);
    return this.universalSharedService.userType==="CONTRACTOR";
  }

  openProfile(notification) {
    this.shramikHttpService.getLabourerDetails(notification.labourerId).subscribe( data => {
      this.notificationView=false;
      console.log(notification);
      this.profileDetails = data.object;
      this.profileDetails.workerApplicationId = notification.workerApplicationId;
    });

  }

  generateQRCode() {
    console.log('Generate QR Code');
  }

  goBackToNotifications() {
    this.ngOnInit();
    this.notificationView=true;
    console.log('function called');
  }

  approveRequest(a) {
    console.log(a);
    this.shramikHttpService.approveWorkerApplication(a).subscribe(data => {
      console.log('Request Approved!');
      this.notificationView=true;
    });
    
  }

  denyRequest(a) {
    console.log(a);
    this.shramikHttpService.declineWorkerApplication(a).subscribe(data => {
          console.log('Request denied!');
          this.notificationView=true;
    });
  }
  
  toggleVisibilityForTodaysNotifsWorker() {
    this.todaysNotificationsWorkerVisible=!this.todaysNotificationsWorkerVisible;
    if(this.todaysNotifsIcon=='chevron-down-outline') {
      this.todaysNotifsIcon='chevron-up-outline';
    }
    else {
      this.todaysNotifsIcon='chevron-down-outline';
    }
  }

  toggleVisibilityForOlderNotifsWorker() {
    this.olderNotificationsWorkerVisible=!this.olderNotificationsWorkerVisible;
    if(this.olderNotifsIcon=='chevron-down-outline') {
      this.olderNotifsIcon='chevron-up-outline';
    }
    else {
      this.olderNotifsIcon='chevron-down-outline';
    }
  }
  toggleVisibilityForTodaysNotifsContractor() {
    this.todaysNotificationsContractorVisible=!this.todaysNotificationsContractorVisible;
    if(this.todaysNotifsIcon=='chevron-down-outline') {
      this.todaysNotifsIcon='chevron-up-outline';
    }
    else {
      this.todaysNotifsIcon='chevron-down-outline';
    }
  }

  toggleVisibilityForOlderNotifsContractor() {
    this.olderNotificationsContractorVisible=!this.olderNotificationsContractorVisible;
    if(this.olderNotifsIcon=='chevron-down-outline') {
      this.olderNotifsIcon='chevron-up-outline';
    }
    else {
      this.olderNotifsIcon='chevron-down-outline';
    }
  }
}