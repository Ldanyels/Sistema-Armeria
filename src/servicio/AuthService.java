package servicio;

import modelo.Usuario;

public class AuthService {

    private Usuario[] usuarios;
    private int totalUsuarios = 0;
    private Usuario sesionActiva = null;

    public AuthService() {
        usuarios = new Usuario[20];
        // Usuarios por defecto del sistema
        agregarUsuario(new Usuario("admin",  "admin123",  "ADMIN",  "Administrador"));
        agregarUsuario(new Usuario("armero", "armero123", "ARMERO", "Armero de Turno"));
    }

    public void agregarUsuario(Usuario u) {
        usuarios[totalUsuarios++] = u;
    }

    // Intento de login — retorna true si las credenciales son correctas
    public boolean login(String username, String password) {
        for (int i = 0; i < totalUsuarios; i++) {
            if (usuarios[i].getUsername().equals(username) &&
                usuarios[i].getPassword().equals(password)) {
                sesionActiva = usuarios[i];
                return true;
            }
        }
        return false; // credenciales incorrectas
    }

    public void    logout()             { sesionActiva = null; }
    public boolean haySesion()          { return sesionActiva != null; }
    public Usuario getUsuarioActual()   { return sesionActiva; }
    public boolean esAdmin()            {
        return sesionActiva != null && sesionActiva.esAdmin();
    }
}