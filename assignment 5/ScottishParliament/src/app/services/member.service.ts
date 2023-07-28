import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";

import { Member } from '../models/member.model';
import { MemberParty } from '../models/memberparty.model';
import { Party } from '../models/party.model';
import { Website } from '../models/website.model';

import { Observable, throwError, catchError, map, switchMap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MemberService {

  private url = 'https://data.parliament.scot/api/';

  constructor(private http:HttpClient) { }

  getMembers(): Observable<Member[]> {
    return this.http.get<Member[]>(this.url + 'members');
  }

  getMemberByID(id: number): Observable<Member> {
    return this.http.get<Member>(this.url + 'members/' + id)
      .pipe(
        catchError(this.handleError)
      );
  }

  getMemberParties(): Observable<MemberParty[]> {
    return this.http.get<MemberParty[]>(this.url + 'memberparties');
  }

  getPartyByID(id: number): Observable<Party> {
    return this.http.get<Party>(this.url + 'parties/' + id);
  }

  getPartyByMemberID(id: number): Observable<Party> {
    return this.getMemberParties().pipe(
      map(memberParties => memberParties.filter(memberParty => memberParty.PersonID == id)),
      switchMap((memberParties) => {
        return this.getPartyByID(memberParties[0].PartyID);
      })
    );
  }

  getWebsites(): Observable<Website[]> {
    return this.http.get<Website[]>(this.url + 'websites');
  }

  getWebsitesByMemberID(id: number): Observable<Website[]> {
    return this.getWebsites().pipe(
      map(websites => websites.filter(website => website.PersonID == id))
    );
  }

  //function for handling errors
  private handleError(error: HttpErrorResponse) {
    if (error.status === 0) {
      console.error('An error occurred:', error.error);
    } else {
      console.error(
        `Backend returned code ${error.status}, body was: `, error.error);
    }
    return throwError( () => new Error('Something bad happened; please try again later.'));
  }
}
