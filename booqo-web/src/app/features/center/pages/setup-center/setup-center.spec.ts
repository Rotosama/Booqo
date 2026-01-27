import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SetupCenter } from './setup-center.page';

describe('SetupCenter', () => {
  let component: SetupCenter;
  let fixture: ComponentFixture<SetupCenter>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SetupCenter],
    }).compileComponents();

    fixture = TestBed.createComponent(SetupCenter);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
