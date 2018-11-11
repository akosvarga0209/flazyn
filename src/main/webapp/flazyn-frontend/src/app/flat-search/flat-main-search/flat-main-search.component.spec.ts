import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FlatMainSearchComponent } from './flat-main-search.component';

describe('FlatMainSearchComponent', () => {
  let component: FlatMainSearchComponent;
  let fixture: ComponentFixture<FlatMainSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FlatMainSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FlatMainSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
