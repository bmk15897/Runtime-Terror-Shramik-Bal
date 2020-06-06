import { IonicModule } from '@ionic/angular';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { TabsPageRoutingModule } from './tabs-routing.module';

import { TabsPage } from './tabs.page';
import { LoginPageComponent } from './login-page/login-page.component';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MaterialModule} from '../material.module';
import { HttpClientModule} from '@angular/common/http';
import {ShramikHttpService} from '../services/http-services/shramik-http.service';
import { RegistrationComponent } from './registration/registration.component';
@NgModule({
  imports: [
    IonicModule,
    CommonModule,
    FormsModule,
    TabsPageRoutingModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MaterialModule,
    HttpClientModule
  ],
  declarations: [TabsPage,LoginPageComponent,RegistrationComponent],
  providers:[ShramikHttpService]
})
export class TabsPageModule {}
