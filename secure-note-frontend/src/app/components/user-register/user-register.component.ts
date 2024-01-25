import { Component } from '@angular/core';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {MatButtonModule} from "@angular/material/button";
import {MatCardModule} from "@angular/material/card";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {UserRegister} from "../../models/user-register";
import {UserService} from "../../services/user.service";

@Component({
  selector: 'app-user-register',
  standalone: true,
    imports: [
        FormsModule,
        MatButtonModule,
        MatCardModule,
        MatFormFieldModule,
        MatInputModule,
        ReactiveFormsModule
    ],
  templateUrl: './user-register.component.html',
  styleUrl: './user-register.component.css'
})
export class UserRegisterComponent {
  registerForm!: FormGroup;

  constructor(private formBuilder: FormBuilder, private readonly userService: UserService) {}

  ngOnInit(): void {
    this.registerForm! = this.formBuilder.group({
      username: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
      confirmPassword: ['', Validators.required]
    });
  }

  onRegister() {
    const email = this.registerForm.get('username')?.value;
    const password = this.registerForm.get('password')?.value;
    const passwordConfirm = this.registerForm.get('confirmPassword')?.value;
    const userRegister: UserRegister = {email, password, passwordConfirm};
    this.userService.register(userRegister).subscribe({
      next: () => console.log('register success'),
      error: () => console.log('register failed')
    })
  }
}
