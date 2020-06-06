import { IonicModule } from '@ionic/angular';
import { RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Tab4Page } from './tab4.page';
import { Tab4PageRoutingModule } from './tab4-routing.module'
import { NotificationProfileViewComponent } from './notification-profile-view/notification-profile-view.component';



@NgModule({
  declarations: [Tab4Page,NotificationProfileViewComponent],
  imports: [
    CommonModule,
    IonicModule,
    FormsModule,
    RouterModule.forChild([{ path: '', component: Tab4Page }]),
    Tab4PageRoutingModule,
  ]
})
export class Tab4Module { }
