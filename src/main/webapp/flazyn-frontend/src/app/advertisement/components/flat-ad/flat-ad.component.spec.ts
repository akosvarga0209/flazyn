import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FlatAdComponent } from './flat-ad.component';

describe('FlatAdComponent', () => {
  let component: FlatAdComponent;
  let fixture: ComponentFixture<FlatAdComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FlatAdComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FlatAdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
