/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Logica.Categoria;
import Logica.MovimientoInventario;
import Logica.Producto;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Usuario
 */
public class ProductoJpaController implements Serializable {

    public ProductoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public ProductoJpaController() {
        emf = Persistence.createEntityManagerFactory("persistence_unit");
    }

    public void create(Producto producto) {
        if (producto.getMovimientos() == null) {
            producto.setMovimientos(new ArrayList<MovimientoInventario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Categoria categoria = producto.getCategoria();
            if (categoria != null) {
                categoria = em.getReference(categoria.getClass(), categoria.getId());
                producto.setCategoria(categoria);
            }
            List<MovimientoInventario> attachedMovimientos = new ArrayList<MovimientoInventario>();
            for (MovimientoInventario movimientosMovimientoInventarioToAttach : producto.getMovimientos()) {
                movimientosMovimientoInventarioToAttach = em.getReference(movimientosMovimientoInventarioToAttach.getClass(), movimientosMovimientoInventarioToAttach.getId());
                attachedMovimientos.add(movimientosMovimientoInventarioToAttach);
            }
            producto.setMovimientos(attachedMovimientos);
            em.persist(producto);
            if (categoria != null) {
                categoria.getProductos().add(producto);
                categoria = em.merge(categoria);
            }
            for (MovimientoInventario movimientosMovimientoInventario : producto.getMovimientos()) {
                Producto oldProductoOfMovimientosMovimientoInventario = movimientosMovimientoInventario.getProducto();
                movimientosMovimientoInventario.setProducto(producto);
                movimientosMovimientoInventario = em.merge(movimientosMovimientoInventario);
                if (oldProductoOfMovimientosMovimientoInventario != null) {
                    oldProductoOfMovimientosMovimientoInventario.getMovimientos().remove(movimientosMovimientoInventario);
                    oldProductoOfMovimientosMovimientoInventario = em.merge(oldProductoOfMovimientosMovimientoInventario);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Producto producto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto persistentProducto = em.find(Producto.class, producto.getId());
            Categoria categoriaOld = persistentProducto.getCategoria();
            Categoria categoriaNew = producto.getCategoria();
            List<MovimientoInventario> movimientosOld = persistentProducto.getMovimientos();
            List<MovimientoInventario> movimientosNew = producto.getMovimientos();
            if (categoriaNew != null) {
                categoriaNew = em.getReference(categoriaNew.getClass(), categoriaNew.getId());
                producto.setCategoria(categoriaNew);
            }
            List<MovimientoInventario> attachedMovimientosNew = new ArrayList<MovimientoInventario>();
            for (MovimientoInventario movimientosNewMovimientoInventarioToAttach : movimientosNew) {
                movimientosNewMovimientoInventarioToAttach = em.getReference(movimientosNewMovimientoInventarioToAttach.getClass(), movimientosNewMovimientoInventarioToAttach.getId());
                attachedMovimientosNew.add(movimientosNewMovimientoInventarioToAttach);
            }
            movimientosNew = attachedMovimientosNew;
            producto.setMovimientos(movimientosNew);
            producto = em.merge(producto);
            if (categoriaOld != null && !categoriaOld.equals(categoriaNew)) {
                categoriaOld.getProductos().remove(producto);
                categoriaOld = em.merge(categoriaOld);
            }
            if (categoriaNew != null && !categoriaNew.equals(categoriaOld)) {
                categoriaNew.getProductos().add(producto);
                categoriaNew = em.merge(categoriaNew);
            }
            for (MovimientoInventario movimientosOldMovimientoInventario : movimientosOld) {
                if (!movimientosNew.contains(movimientosOldMovimientoInventario)) {
                    movimientosOldMovimientoInventario.setProducto(null);
                    movimientosOldMovimientoInventario = em.merge(movimientosOldMovimientoInventario);
                }
            }
            for (MovimientoInventario movimientosNewMovimientoInventario : movimientosNew) {
                if (!movimientosOld.contains(movimientosNewMovimientoInventario)) {
                    Producto oldProductoOfMovimientosNewMovimientoInventario = movimientosNewMovimientoInventario.getProducto();
                    movimientosNewMovimientoInventario.setProducto(producto);
                    movimientosNewMovimientoInventario = em.merge(movimientosNewMovimientoInventario);
                    if (oldProductoOfMovimientosNewMovimientoInventario != null && !oldProductoOfMovimientosNewMovimientoInventario.equals(producto)) {
                        oldProductoOfMovimientosNewMovimientoInventario.getMovimientos().remove(movimientosNewMovimientoInventario);
                        oldProductoOfMovimientosNewMovimientoInventario = em.merge(oldProductoOfMovimientosNewMovimientoInventario);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = producto.getId();
                if (findProducto(id) == null) {
                    throw new NonexistentEntityException("The producto with id " + id + " no longer exists.");
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
            Producto producto;
            try {
                producto = em.getReference(Producto.class, id);
                producto.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The producto with id " + id + " no longer exists.", enfe);
            }
            Categoria categoria = producto.getCategoria();
            if (categoria != null) {
                categoria.getProductos().remove(producto);
                categoria = em.merge(categoria);
            }
            List<MovimientoInventario> movimientos = producto.getMovimientos();
            for (MovimientoInventario movimientosMovimientoInventario : movimientos) {
                movimientosMovimientoInventario.setProducto(null);
                movimientosMovimientoInventario = em.merge(movimientosMovimientoInventario);
            }
            em.remove(producto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Producto> findProductoEntities() {
        return findProductoEntities(true, -1, -1);
    }

    public List<Producto> findProductoEntities(int maxResults, int firstResult) {
        return findProductoEntities(false, maxResults, firstResult);
    }

    private List<Producto> findProductoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Producto.class));
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

    public Producto findProducto(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Producto.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Producto> rt = cq.from(Producto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
