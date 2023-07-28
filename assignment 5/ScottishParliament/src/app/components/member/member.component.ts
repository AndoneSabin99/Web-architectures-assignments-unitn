import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { Member } from 'src/app/models/member.model';
import { Party } from 'src/app/models/party.model';
import { Website } from 'src/app/models/website.model';
import { MemberService } from 'src/app/services/member.service';


@Component({
  selector: 'app-member',
  templateUrl: './member.component.html',
  styleUrls: ['./member.component.css']
})
export class MemberComponent implements OnInit{
  member!: Member;
  party!: Party;
  websites!: Website[];

  constructor(
    private memberService: MemberService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    //get the id from the parameters of the query
    this.route.queryParams.subscribe(params => {
      const id = params['id'];
      //if undefined, redirect the user to error page
      //otherwise get the details of the member
      if(id === undefined) {
        this.router.navigate(['/error-page']);
      } else {
        this.getMemberById(id);
        this.getPartyByMember(id);
        this.getWebsitesByMember(id);
      }
    });
  }

  //get the member by its id
  getMemberById(id: number): void {
    this.memberService.getMemberByID(id).subscribe({
      //get the member and assign it to this.member
      next: (member) => {
        this.member = member;
      },
      //if there is an error, redirect to error page
      error: (error) => {
        this.router.navigate(['/error-page']);
      }
    });
  }

  //get party by the member id
  getPartyByMember(id: number): void {
    this.memberService.getPartyByMemberID(id).subscribe((party) => {
      this.party = party;
    });
  }

  //get websites by the member id
  getWebsitesByMember(id: number): void {
    this.memberService.getWebsitesByMemberID(id).subscribe((websites) => {
      this.websites = websites;
    })
  }
}
