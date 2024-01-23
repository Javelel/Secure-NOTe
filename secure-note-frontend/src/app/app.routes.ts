import { Routes } from '@angular/router';

export const routes: Routes = [
  { path: 'login', loadComponent: () => import('./components/user-login/user-login.component').then((c) => c.UserLoginComponent) },
  { path: 'notes', loadComponent: () => import('./components/note-list/note-list.component').then((c) => c.NoteListComponent) },
  { path: 'notes/create', loadComponent: () => import('./components/note-editor/note-editor.component').then((c) => c.NoteEditorComponent) },
];
