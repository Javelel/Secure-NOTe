export class UserRegister {
  constructor(
    public readonly email: string,
    public readonly password: string,
    public readonly passwordConfirm: string
  ) {}
}
