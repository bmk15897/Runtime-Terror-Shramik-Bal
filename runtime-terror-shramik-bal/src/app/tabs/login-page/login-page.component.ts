import { Component, OnInit, ViewChild } from '@angular/core';
import {UniversalSharedService} from '../../services/shared-data-services/universal-shared.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AlertController } from '@ionic/angular';
import { ShramikHttpService } from 'src/app/services/http-services/shramik-http.service';
import { Router } from '@angular/router';
import { RegistrationComponent } from 'src/app/tabs/registration/registration.component';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss'],
})
export class LoginPageComponent implements OnInit {

  constructor(private router: Router,private shramikHttpService : ShramikHttpService, private universalSharedService: UniversalSharedService,public alertController: AlertController) { }


  @ViewChild(RegistrationComponent) registrationComponent: RegistrationComponent;
  
  public initialLoad;

  public loginForm = new FormGroup({
    username: new FormControl('',[Validators.required]),
    password: new FormControl('',[Validators.required])});

  public registrationForm = new FormGroup({
    name : new FormControl('',[Validators.required,Validators.pattern('^[a-zA-Z ]*$')]),
    username: new FormControl('',[Validators.required]),
    password: new FormControl('',[Validators.required]),
    confirmPassword: new FormControl('',[Validators.required]),
    type: new FormControl('',[Validators.required]),
    gender: new FormControl('',[Validators.required]),
    aadhaarId : new FormControl('',[]),
    contactNo : new FormControl('',[Validators.required,Validators.maxLength(10),Validators.minLength(10)]),
    address: new FormControl('',[Validators.required]),
    city: new FormControl('',[Validators.required]),
    state: new FormControl('',[Validators.required]),
    personOrGroup : new FormControl('',[]),
    groupStrength : new FormControl(1,[]),
    specialization : new FormControl('',[]),
    yearsOfExperience :new FormControl('',[]),
    dateOfBirth: new FormControl('',[Validators.required]),
    imageUrl : new FormControl('',[Validators.required])
  });

  public userTypes=["Worker","Contractor"];
  public workerTypes=["Individual","Group"];
  public specializationFields=["Agriculture","Construction", "Painters","Sewage Issues","House Shifting Related Help","Public Work"];
  public genderList=[{key:'Male',value:'M'},{key:'Female',value:'F'},{key:'Other',value:'O'}];

  public passwordType='password';
  public passwordIcon='eye';

  public loginPasswordType='password';
  public loginPasswordIcon='eye';

  ngOnInit() {
    this.initialLoad=true;
    this.registrationForm.get('type').valueChanges
      .subscribe(userCategory => {

        if (userCategory === 'Worker') {
          this.registrationForm.controls.personOrGroup.setValidators([Validators.required]);
          this.registrationForm.controls.groupStrength.setValidators([Validators.required]);
          this.registrationForm.controls.specialization.setValidators([Validators.required]);
          this.registrationForm.controls.yearsOfExperience.setValidators([Validators.required]);
        } else if(userCategory === 'Contractor'){
          this.registrationForm.controls.personOrGroup.setValidators([]);
          this.registrationForm.controls.groupStrength.setValidators([]);
          this.registrationForm.controls.specialization.setValidators([]);
          this.registrationForm.controls.yearsOfExperience.setValidators([]);
        }
      });
  }


  logIn() {
    let loginDetails={userName: '', password:''};
    loginDetails.userName=this.loginForm.controls.username.value;
    loginDetails.password=this.loginForm.controls.password.value;

    //console.log(this.loginForm.value);

    this.shramikHttpService.validateUserThroughLogin(loginDetails).subscribe(data => {
      console.log(data);
      this.universalSharedService.userObject=data.object;
      if(this.universalSharedService.userObject===null) {
         this.presentAlert();
      } else {
      this.universalSharedService.userLoggedIn=true;
      if(this.universalSharedService.userObject.loginDetails.type==="L"){
        this.universalSharedService.userType="WORKER";
        this.universalSharedService.userObjectWorker = data;
      }else if(this.universalSharedService.userObject.loginDetails.type==="C") {
        this.universalSharedService.userType="CONTRACTOR";
        this.universalSharedService.userObjectContractor = data;
      }
    }
   });
    
  }

