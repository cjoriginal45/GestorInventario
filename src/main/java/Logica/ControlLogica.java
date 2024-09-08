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
        return control.authenticate(nombreUsuario,contrasenia);
    }

}
