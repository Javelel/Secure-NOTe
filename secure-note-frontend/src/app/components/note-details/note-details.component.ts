import { Component } from '@angular/core';
import {MatCardModule} from "@angular/material/card";
import {Observable} from "rxjs";
import {NoteResource} from "../../models/note.resource";
import {NoteService} from "../../services/note.service";
import {ActivatedRoute, Router} from "@angular/router";
import {AsyncPipe, NgIf} from "@angular/common";
import {SanitizeHtmlPipe} from "../../pipes/sanitize-html.pipe";

@Component({
  selector: 'app-note-details',
  standalone: true,
  imports: [
    MatCardModule,
    AsyncPipe,
    NgIf,
    SanitizeHtmlPipe
  ],
  templateUrl: './note-details.component.html',
  styleUrl: './note-details.component.css'
})
export class NoteDetailsComponent {
  noteId: string;
  decryptedContent!: string;
  note$: Observable<NoteResource>

  constructor(
    private readonly noteService: NoteService,
    private readonly route: ActivatedRoute,
    private readonly router: Router) {
    this.noteId = this.route.snapshot.params['id'];
    this.note$ = this.noteService.getNoteById(this.noteId);

    // @ts-ignore
    const navigationState = this.router.getCurrentNavigation().extras.state;
    if (navigationState) {
      this.decryptedContent = navigationState['decryptedContent'];
    } else {
      this.note$.subscribe(note => {
        this.decryptedContent = note.content;
      });
    }
  }
}
