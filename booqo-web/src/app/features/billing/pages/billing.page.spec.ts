import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BillingPage } from './billing.page';

describe('BillingPage', () => {
  let component: BillingPage;
  let fixture: ComponentFixture<BillingPage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BillingPage]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BillingPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
