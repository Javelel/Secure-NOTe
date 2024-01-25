import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {NoteResource} from "../models/note.resource";
import {NoteRequest} from "../models/note.request";

@Injectable({
  providedIn: 'root'
})
export class NoteService {

  constructor(private httpClient: HttpClient) {
  }
  private baseUrl = 'http://localhost:8080/api/notes';

  getNotes(): Observable<NoteResource[]> {
    const options = {
      withCredentials: true
    };
    return this.httpClient.get<NoteResource[]>(this.baseUrl, options);
  }

  getNoteById(id: string): Observable<NoteResource> {
    const options = {
      withCredentials: true
    };
    return this.httpClient.get<NoteResource>(`${this.baseUrl}/${id}`, options);
  }

  getEncryptedNoteById(id: string, password: string): Observable<NoteResource> {
    const options = {
      withCredentials: true
    };
    return this.httpClient.post<NoteResource>(`${this.baseUrl}/${id}`, {password}, options)
  }

  createNote(noteRequest: NoteRequest) {
    const options = {
      withCredentials: true
    };
    console.log("dodaje notatke");
    return this.httpClient.post<NoteRequest>(`${this.baseUrl}`, noteRequest, options);
  }

  deleteNoteById(id: string) {
    const options = {
      withCredentials: true
    };
    return this.httpClient.delete(`${this.baseUrl}/${id}`, options);
  }
}
