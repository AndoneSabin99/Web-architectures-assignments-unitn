import { Component, OnInit } from '@angular/core';

import { Member } from 'src/app/models/member.model';

import { MemberService } from 'src/app/services/member.service';

@Component({
  selector: 'app-parliament',
  templateUrl: './parliament.component.html',
  styleUrls: ['./parliament.component.css']
})
export class ParliamentComponent implements OnInit {
  //list of members
  members?: Member[];

  constructor(private memberService: MemberService) { }

  ngOnInit(): void {
    //get the list of members
    this.memberService.getMembers().subscribe((members) => {
      //sort them by surname
      members.sort(function(member1,member2) {
        return ((member1.ParliamentaryName > member2.ParliamentaryName) ? 1 : -1);
      });
      //assign to this.members the sorted list of the members
      this.members = members;
    });
  }
}
