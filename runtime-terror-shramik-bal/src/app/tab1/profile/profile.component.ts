import { Component, OnInit } from '@angular/core';
import {UniversalSharedService} from '../../services/shared-data-services/universal-shared.service';
import {BarcodeScanner} from '@ionic-native/barcode-scanner/ngx';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss'],
})
export class ProfileComponent implements OnInit {

  constructor(private universalSharedService: UniversalSharedService, private barcodeScanner : BarcodeScanner) { }

  public profileDetails={};

  qrData=null;
  scannedCode=null;
  createdCode=null;
  contentheight='280px';

  ngOnInit() {

    if(this.universalSharedService.userType=="CONTRACTOR") {
      this.profileDetails=this.universalSharedService.userObject;
      this.contentheight='240px';
    }
    else {
      this.profileDetails=this.universalSharedService.userObject;
      this.contentheight='300px';
    }
    console.log(this.universalSharedService.userObject);
 }
  
  openPicture() {
    console.log('Picture clicked');
  }

  generateTranslation(text) {
    if(this.universalSharedService.language=='HI') {
      switch(text) {
        case "My Profile" : {
          return "प्रोफ़ाइल";
        }
        case "Generate QR Code" : {
          return "क्यू आर कोड बनाएं";
        }
        case "Field of Specialization" : {
          return "विशेष वोग्याता";
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
        case "Aadhar ID Number" : {
          return "आधार कार्ड नंबर";
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
        case "CONTRACTOR" : {
          return "ठेकेदार";
        }
        case "LABOURER" : {
          return "मजदूर";
        }
        case "Number of services availed" : {
          return "ऍप के माद्यम से ली गयी सेवाओं की संख्या";
        }
      }
    }
    return text;
  }

  generateQRCode() {
    this.createdCode=this.qrData;
  }

  scanQrCode() {
    this.barcodeScanner.scan().then(barcodeData=> {
      this.scannedCode="ABCD";
    })
  }
}
