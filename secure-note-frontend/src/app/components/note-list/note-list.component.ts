import {Component} from '@angular/core';
import {NoteResource} from "../../models/note.resource";
import {MatCardModule} from "@angular/material/card";
import {MatButtonModule} from "@angular/material/button";
import {AsyncPipe, NgForOf} from "@angular/common";
import {SanitizeHtmlPipe} from "../../pipes/sanitize-html.pipe";
import {MatDialog, MatDialogRef} from "@angular/material/dialog";
import {NotePasswordDialogComponent} from "../note-password-dialog/note-password-dialog.component";
import {Router} from "@angular/router";
import {Observable} from "rxjs";
import {NoteService} from "../../services/note.service";

@Component({
  selector: 'app-note-list',
  standalone: true,
  imports: [
    MatCardModule,
    MatButtonModule,
    NgForOf,
    SanitizeHtmlPipe,
    AsyncPipe
  ],
  templateUrl: './note-list.component.html',
  styleUrl: './note-list.component.css'
})
export class NoteListComponent {

  notes$: Observable<NoteResource[]>;

  constructor(private dialog: MatDialog, private readonly router: Router,  private readonly noteService: NoteService) {
    this.notes$ = noteService.getNotes();
  }



  protected deleteNote(id: string) {
    this.noteService.deleteNoteById(id).subscribe(() => {
      this.notes$ = this.noteService.getNotes();
    });
  }

  createNote() {
    this.router.navigate(['notes/create']).then(r => {});
  }

  editNote(note: NoteResource) {
    if(note.isEncrypted) {
      this.openPasswordDialog(note);
    } else {
      this.router.navigate(['notes', note.id]).then(r => {});
    }
  }

  openPasswordDialog(note: NoteResource): void {
    const dialogRef: MatDialogRef<NotePasswordDialogComponent> = this.dialog.open(NotePasswordDialogComponent, {
      width: '250px',
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.noteService.getEncryptedNoteById(note.id, result).subscribe(note => {
          console.log('Content');
          console.log(note.content);
          this.navigateToEdit(note.content, note);
        })
      } else {
        console.log('The dialog was closed');
      }
    });
  }
  navigateToEdit(decryptedContent: string, note: NoteResource): void {
    this.router.navigate(['notes', note.id], { state: { decryptedContent } }).then(r => {});
  }
}
