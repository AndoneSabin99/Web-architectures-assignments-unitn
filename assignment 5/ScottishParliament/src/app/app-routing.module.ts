import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MemberComponent } from './components/member/member.component';
import { ErrorComponent } from './components/error/error.component';
import { ParliamentComponent } from './components/parliament/parliament.component';

const routes: Routes = [
  { path: 'parliament', component: ParliamentComponent},
  { path: 'member', component: MemberComponent},
  { path: '', redirectTo: 'parliament', pathMatch: 'full'},
  { path: '**', component: ErrorComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