  getTranslation(text) {
    if(this.universalSharedService.language=='HI') {
      switch(text) {
        case "Shramik Bal" : {
          return "श्रमिक बल";
        }
        case "Log In" : {
          return "लोग इन करें";
        }
        case "Create an account" : {
          return "अकाउंट बनाएं";
        }
        case "Username" : {
          return "यूजरनाम";
        }
        case "Password" : {
          return "पासवर्ड";
        }
        case "Change Language" : {
          return "भाषा बदलें";
        }
        case "Incorrect Username or Password" : {
          return "गलत यूजरनाम या पासवर्ड";
        }
        case "Close" : {
          return "बंद करें";
        }
        case "Account Registration Form" : {
          return "अकाउंट सम्बंधित जानकारी";
        }
        case "Confirm Password" : {
          return "कन्फर्म पासवर्ड";
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
        case "Worker" : {
          return "श्रमिक";
        }
        case "Contractor" : {
          return "ठेकेदार";
        }
        case "Register as Contractor or Worker?" : {
          return "आप श्रमिक हैं या ठेकेदार ?";
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
        case "Register" : {
          return "रजिस्टर करें";
        }
        case "Contact Number" : {
          return "मोबाइल नंबर";
        }
        case "Public Work" : {
          return "सरकारी काम";
        }
        case "Male" : {
          return "पुरुष";
        }
        case "Female" : {
          return "महिला";
        }
        case "Other" : {
          return "अन्य";
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
        case "Registration successful" : {
          return "रजिस्ट्रेशन सफलतापूर्वक हो गया है";
        }
        case "Please enter the OTP you received on your mobile number" : {
          return "कृपया अपने फ़ोन पर प्राप्त ओटीपी दर्ज करें";
        }
        case "OTP" : {
          return "ओ.टी.पी."
        }
        case "Submit" : {
          return "सबमिट करें";
        }
        case "Image URL": {
          return "फोटो की यूआरएल";
        }
    }
  }
  return text;
  }

  changeLanguage() {
    if(this.universalSharedService.language=="EN") {
      this.universalSharedService.language="HI";
    }
    else {
      this.universalSharedService.language="EN";
    }
  }  
  
  async presentAlert() {
    const alert = await this.alertController.create({
      cssClass: 'error-popup',
      message: this.getTranslation("Incorrect Username or Password"),
      buttons: [this.getTranslation("Close")]
    });

    await alert.present();
  }

  redirectToRegistrationPage() {
    console.log('User register function called');
    console.log(this.initialLoad);
    this.initialLoad=false;
    console.log(this.initialLoad);
    this.router.navigate['/tabs/register'];
  }

  async presentAlertForRegistration() {
    const alert = await this.alertController.create({
      cssClass: 'error-popup',
      message: this.getTranslation("Registration successful"),
      buttons: [{
        text: this.getTranslation("Close"),
        handler: () => {
          console.log('Registration successful');
          this.initialLoad=true;
        }
      }
      ]
    });
    await alert.present();
  }

  backToLoginPage() {
    this.initialLoad=true;
  }

  completeRegistration() {

    this.presentAlertPrompt();
    console.log(this.registrationForm.value);
    let  loginDetails = {
      userName: '',
      password:'',
      type: '',
      user_id: null
    };

    loginDetails.userName=this.registrationForm.controls.username.value;
    loginDetails.password=this.registrationForm.controls.password.value;
    
    let registrationDetails = {
      loginDetails: null,
      labourer:null,
      contractor:null
    };

    let labourer = {
      labourerId:null,
      loginDetails:null,
      name:null,
      aadhaarId:null,
      yearsOfExperience:null,
      age:null,
      address:null,
      gender:null,
      city:null,
      state:null,
      fieldOfSpecialization:null,
      avgRating:this.generateRandomRating(),
      withUsSince:null,
      noOfServicesProvided:this.generateRandomServicesProvided(),
      contactNo:null,
      imageUrl:null,
      groupCount:null,
      personOrGroup:null,
      dateOfBirth:null
    };

    let contractor = {
      contractorId:null,
      loginDetails:null,
      name:null,
      age:null,
      gender:null,
      aadhaarId:null,
      contactNo:null,
      noOfServicesUsed:this.generateRandomServicesProvided(),
      avgRating:this.generateRandomRating(),
      imageUrl:null,
      address:null,
      city:null,
      state:null,
      dateOfBirth:null
    };

    let currentYear=new Date().getFullYear();
    let birthYear : number = +this.registrationForm.controls.dateOfBirth.value.slice(0,4)
    let age=currentYear-birthYear;

    if(this.registeringAsLabourer())
    {
      loginDetails.type = 'L';
      if(this.registeringAsGroup()===false) {
        labourer.personOrGroup = 'P';
        labourer.groupCount = this.registrationForm.controls.groupStrength.value;
      }else {
        labourer.personOrGroup = 'G';
        labourer.groupCount = 1;
      }

      labourer.loginDetails = loginDetails;
      labourer.name = this.registrationForm.controls.name.value;
      labourer.withUsSince = 2020;
      labourer.aadhaarId = this.registrationForm.controls.aadhaarId.value;
      labourer.contactNo=this.registrationForm.controls.contactNo.value;
      labourer.address = this.registrationForm.controls.address.value;
      labourer.gender = this.registrationForm.controls.gender.value;
      labourer.city = this.registrationForm.controls.city.value;
      labourer.state = this.registrationForm.controls.state.value;
      labourer.age = age;
      labourer.dateOfBirth = this.registrationForm.controls.dateOfBirth.value;
      labourer.imageUrl=this.registrationForm.controls.imageUrl.value;
      labourer.fieldOfSpecialization = this.registrationForm.controls.specialization.value.join(",");
      labourer.yearsOfExperience = this.registrationForm.controls.yearsOfExperience.value;
      registrationDetails.loginDetails = loginDetails;
      registrationDetails.labourer = labourer;
    } else {
      loginDetails.type = 'C';
      contractor.loginDetails = loginDetails;
      contractor.name = this.registrationForm.controls.name.value;
      contractor.age = age;
      contractor.aadhaarId = this.registrationForm.controls.aadhaarId.value;
      contractor.contactNo=this.registrationForm.controls.contactNo.value;
      contractor.address = this.registrationForm.controls.address.value;
      contractor.gender = this.registrationForm.controls.gender.value;
      contractor.city = this.registrationForm.controls.city.value;
      contractor.state = this.registrationForm.controls.state.value;
      contractor.imageUrl=this.registrationForm.controls.imageUrl.value;
      contractor.dateOfBirth = this.registrationForm.controls.dateOfBirth.value;
      registrationDetails.loginDetails = loginDetails;
      registrationDetails.contractor = contractor;
    }

    console.log(registrationDetails);
    this.shramikHttpService.registerUser(registrationDetails).subscribe(data => {
          console.log(data);
        });
    
  }

  registeringAsGroup() {
    if(this.registrationForm.controls.personOrGroup.value=="Group") {
      return true;
    }
    return false;
  }

  registeringAsLabourer() {
    if(this.registrationForm.controls.type.value=="Worker") {
      return true;
    }
    return false;
  }
  
  checkPasswords() {
    return this.registrationForm.controls.password.value==this.registrationForm.controls.confirmPassword.value;     
  }

  hideShowPassword() {
    this.passwordType = this.passwordType === 'text' ? 'password' : 'text';
    this.passwordIcon = this.passwordIcon === 'eye-off' ? 'eye' : 'eye-off';
}

onKeyPress(event) {
  if ((event.keyCode >= 65 && event.keyCode <= 90) || (event.keyCode >= 97 && event.keyCode <= 122) || event.keyCode == 32 || event.keyCode == 46) {
      return true;
  }
  else {
      return false;
  }
}

hideShowLoginPassword() {
  this.loginPasswordType = this.loginPasswordType === 'text' ? 'password' : 'text';
  this.loginPasswordIcon = this.loginPasswordIcon === 'eye-off' ? 'eye' : 'eye-off';
}

async presentAlertPrompt() {
  const alert = await this.alertController.create({
    cssClass: 'my-custom-class',
    header: this.getTranslation("Please enter the OTP you received on your mobile number"),
    inputs: [
      {
        name: this.getTranslation("OTP"),
        type: 'password',
        placeholder: 'XXXX'
      }],
    buttons: [{
        text: this.getTranslation("Submit"),
        handler: () => {
          console.log('Confirm Ok');
          this.presentAlertForRegistration();
          this.loginForm.controls.username.setValue(this.registrationForm.controls.username.value);
          this.loginForm.controls.password.setValue(this.registrationForm.controls.password.value);
        }
      }
    ]
  });

  await alert.present();
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


