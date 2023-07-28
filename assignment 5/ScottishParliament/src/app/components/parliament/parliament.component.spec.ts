import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ParliamentComponent } from './parliament.component';

describe('ParliamentComponent', () => {
  let component: ParliamentComponent;
  let fixture: ComponentFixture<ParliamentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ParliamentComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ParliamentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
