import {Component, Inject} from '@angular/core';
import {
  MAT_DIALOG_DATA,
  MatDialogActions,
  MatDialogContent,
  MatDialogRef,
  MatDialogTitle
} from "@angular/material/dialog";
import {MatInputModule} from "@angular/material/input";
import {FormsModule} from "@angular/forms";
import {MatButtonModule} from "@angular/material/button";
import {NoteListComponent} from "../note-list/note-list.component";
import {NoteResource} from "../../models/note.resource";

@Component({
  selector: 'app-note-password-dialog',
  standalone: true,
  imports: [
    MatDialogContent,
    MatInputModule,
    FormsModule,
    MatDialogTitle,
    MatDialogActions,
    MatButtonModule
  ],
  templateUrl: './note-password-dialog.component.html',
  styleUrl: './note-password-dialog.component.css'
})
export class NotePasswordDialogComponent {
  password!: string;
  constructor(
    public dialogRef: MatDialogRef<NoteListComponent>,
  ) {}

  onCancelClick(): void {
    this.dialogRef.close();
  }

  onDecodeClick(): void {
    this.dialogRef.close(this.password);
  }
}
