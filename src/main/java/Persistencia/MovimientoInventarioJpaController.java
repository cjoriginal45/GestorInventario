/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Logica.MovimientoInventario;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Logica.Producto;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Usuario
 */
public class MovimientoInventarioJpaController implements Serializable {

    public MovimientoInventarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
      public MovimientoInventarioJpaController() {
        emf = Persistence.createEntityManagerFactory("persistence_unit");
    }

    public void create(MovimientoInventario movimientoInventario) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto producto = movimientoInventario.getProducto();
            if (producto != null) {
                producto = em.getReference(producto.getClass(), producto.getId());
                movimientoInventario.setProducto(producto);
            }
            em.persist(movimientoInventario);
            if (producto != null) {
                producto.getMovimientos().add(movimientoInventario);
                producto = em.merge(producto);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MovimientoInventario movimientoInventario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MovimientoInventario persistentMovimientoInventario = em.find(MovimientoInventario.class, movimientoInventario.getId());
            Producto productoOld = persistentMovimientoInventario.getProducto();
            Producto productoNew = movimientoInventario.getProducto();
            if (productoNew != null) {
                productoNew = em.getReference(productoNew.getClass(), productoNew.getId());
                movimientoInventario.setProducto(productoNew);
            }
            movimientoInventario = em.merge(movimientoInventario);
            if (productoOld != null && !productoOld.equals(productoNew)) {
                productoOld.getMovimientos().remove(movimientoInventario);
                productoOld = em.merge(productoOld);
            }
            if (productoNew != null && !productoNew.equals(productoOld)) {
                productoNew.getMovimientos().add(movimientoInventario);
                productoNew = em.merge(productoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = movimientoInventario.getId();
                if (findMovimientoInventario(id) == null) {
                    throw new NonexistentEntityException("The movimientoInventario with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MovimientoInventario movimientoInventario;
            try {
                movimientoInventario = em.getReference(MovimientoInventario.class, id);
                movimientoInventario.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The movimientoInventario with id " + id + " no longer exists.", enfe);
            }
            Producto producto = movimientoInventario.getProducto();
            if (producto != null) {
                producto.getMovimientos().remove(movimientoInventario);
                producto = em.merge(producto);
            }
            em.remove(movimientoInventario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MovimientoInventario> findMovimientoInventarioEntities() {
        return findMovimientoInventarioEntities(true, -1, -1);
    }

    public List<MovimientoInventario> findMovimientoInventarioEntities(int maxResults, int firstResult) {
        return findMovimientoInventarioEntities(false, maxResults, firstResult);
    }

    private List<MovimientoInventario> findMovimientoInventarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MovimientoInventario.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public MovimientoInventario findMovimientoInventario(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MovimientoInventario.class, id);
        } finally {
            em.close();
        }
    }

    public int getMovimientoInventarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MovimientoInventario> rt = cq.from(MovimientoInventario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
