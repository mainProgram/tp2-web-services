import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddEditClasseComponent } from './add-edit-classe.component';

describe('AddEditClasseComponent', () => {
  let component: AddEditClasseComponent;
  let fixture: ComponentFixture<AddEditClasseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddEditClasseComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddEditClasseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
