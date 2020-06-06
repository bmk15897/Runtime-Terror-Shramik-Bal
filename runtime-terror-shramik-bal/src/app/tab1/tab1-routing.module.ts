import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Tab1Page } from './tab1.page';
import { ProfileComponent } from './profile/profile.component';
import { HistoryComponent } from './history/history.component';
import { SettingsComponent } from './settings/settings.component';
import { UpdateProfileComponent } from './update-profile/update-profile.component';
import { ChangePasswordComponent } from './change-password/change-password.component';

const routes: Routes = [
  {
    path: '',
    component: Tab1Page,
  },
  {
    path: 'profile',
    component: ProfileComponent,
  },
  {
    path: 'history',
    component: HistoryComponent,
  },
  {
    path: 'settings',
    component: SettingsComponent,
  },
  {
    path: 'updateProfile',
    component: UpdateProfileComponent,
  },
  {
    path: 'changePassword',
    component: ChangePasswordComponent,
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class Tab1PageRoutingModule {}
