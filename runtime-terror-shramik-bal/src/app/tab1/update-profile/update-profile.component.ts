import { Component, OnInit } from '@angular/core';
import {UniversalSharedService} from '../../services/shared-data-services/universal-shared.service'
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ShramikHttpService } from 'src/app/services/http-services/shramik-http.service';
import { AlertController } from '@ionic/angular';

@Component({
  selector: 'app-update-profile',
  templateUrl: './update-profile.component.html',
  styleUrls: ['./update-profile.component.scss'],
})
export class UpdateProfileComponent implements OnInit {


  public userTypes=["Worker","Contractor"];
  public workerTypes=["Individual","Group"];
  public specializationFields=["Agriculture","Construction", "Painters","Sewage Issues","House Shifting Related Help","Public Work","Domestic Help"];


  //User's information which will be stored somewhere in an object in universal-service
  userInformation=this.universalSharedService.userObject;

//Later when you assign values from the above dictionary to formControls, make sure you set blank value for formControl if the attribute is absent in the dictionary
//Rather, login ke baad jab object aayega, tab hi jo attributes absent honge object me wo add kar denge object me, and initialize them as empty string

  public updateProfileInformationForm = new FormGroup({
    name : new FormControl('',[Validators.required]), //not sure if we should allow 'em to change username
    username: new FormControl('',[Validators.required]),
    aadhaarId : new FormControl('',[]), //this should be disabled for update, permanently
    contactNo : new FormControl('',[Validators.required,Validators.maxLength(10),Validators.minLength(10)]),
    address: new FormControl('',[Validators.required]),
    city: new FormControl('',[Validators.required]),
    state: new FormControl('',[Validators.required]),
    age: new FormControl('',[Validators.required]),
    personOrGroup : new FormControl('',[]),
    groupStrength : new FormControl('1',[]),
    specialization : new FormControl('',[]),
    yearsOfExperience :new FormControl('',[])
  });

  
  constructor(public alertController: AlertController,private universalSharedService: UniversalSharedService, private router: Router,private shramikHttpService : ShramikHttpService) { }

  ngOnInit() {
    console.log(this.userInformation);
    console.log(this.universalSharedService.userType);
    if (this.universalSharedService.userType === 'WORKER') {

      this.userInformation = this.universalSharedService.userObject;

          console.log(this.userInformation.labourer.name);
          this.updateProfileInformationForm.controls.name.setValue(this.userInformation.labourer.name);
          this.updateProfileInformationForm.controls.username.setValue(this.userInformation.loginDetails.userName);
          this.updateProfileInformationForm.controls.aadhaarId.setValue(this.userInformation.labourer.aadhaarId);
          this.updateProfileInformationForm.controls.contactNo.setValue(this.userInformation.labourer.contactNo);
          this.updateProfileInformationForm.controls.address.setValue(this.userInformation.labourer.address);
          this.updateProfileInformationForm.controls.city.setValue(this.userInformation.labourer.city);
          this.updateProfileInformationForm.controls.state.setValue(this.userInformation.labourer.state);
          this.updateProfileInformationForm.controls.age.setValue(this.userInformation.labourer.age);
          this.updateProfileInformationForm.controls.personOrGroup.setValue(this.userInformation.labourer.personOrGroup);
          this.updateProfileInformationForm.controls.groupStrength.setValue(this.userInformation.labourer.groupCount);
          this.updateProfileInformationForm.controls.specialization.setValue(this.userInformation.labourer.fieldOfSpecialization.split(","));
          this.updateProfileInformationForm.controls.yearsOfExperience.setValue(this.userInformation.labourer.yearsOfExperience);

          this.updateProfileInformationForm.controls.personOrGroup.setValidators([Validators.required]);
          this.updateProfileInformationForm.controls.groupStrength.setValidators([Validators.required]);
          this.updateProfileInformationForm.controls.specialization.setValidators([Validators.required]);
          this.updateProfileInformationForm.controls.yearsOfExperience.setValidators([Validators.required]);
        } else if(this.universalSharedService.userType === 'CONTRACTOR'){

          this.userInformation = this.universalSharedService.userObject;

          this.updateProfileInformationForm.controls.name.setValue(this.userInformation.contractor.name);
          this.updateProfileInformationForm.controls.username.setValue(this.userInformation.loginDetails.userName);
          this.updateProfileInformationForm.controls.aadhaarId.setValue(this.userInformation.contractor.aadhaarId);
          this.updateProfileInformationForm.controls.contactNo.setValue(this.userInformation.contractor.contactNo);
          this.updateProfileInformationForm.controls.address.setValue(this.userInformation.contractor.address);
          this.updateProfileInformationForm.controls.city.setValue(this.userInformation.contractor.city);
          this.updateProfileInformationForm.controls.state.setValue(this.userInformation.contractor.state);
          this.updateProfileInformationForm.controls.age.setValue(this.userInformation.contractor.age);

          this.updateProfileInformationForm.controls.personOrGroup.setValidators([]);
          this.updateProfileInformationForm.controls.groupStrength.setValidators([]);
          this.updateProfileInformationForm.controls.specialization.setValidators([]);
          this.updateProfileInformationForm.controls.yearsOfExperience.setValidators([]);
        }
  }

