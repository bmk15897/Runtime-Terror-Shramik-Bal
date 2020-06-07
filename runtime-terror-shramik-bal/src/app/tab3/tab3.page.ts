import { Component } from '@angular/core';
import { UniversalSharedService} from '../services/shared-data-services/universal-shared.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ShramikHttpService } from 'src/app/services/http-services/shramik-http.service';
import { AlertController } from '@ionic/angular';

@Component({
  selector: 'app-tab3',
  templateUrl: 'tab3.page.html',
  styleUrls: ['tab3.page.scss']
})
export class Tab3Page {

  public specializationFields = ["Agriculture","Construction", "Painters","Sewage Issues","House Shifting Related Help","Public Work"];
  public language="";

  
public createApplicationForm = new FormGroup({
  description: new FormControl('',[Validators.required]),
  field: new FormControl('',[Validators.required]),
  noOfPeople: new FormControl('',[Validators.required]),
  siteAddress: new FormControl('',[Validators.required]),
  siteCity: new FormControl('',[Validators.required]),
  siteState: new FormControl('',[Validators.required])});

  constructor(public alertController: AlertController,private universalSharedService: UniversalSharedService,private shramikHttpService : ShramikHttpService) {
    this.language=universalSharedService.language;
  }

  ngOnInit() {
    this.language=this.universalSharedService.language;
  }


  getTranslation(text) {
    
    if(this.universalSharedService.language=='HI') {
      switch(text) {
        case "Create Application" : {
          return "अनुरोध बनाएँ";
        }
        case "Requirement" : {
          return "जरुरत";
        }
        case "Number of workers required" : {
          return "श्रमिकों की संख्या की आवश्यकता";
        }
        case "Address-Line-1" : {
          return "प्राथमिक पता";
        }
        case "Create Application" : {
          return "रजिस्टर करें";
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
        case "Public Work" : {
          return "सरकारी काम";
        }
        case "Describe your requirement" : {
          return "अपने अनुरोध का वर्णन करें";
        }
        case "Application succesfully created" : {
          return "अनुरोध सफलतापूर्वक बन गया है";
        }
        case "Close" : {
          return "बंद करे";
        }
        case "State" : {
          return "राज्य";
        }
    }
    }
    return text;
  }

  createApplication(){
    console.log(this.createApplicationForm.value);
    let contractorRequirement = {
      contractorRequirementId : null,
      contractorUserName : null,
      description: null,
      field: null,
      noOfPeople: null,
      noOfPeopleApplied: 0,
      siteAddress: null,
      siteCity: null,
      siteState: null,
      isActive: 'Y',
      createdDate: (new Date()).toDateString()
    }
    contractorRequirement.contractorUserName = this.universalSharedService.userObject.loginDetails.userName;
    contractorRequirement.field = this.createApplicationForm.controls.field.value;
    contractorRequirement.siteAddress = this.createApplicationForm.controls.siteAddress.value;
    contractorRequirement.description = this.createApplicationForm.controls.description.value;
    contractorRequirement.siteCity = this.createApplicationForm.controls.siteCity.value;
    contractorRequirement.siteState = this.createApplicationForm.controls.siteState.value;
    contractorRequirement.noOfPeople = this.createApplicationForm.controls.noOfPeople.value;
    console.log(contractorRequirement);
    this.presentAlert();
    this.shramikHttpService.postContractorRequirement(contractorRequirement).subscribe(data => {
      console.log(data);
      this.createApplicationForm.reset();
    });
  }

  async presentAlert() {
    const alert = await this.alertController.create({
      cssClass: 'error-popup',
      message: this.getTranslation("Application succesfully created"),
      buttons: [this.getTranslation("Close")]
    });

    await alert.present();
  }
}
