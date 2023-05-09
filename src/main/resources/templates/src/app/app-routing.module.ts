import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AdminPageComponent } from './components/admin-page/admin-page.component';
import { LoginPageComponent } from './components/login-page/login-page.component';
import { HomeComponent } from './components/home/home.component';
import { ProfessorPageComponent } from './components/professor-page/professor-page.component';
import {StudentPageComponent} from './components/student-page/student-page.component';

const routes: Routes = [
  {path:"", redirectTo:"login", pathMatch:"full"},
  {path:"login", component:LoginPageComponent},
  {path:"home", component:HomeComponent},
  {path:"admin", component:AdminPageComponent},
  {path:"professor", component:ProfessorPageComponent},
  {path:"student", component:StudentPageComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
