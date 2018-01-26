package com.inacap.beans;

import com.inacap.entities.*;
import java.util.*;
import javax.ejb.Local;

@Local
public interface ServicioBeanLocal {

    Usuario loggear(String rut, String clave);
    void save(Object o);
    void sincronizar(Object o);
    Object buscar(Object id, Object o);
    void comprar(Venta venta);
    
    List<Categoria> getCategorias();
    List<Producto> getProductos();
    
}