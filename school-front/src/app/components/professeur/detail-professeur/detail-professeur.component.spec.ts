import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailProfesseurComponent } from './detail-professeur.component';

describe('DetailProfesseurComponent', () => {
  let component: DetailProfesseurComponent;
  let fixture: ComponentFixture<DetailProfesseurComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DetailProfesseurComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DetailProfesseurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
