import { IonicModule } from '@ionic/angular';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule,ReactiveFormsModule } from '@angular/forms';
import { Tab1Page } from './tab1.page';
import { ProfileComponent } from './profile/profile.component';
import { SettingsComponent } from './settings/settings.component';
import { HistoryComponent } from './history/history.component';
import { ExploreContainerComponentModule } from '../explore-container/explore-container.module';
import {MatFormFieldModule} from '@angular/material/form-field';

import { Tab1PageRoutingModule } from './tab1-routing.module';
import { UpdateProfileComponent } from './update-profile/update-profile.component';
import { ChangePasswordComponent } from './change-password/change-password.component';
import {NgxQRCodeModule} from 'ngx-qrcode2';
import {BarcodeScanner} from '@ionic-native/barcode-scanner/ngx';

@NgModule({
  imports: [
    IonicModule,
    CommonModule,
    FormsModule,
    ExploreContainerComponentModule,
    Tab1PageRoutingModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    NgxQRCodeModule
  ],
  declarations: [Tab1Page,ProfileComponent,SettingsComponent,HistoryComponent,ChangePasswordComponent,UpdateProfileComponent],
  providers : [ BarcodeScanner]

})
export class Tab1PageModule {}
