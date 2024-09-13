package Logica;

import Persistencia.ControlPersistencia;
import java.util.List;
import javax.persistence.metamodel.SingularAttribute;

/**
 *
 * @author Usuario
 */
public class ControlLogica {

    ControlPersistencia control = new ControlPersistencia();

    //crud usuario
    public void crearUsuario(Usuario user) {
        control.crearUsuario(user);
    }

    public List<Usuario> traerUsuarios() {
        return control.traerUsuarios();
    }

    public void eliminarUsuario(int id) {
        control.eliminarUsuario(id);
    }

    public void editarUsuario(Usuario usu) {
        control.editarUsuario(usu);
    }

    public Usuario traerUsuario(int id) {
        return control.traerUsuario(id);
    }

    public boolean authenticate(String nombreUsuario, String contrasenia) {
        return control.authenticate(nombreUsuario, contrasenia);
    }

    public Rol authenticateAndGetRole(String username, String password) {
        // Lógica para autenticar al usuario y obtener su rol
        Usuario user = control.traerUsuario(username, password);
        if (user != null) {
            return user.getRol();  // Devuelve el rol del usuario
        } else {
            return null;  // Autenticación fallida
        }
    }

}
