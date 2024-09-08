
package Persistencia;

import Logica.Usuario;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.metamodel.SingularAttribute;


public class ControlPersistencia {
        CategoriaJpaController catJpa = new CategoriaJpaController();
        MovimientoInventarioJpaController movJpa = new MovimientoInventarioJpaController();
        ProductoJpaController proJpa = new ProductoJpaController();
        UsuarioJpaController userJpa = new UsuarioJpaController();

    public void crearUsuario(Usuario user) {
        userJpa.create(user);
    }

    public List<Usuario> traerUsuarios() {
       return userJpa.findUsuarioEntities();
    }

    public void eliminarUsuario(int id) {
            try {
                userJpa.destroy(id);
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(ControlPersistencia.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    public void editarUsuario(Usuario usu) {
            try {
                userJpa.edit(usu);
            } catch (Exception ex) {
                Logger.getLogger(ControlPersistencia.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    public Usuario traerUsuario(int id) {
        return userJpa.findUsuario(id);
    }
    


    public boolean authenticate(String nombreUsuario, String contrasenia) {
        List<Usuario> us = userJpa.findUsuarioEntities();
        
        for(Usuario u:us){
            if(u.getNombreUsuario().equals(nombreUsuario) && u.getContrasenia().equals(contrasenia)){
                return true;
            }   
        }
        return false;
    }
        
        
}
