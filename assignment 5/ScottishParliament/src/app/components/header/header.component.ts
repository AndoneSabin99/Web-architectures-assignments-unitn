import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit{

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  //function that loads the list of all members, i.e. redirects the user to the list page
  loadMembers() {
    this.router.navigate(['']);
  }
}
