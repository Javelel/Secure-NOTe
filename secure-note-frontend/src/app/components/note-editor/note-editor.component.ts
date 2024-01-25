import {Component, EventEmitter, Output} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {MatInputModule} from "@angular/material/input";
import {MatButtonModule} from "@angular/material/button";
import {Router} from "@angular/router";
import {NoteService} from "../../services/note.service";
import {NoteRequest} from "../../models/note.request";
import {MatCheckboxModule} from "@angular/material/checkbox";
import {NgIf} from "@angular/common";

@Component({
  selector: 'app-note-editor',
  standalone: true,
  imports: [
    MatInputModule,
    MatButtonModule,
    ReactiveFormsModule,
    MatCheckboxModule,
    NgIf
  ],
  templateUrl: './note-editor.component.html',
  styleUrl: './note-editor.component.css'
})
export class NoteEditorComponent {

  noteForm: FormGroup;


  constructor(private formBuilder: FormBuilder,
              private readonly router: Router,
              private readonly noteService: NoteService) {
    this.noteForm = this.formBuilder.group({
      title: ['', Validators.required],
      content: ['', Validators.required],
      isEncrypted: [false],
      password: ['']
    });
  }

  onSubmit(): void {
    if (this.noteForm.valid) {
      const { isEncrypted, title, content, password } = this.noteForm.value;
      let noteToCreate: NoteRequest;
      if (!isEncrypted) {
        noteToCreate = {title, content, isEncrypted};
      } else {
        noteToCreate = {title, content, isEncrypted, password};
      }
      this.noteService.createNote(noteToCreate).subscribe({
        next: () => console.log('create note success'),
        error: () => console.log('create note failed')
      })
    }
  }
}
