import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetAllTasksComponent } from './get-all-tasks.component';

describe('GetAllTasksComponent', () => {
  let component: GetAllTasksComponent;
  let fixture: ComponentFixture<GetAllTasksComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [GetAllTasksComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(GetAllTasksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
