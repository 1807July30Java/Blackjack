import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { PlayComponent } from './components/play/play.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';

export const routes: Routes = [
    { path: '', redirectTo: '/dash',pathMatch: 'full' },
    { path: 'login', component: LoginComponent },
    { path: 'register', component: RegisterComponent },
    { path: 'dash',component: DashboardComponent },
    { path: 'play', component: PlayComponent },
    { path: '**', redirectTo: '/dash'}
]

@NgModule({
    imports: [ RouterModule.forRoot(routes,{useHash:true}) ],
    exports: [ RouterModule ]
})

export class AppRoutingModule {}
