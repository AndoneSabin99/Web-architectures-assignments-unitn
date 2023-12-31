import { Component, OnInit, Input } from '@angular/core';

import { Member } from 'src/app/models/member.model';

@Component({
  selector: 'app-member-card',
  templateUrl: './member-card.component.html',
  styleUrls: ['./member-card.component.css']
})
export class MemberCardComponent implements OnInit{
  //get member
  @Input() member!: Member;

  constructor() { }

  ngOnInit(): void {
  }
}
