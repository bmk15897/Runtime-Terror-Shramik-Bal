import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UniversalSharedService {

  language='HI';

  userLoggedIn=false;

  userType='CONTRACTOR';

  userObject = null;

  userObjectWorker=null

  userObjectContractor = null
  

  dictionaryEngToHindi = {
  	"Bharati":"भारती",
  	"Shreya":"श्रेया",
  	"Bramha Patil":"ब्रम्हा पाटिल",
  	"Suresh Kamlakar":"सुरेश कमलाकर",
  	"Venkat Murthy":"वेंकट मूर्ति",
  	"Mukesh Lal":"मुकेश लाल",
  	"Kamal Hasmukh":"कमल हसमुख",
  	"Sunita Kumari":"सुनीता कुमारी",
  	"Nihaal Sarode":"निहाल सरोद",
  	"Takshashil Babu":"तक्षशिल बाबू",
  	"Alam Khan":"आलम खान",
  	"Kavita Ambedkar":"कविता अम्बेडकर"
  }

  getEngToHindiNames(n) {
  	return this.dictionaryEngToHindi[n];
  }

  constructor() { }
}
