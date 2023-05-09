import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {HTTP_INTERCEPTORS, HttpClient, HttpClientModule} from '@angular/common/http';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';

import {LoginPageComponent} from './components/login-page/login-page.component';
import {HomeComponent} from './components/home/home.component';
import {AdminPageComponent} from './components/admin-page/admin-page.component';
import {ProfessorPageComponent} from './components/professor-page/professor-page.component';
import {LoginSuccessComponent} from './components/login-success/login-success.component';
import {CorsInterceptor} from "./cors.interceptor";

@NgModule({
    declarations: [
        AppComponent,
        LoginPageComponent,
        HomeComponent,
        AdminPageComponent,
        ProfessorPageComponent,
        LoginSuccessComponent,
    ],
    imports: [
        BrowserModule,
        HttpClientModule,
        AppRoutingModule
    ],
    providers: [
        {provide: HTTP_INTERCEPTORS, useClass: CorsInterceptor, multi: true}
    ],
    bootstrap: [AppComponent]
})

export class AppModule {
    constructor(private httpClient: HttpClient) {
        // Set CORS configuration
        // this.configureCORS();
    }
    //
    // private configureCORS(): void {
    //     this.httpClient.defaults.withCredentials = true;
    //     this.httpClient.defaults.headers['Access-Control-Allow-Origin'] = '*';
    //     // You can also set other CORS-related headers if needed
    // }
}
