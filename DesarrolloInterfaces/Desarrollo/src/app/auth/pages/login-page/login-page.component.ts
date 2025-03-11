import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { User } from 'src/app/auth/interfaces/user.interface';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styles: []
})
export class LoginPageComponent {
  email: string = '';  // Variable para almacenar el email
  password: string = '';  // Variable para almacenar la contraseña

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  // Método para manejar el login
  onLogin(): void {
    console.log('Email:', this.email);  // Verifica que los valores de email y password se reciban correctamente
    console.log('Password:', this.password);

    // Llamamos al servicio para realizar el login
    this.authService.login(this.email, this.password)
      .subscribe({
        next: (user: User) => {
          if (user) {
            console.log('Login exitoso:', user);
            this.router.navigate(['/heroes']);  // Redirige a la página de héroes
          }
        },
        error: (err) => {
          console.error('Error de autenticación:', err);
          alert('Credenciales incorrectas');
        }
      });
  }
}
