export class NoteResource {
  constructor(
    public readonly id: string,
    public readonly title: string,
    public readonly content: string,
    public readonly isEncrypted: boolean,
    public readonly authorName: string,
  ) {}
}
