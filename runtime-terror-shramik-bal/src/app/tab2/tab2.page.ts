import { Component } from '@angular/core';
import { UniversalSharedService} from '../services/shared-data-services/universal-shared.service'
import { AlertController } from '@ionic/angular';
import { ShramikHttpService } from 'src/app/services/http-services/shramik-http.service';

@Component({
  selector: 'app-tab2',
  templateUrl: 'tab2.page.html',
  styleUrls: ['tab2.page.scss']
})
export class Tab2Page {

  isItemAvailable : boolean;
  options: any = [];
  noSearchTriggered = true;
  searchResultList=[];
  searchCriteriaOption="";

  constructor(public alertController: AlertController,private universalSharedService: UniversalSharedService,private shramikHttpService : ShramikHttpService) {
  this.isItemAvailable = false;
  }

  ngOnInit() {
    this.noSearchTriggered=true;
    this.searchResultList=[];
    this.searchCriteriaOption="";
    if(this.universalSharedService.userType==="CONTRACTOR") {
      this.shramikHttpService.findLabourersByState(this.universalSharedService.userObject.contractor.state).subscribe(data => {
        console.log(data.object);
        this.workers = data.object;
      });
    } else {
        let req1 = {
          field: this.universalSharedService.userObject.labourer.fieldOfSpecialization,
          city: this.universalSharedService.userObject.labourer.city
        }
        console.log(req1);
        this.shramikHttpService.getCRAccordingToFieldAndCity(req1).subscribe(data => {
        console.log(data.object);
        this.applications = data.object;
        let req2 = {
        field: this.universalSharedService.userObject.labourer.fieldOfSpecialization,
        state: this.universalSharedService.userObject.labourer.state
        }
        if(this.applications.length<5) {
          this.shramikHttpService.getCRAccordingToFieldAndState(req2).subscribe(data => {
            console.log(data.object);
            this.applications = data.object;
            if(this.applications.length<5) {
              this.shramikHttpService.getCRAccordingToField(this.universalSharedService.userObject.labourer.fieldOfSpecialization).subscribe(data => {
                console.log(data.object);
                this.applications = data.object;
            });
            }
          });
        }
      });
    }
  }

  public workers=[];

  public applications=[];

  initializeOptions(){
    this.options = ["Agriculture","Construction", "Painters","Sewage Issues","House Shifting","Public Work"];
    }

  getItems(ev: any) {
  this.initializeOptions();
  this.isItemAvailable=true;
  this.noSearchTriggered=true;
  this.searchResultList=[];
  this.searchCriteriaOption="";
  const val = ev.target.value;
  if (val && val.trim() != '') {
      this.isItemAvailable = true;
      this.options = this.options.filter((item) => {
      return (item.toLowerCase().indexOf(val.toLowerCase()) > -1);
  });
  }
  if(val.trim() == '') {
    this.isItemAvailable = false;
  }
 }

 getTranslation(text) {
  if(this.universalSharedService.language=='HI') {
    switch(text) {
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
        return "नाले की समस्या ";
      }
      case "House Shifting" : {
        return "घर की शिफ्टिंग";
      }
      case "Public Work" : {
        return "सरकारी काम";
      }
      case "How can we help you?" : {
        return "हम आपकी क्या सहायता कर सकते हैं?";
      }
      case "Name" : {
        return "नाम";
      }
      case "Age" : {
        return "आयु";
      }
      case "Rating" : {
        return "रेटिंग";
      }
      case "Specialization" : {
        return "विशेष योग्यता";
      }
      case "years" : {
        return "वर्ष";
      }
      case "Showing search results for" : {
        return "खोज परिणाम ";
      }
      case "Application ID" : {
        return "एप्लीकेशन आई डी";
      }
      case "Requirement" : {
        return "क्षेत्र";
      }
      case "Number of people required" : {
        return "आवश्यक लोगों की संख्या";
      }
      case "Number of people selected so far" : {
        return "अब तक चयनित लोगों की संख्या";
      }
      case "Site Address" : {
        return "साइट का पता";
      }
      case "City" : {
        return "शहर";
      }
      case "Contractor ID" : {
        return "ठेकेदार की आई.डी.";
      }
      case "Apply" : {
        return "आवेदन करें";
      }
      case "Open Applications" : {
        return "नए रोज़गार के अवसर";
      }
      case "Number of services provided" : {
        return "प्रदान की गई सेवाओं की संख्या";
      }
      case "With us since" : {
        return "हमारे साथ इस वर्ष से";
      }
      case "Years of experience" : {
        return "इतने सालों का अनुभव";
      }
      case "Applied successfully" : {
        return "आवेदन सफलतापूर्वक कर दिया गया है";
      }
      case "Close" : {
        return "बंद करें";
      }
      case "Description" : {
        return "विवरण";
      }
      case "Applied" : {
        return "आवेदन कर दिया गया है";
      }
  }
  }
  return text;
}

triggerSearch(option) {
  this.searchResultList=[]
  console.log('Triggering search for '+option);
  this.noSearchTriggered=false;
  this.isItemAvailable=false;
  this.searchCriteriaOption=option;

  this.searchResultList=this.workers.filter(worker => {
    return worker.fieldOfSpecialization.toLowerCase()==option.toLowerCase()});
}

applyNow(a) {
  let WAReq = {
    createdDate: (new Date()).toDateString(),
    contractorRequirementId: a.contractorRequirementId,
    labourer:this.universalSharedService.userObject.loginDetails.userName
  }
  this.shramikHttpService.addWorkerApplication(WAReq).subscribe(data => {
      console.log(data);
    });
  console.log(a.contractorRequirementId);
  a.buttonText = "Applied";
  a.color = "success";
  this.presentApplyConfirmationAlert();

}

getColor() {
  let color = '#99ff99';
  return color;
}

async presentApplyConfirmationAlert() {
  const alert = await this.alertController.create({
    cssClass: 'error-popup',
    message: this.getTranslation("Applied successfully"),
    buttons: [this.getTranslation("Close")]
  });

  await alert.present();
}

buttonColor(a) {
  if(a.buttonText=="Applied") {
    return "success";
  }
  else {
    return "dark";
  }
}

generateRandomRating() {
  return this.getRandomInt(20,50)/10;
}

generateRandomServicesProvided() {
  return this.getRandomInt(5,20);
}

getRandomInt(min, max) {
  return Math.floor(Math.random() * (max - min + 1)) + min;
}

    
}
