import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListProfesseurComponent } from './list-professeur.component';

describe('ListProfesseurComponent', () => {
  let component: ListProfesseurComponent;
  let fixture: ComponentFixture<ListProfesseurComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListProfesseurComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListProfesseurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
