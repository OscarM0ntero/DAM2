import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddPersonajeComponent } from './add-personaje.component';

describe('AddPersonajeComponent', () => {
  let component: AddPersonajeComponent;
  let fixture: ComponentFixture<AddPersonajeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddPersonajeComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddPersonajeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