  getTranslation(text) {
    if(this.universalSharedService.language=='HI') {
      switch(text) {
        case "Update Profile" : {
          return "प्रोफाइल अपडेट करें";
        }
        case "Username" : {
          return "यूजरनाम";
        }
        case "Close" : {
          return "बंद करें";
        }
        case "Name" : {
          return "नाम";
        }
        case "Register as individual or group?" : {
          return "एक या समूह ?";
        }
        case "Individual" : {
          return "एक";
        }
        case "Group" : {
          return "समूह";
        }
        case "Aadhar ID Number" : {
          return "आधार कार्ड नंबर";
        }
        case "Address" : {
          return "पता";
        }
        case "City" : {
          return "शहर";
        }
        case "Experience in years" : {
          return "आपको कितने सालों का अनुभव है?";
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
        case "Specialization" : {
          return "आप किस काम में सक्षम हैं?";
        }
        case "Age" : {
          return "आपकी उम्र";
        }
        case "Number of people in the group" : {
          return "आपके समूह में कितने लोग हैं ?";
        }
        case "Contact Number" : {
          return "मोबाइल नंबर";
        }
        case "Public Work" : {
          return "सरकारी काम";
        }
        case "Profile successfully updated" : {
          return "प्रोफ़ाइल अपडेट कर दी गई है";
        }
        case "Close" : {
          return "बंद करे";
        }
        case "State" : {
          return "राज्य";
        }
        case "Gender" : {
          return "लिंग";
        }
        case "Date of Birth" : {
          return "जन्म तिथि";
        }
        case "Domestic Help" : {
          return "घरेलु मदद";
        }
      }
    }
    return text;
  }


  registeringAsGroup() {
    if(this.updateProfileInformationForm.controls.personOrGroup.value=="Group") {
      return true;
    }
    return false;
  }

  isLabourer() {
    if(this.universalSharedService.userType=="WORKER") {
      return true;
    }
    return false;
  }

  updateProfile() {
    console.log(this.updateProfileInformationForm.value);
    
    let tempProfile = this.universalSharedService.userObject;
    if(this.universalSharedService.userType === "WORKER"){
      tempProfile.labourer.name = this.updateProfileInformationForm.controls.name.value;
      tempProfile.labourer.contactNo = this.updateProfileInformationForm.controls.contactNo.value;
      tempProfile.labourer.city = this.updateProfileInformationForm.controls.city.value;
      tempProfile.labourer.state = this.updateProfileInformationForm.controls.state.value;
      tempProfile.labourer.age = this.updateProfileInformationForm.controls.age.value;
      tempProfile.labourer.personOrGroup = this.updateProfileInformationForm.controls.personOrGroup.value;
      tempProfile.labourer.groupCount = this.updateProfileInformationForm.controls.groupStrength.value;
      tempProfile.labourer.fieldOfSpecialization = this.updateProfileInformationForm.controls.specialization.value.join(",");
      tempProfile.labourer.yearsOfExperience = this.updateProfileInformationForm.controls.yearsOfExperience.value;
      this.shramikHttpService.updateProfile(tempProfile).subscribe(data => {
        console.log(data);
        this.universalSharedService.userObject = data.object;
        this.universalSharedService.userObjectWorker = data.object;
      this.router.navigate(['/tabs/tab1/settings']);
    });
    } else if(this.universalSharedService.userType === "CONTRACTOR") {
      tempProfile.contractor.name = this.updateProfileInformationForm.controls.name.value;
      tempProfile.contractor.contactNo = this.updateProfileInformationForm.controls.contactNo.value;
      tempProfile.contractor.city = this.updateProfileInformationForm.controls.city.value;
      tempProfile.contractor.state = this.updateProfileInformationForm.controls.state.value;
      tempProfile.contractor.age = this.updateProfileInformationForm.controls.age.value;
      this.shramikHttpService.updateProfile(tempProfile).subscribe(data => {
        console.log(data);
        this.universalSharedService.userObject = data.object;
        this.universalSharedService.userObjectContractor = data.object;
      this.router.navigate(['/tabs/tab1/settings']);
    });
    }  
    this.presentAlert();
  }

  onKeyPress(event) {
    if ((event.keyCode >= 65 && event.keyCode <= 90) || (event.keyCode >= 97 && event.keyCode <= 122) || event.keyCode == 32 || event.keyCode == 46) {
        return true;
    }
    else {
        return false;
    }
  }

  async presentAlert() {
    const alert = await this.alertController.create({
      cssClass: 'error-popup',
      message: this.getTranslation("Profile successfully updated"),
      buttons: [this.getTranslation("Close")]
    });

    await alert.present();
  }
}
