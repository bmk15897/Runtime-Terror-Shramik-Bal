import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ShramikHttpService {

  baseUrl: string = `http://localhost:8080/shramik-bal`;

  constructor(private http : HttpClient) { }

  public validateUserThroughLogin(loginDetails): Observable<any> {
    return this.http.post(this.baseUrl+`/signin`,loginDetails);
  }

  public registerUser(registrationDetails): Observable<any> {
    return this.http.post(this.baseUrl+`/signup`,registrationDetails);
  }

  public updateProfile(profileDetails): Observable<any> {
  	return this.http.post(this.baseUrl+`/update-profile-fields`,profileDetails);
  }

  public updateProfilePassword(pwdDetails): Observable<any> {
  	return this.http.post(this.baseUrl+`/update-profile-password`,pwdDetails);
  }

  public getLabourerDetails(labourerId): Observable<any> {
 	return this.http.post(this.baseUrl+`/get-labourer-details`,labourerId);
  }  

  public getContractorDetails(contractorId): Observable<any> {
  	return this.http.post(this.baseUrl+`/get-contractor-details`,contractorId);
  }  

  public postContractorRequirement(contractorRequirement):Observable<any> {
  	return this.http.post(this.baseUrl+`/post-contractor-requirement`,contractorRequirement);
  }

  public addWorkerApplication(contractorReqWA):Observable<any> {
  	return this.http.post(this.baseUrl+`/post-worker-application`,contractorReqWA);
  }

  public approveWorkerApplication(approveWA):Observable<any> {
  	return this.http.post(this.baseUrl+`/approve-worker-application`,approveWA);
  }

  public declineWorkerApplication(approveWA):Observable<any> {
    return this.http.post(this.baseUrl+`/decline-worker-application`,approveWA);
  }

  public findLabourersByField(field):Observable<any> {
  	return this.http.post(this.baseUrl+`/get-labourers-by-field`,field);
  }

  public findLabourersByCity(city):Observable<any> {
  	return this.http.post(this.baseUrl+`/get-labourers-by-city`,city);
  }

  public findLabourersByState(state):Observable<any> {
  	return this.http.post(this.baseUrl+`/get-labourers-by-state`,state);
  }

  public findLabourersByFieldAndCity(FieldAndCity):Observable<any> {
  	return this.http.post(this.baseUrl+`/get-labourers-by-field-and-city`,FieldAndCity);
  }

  public findLabourersByFieldAndState(FieldAndState):Observable<any> {
  	return this.http.post(this.baseUrl+`/get-labourers-by-field-and-state`,FieldAndState);
  }

  public findWorkerApplicationsByLabourer(labourerId): Observable<any> {
  	return this.http.post(this.baseUrl+`/get-WA-by-labourer`,labourerId);
  }

  findWorkerApplicationsByLabourerForHistory(labourerId): Observable<any> {
    return this.http.post(this.baseUrl+`/get-WA-by-labourer-history`,labourerId);
  }

  public getWAForContractorRequirements(contractorRequirementId): Observable<any> {
	return this.http.post(this.baseUrl+`/get-WA-by-contractor-requirement`,contractorRequirementId);
  }

  public getWAForContractor(contractorUserName): Observable<any> {
	return this.http.post(this.baseUrl+`/get-WA-by-contractor`,contractorUserName);
  }  

  public findContractorRequirementsByContractor(contractorId): Observable<any> {
  	return this.http.post(this.baseUrl+`/get-CR-by-contractor`,contractorId);
  }

  public getCRAccordingToSiteCity(City): Observable<any> {
	return this.http.post(this.baseUrl+`/get-CR-by-site-city`,City);
  }

  public getCRAccordingToField(Field): Observable<any> {
	return this.http.post(this.baseUrl+`/get-CR-by-field`,Field);
  }
  
  public getCRAccordingToFieldAndCity(FieldAndCity): Observable<any> {
	return this.http.post(this.baseUrl+`/get-CR-by-site-city-and-specialization`,FieldAndCity);
  }

  public getCRAccordingToFieldAndState(FieldAndState): Observable<any> {
	return this.http.post(this.baseUrl+`/get-CR-by-site-state-and-specialization`,FieldAndState);
  }

  public getAllActiveContractorRequirementsforContractor(contractorUserName):Observable<any> {
  	return this.http.post(this.baseUrl+`/get-active-CR-by-contractor`,contractorUserName);
  }

  public getAllInActiveContractorRequirementsforContractor(contractorUserName):Observable<any> {
  	return this.http.post(this.baseUrl+`/get-inactive-CR-by-contractor`,contractorUserName);
  }

  public getCRForLabourerHome(reqDets):Observable<any> {
        return this.http.post(this.baseUrl+`/get-CR-for-labourer-home`,reqDets);
  }
}
