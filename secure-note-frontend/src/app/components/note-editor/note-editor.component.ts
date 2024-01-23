import {Component, EventEmitter, Output} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {MatInputModule} from "@angular/material/input";
import {MatButtonModule} from "@angular/material/button";

@Component({
  selector: 'app-note-editor',
  standalone: true,
  imports: [
    MatInputModule,
    MatButtonModule,
    ReactiveFormsModule
  ],
  templateUrl: './note-editor.component.html',
  styleUrl: './note-editor.component.css'
})
export class NoteEditorComponent {
  @Output() noteSubmitted = new EventEmitter<{ title: string; content: string }>();

  noteForm: FormGroup;

  constructor(private formBuilder: FormBuilder) {
    this.noteForm = this.formBuilder.group({
      title: ['', Validators.required],
      content: ['', Validators.required]
    });
  }

  onSubmit(): void {
    if (this.noteForm.valid) {
      const { title, content } = this.noteForm.value;
      this.noteSubmitted.emit({ title, content });
      this.noteForm.reset();
    }
  }
}
