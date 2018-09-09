import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { PlayComponent } from './components/play/play.component';

export const routes: Routes = [
    { path: '', redirectTo: '/login',pathMatch: 'full' },
    { path: 'login', component: LoginComponent },
    { path: 'register', component: RegisterComponent },
    { path: 'play', component: PlayComponent },
    { path: '**', redirectTo: '/login'}
]

@NgModule({
    imports: [ RouterModule.forRoot(routes,{useHash:true}) ],
    exports: [ RouterModule ]
})

export class AppRoutingModule {}
