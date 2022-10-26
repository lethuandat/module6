import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CampustCreateComponent } from './campus-create.component';

describe('CampustCreateComponent', () => {
  let component: CampustCreateComponent;
  let fixture: ComponentFixture<CampustCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CampustCreateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CampustCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
