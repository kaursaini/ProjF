import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { PrimeComponent } from './prime/prime.component';
import { SisComponent } from './sis/sis.component';
import { AppRoutingModule } from './/app-routing.module';
import { WorldComponent } from './world/world.component';

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    PrimeComponent,
    SisComponent,
    WorldComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
