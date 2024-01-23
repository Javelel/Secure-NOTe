import { Component } from '@angular/core';
import {NoteResource} from "../../models/note.resource";
import {MatCardModule} from "@angular/material/card";
import {MatButtonModule} from "@angular/material/button";
import {NgForOf} from "@angular/common";
import {SanitizeHtmlPipe} from "../../pipes/sanitize-html.pipe";

@Component({
  selector: 'app-note-list',
  standalone: true,
  imports: [
    MatCardModule,
    MatButtonModule,
    NgForOf,
    SanitizeHtmlPipe
  ],
  templateUrl: './note-list.component.html',
  styleUrl: './note-list.component.css'
})
export class NoteListComponent {
  notes = [
    new NoteResource('uuid1', 'My note 1', 'My content<b> 1 </b>', 'user1'),
    new NoteResource('uuid2', 'My note 2', 'My <b> content 2 </b>', 'user1'),
    new NoteResource('uuid2', 'My note 2', '<i>My</i> content 2', 'user1'),
    new NoteResource('uuid2', 'My note 2', 'My content 2', 'user1'),
    new NoteResource('uuid2', 'My note 2', 'My content 2', 'user1'),
    new NoteResource('uuid2', 'My note 2', 'My content 2', 'user1'),
    new NoteResource('uuid2', 'My note 2', 'My content 2', 'user1'),
  ];

  protected deleteNote(id: string) {
    console.log(`delete note ${id}`);
  }

  createNote() {
    console.log('create note');
  }

  editNote(id: string) {
    console.log(`edit note ${id}`);
  }
}
