import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {HttpClientModule} from "@angular/common/http";
import {Apollo, ApolloModule} from "apollo-angular";
import {HttpLink, HttpLinkModule} from "apollo-angular-link-http";
import {InMemoryCache} from "apollo-cache-inmemory";
import {ReactiveFormsModule} from "@angular/forms";
import { GardenComponent } from './garden/garden.component';

@NgModule({
  declarations: [
    AppComponent,
    GardenComponent
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    HttpClientModule,
    ApolloModule,
    HttpLinkModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {

  constructor(apollo: Apollo, httpLink: HttpLink) {
    apollo.create({
      link: httpLink.create({}),
      cache: new InMemoryCache()
    });
  }

}
