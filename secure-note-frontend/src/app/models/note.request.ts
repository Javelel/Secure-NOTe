export class NoteRequest {
  constructor(
    public readonly title: string,
    public readonly content: string,
    public readonly creator: string,
  ) {}
}