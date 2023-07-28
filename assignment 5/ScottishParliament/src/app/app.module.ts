
// imports
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { ParliamentComponent } from './components/parliament/parliament.component';
import { MemberComponent } from './components/member/member.component';
import { MemberCardComponent } from './components/member-card/member-card.component';
import { ErrorComponent } from './components/error/error.component';
import { NamePipe } from './pipes/name/name.pipe';
import { PhotoPipe } from './pipes/photo/photo.pipe';
import {RouterLink, RouterOutlet} from "@angular/router";
import { AppRoutingModule } from './app-routing.module';


// @NgModule decorator with its metadata
@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    ParliamentComponent,
    MemberComponent,
    MemberCardComponent,
    ErrorComponent,
    NamePipe,
    PhotoPipe,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    RouterOutlet,
    RouterLink,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }



/*
Copyright Google LLC. All Rights Reserved.
Use of this source code is governed by an MIT-style license that
can be found in the LICENSE file at https://angular.io/license
*/
