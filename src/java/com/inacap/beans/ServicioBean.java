package com.inacap.beans;

import com.inacap.entities.*;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author LeoGuitar
 */
@Stateless
public class ServicioBean implements ServicioBeanLocal {

    @PersistenceContext(unitName = "TiendaMascota2018PU")
    private EntityManager em;

    @Override
    public Usuario loggear(String nombreUser, String clave) {
        Usuario usuario = null;
        try {
            Query q = em.createNamedQuery("Usuario.loggear");
            q.setParameter("rutUser", nombreUser);
            q.setParameter("claveUser", clave);
            usuario = (Usuario) q.getSingleResult();
        } catch (Exception e) {
            System.out.println("Excepción.. rut=" + nombreUser + ", clave= " + clave);
            e.printStackTrace();
        } finally {
            return usuario;
        }
    }

    @Override
    public void save(Object o) {
        em.persist(o);
    }

    @Override
    public List<Categoria> getCategorias() {
        return em.createNamedQuery("Categoria.findAll").getResultList();
    }

    @Override
    public List<Producto> getProductos() {
        return em.createNamedQuery("Producto.findAll").getResultList();
    }

    /**
     * Este método sirve para forzar la actualización de la lista enlazada en
     * java
     *
     * @param o Objeto de la clase que se está actualizando.. el objeto unido al
     * recién insertado a la bdd
     */
    @Override
    public void sincronizar(Object o) {
        em.merge(o);
        em.flush();
    }

    @Override
    public Object buscar(Object id, Object o) {
        if (o instanceof Categoria) {
            int parsedId = (int) id;
            return em.find(Categoria.class, parsedId);
        } else if (o instanceof Usuario) {
            String parsedId = String.valueOf(id);
            return em.find(Usuario.class, parsedId);
        } else if (o instanceof Producto) {
            int parsedId = (int) id;
            return em.find(Producto.class, parsedId);
        } else {
            return null;
        }
    }

    @Override
    public void comprar(Venta venta) {
        try {
            em.persist(venta);
            for (Detalleventa dv : venta.getDetalleventaList()) {
                Producto p = (Producto) buscar(dv.getProducto().getIdProducto(), new Producto());
                p.setUnidadesProducto(p.getUnidadesProducto() - dv.getCantidad());
                em.merge(p);
                em.persist(dv);
            }

            em.merge(venta);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
