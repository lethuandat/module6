import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CampustListComponent } from './campus-list.component';

describe('CampustListComponent', () => {
  let component: CampustListComponent;
  let fixture: ComponentFixture<CampustListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CampustListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CampustListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
