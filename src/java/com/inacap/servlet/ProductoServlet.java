package com.inacap.servlet;

import com.inacap.beans.ServicioBeanLocal;
import com.inacap.entities.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.commons.io.IOUtils;

@WebServlet(name = "ProductoServlet", urlPatterns = {"/ProductoServlet"})
@MultipartConfig(location = "/tmp",
        fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class ProductoServlet extends HttpServlet {

    @EJB
    ServicioBeanLocal servicioBean;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "goToNewProductos":
                    goToNewProductos(request, response);
                    break;
                case "productos":
                    goToProductos(request, response);
                    break;
                case "nuevoProducto":
                    nuevoProducto(request, response);
                    break;
                case "myCart":
                    myCart(request, response);
                    break;
                case "catalogo":
                    goToCatalogo(request, response);
                    break;
                case "addToCart":
                    addToCart(request, response);
                    break;
                case "buy":
                    buy(request, response);
                    break;
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void goToNewProductos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Categoria> listaCats = servicioBean.getCategorias();
        request.setAttribute("categorias", listaCats);
        request.getRequestDispatcher("jsp/admin/producto/formulario.jsp").forward(request, response);
    }

    private void nuevoProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("desc");
        String categoria = request.getParameter("cat");
        int precio = 0;
        int unidad = 0;
        String errores = "";
        // Capturar imagen desde formulario
        InputStream stream = null;
        Part foto = request.getPart("foto");
        if (foto != null) {
            stream = foto.getInputStream();
        }

        if (nombre.isEmpty()) {
            errores = errores.concat("- Falta nombre..<br>");
        }
        try {
            precio = Integer.valueOf(request.getParameter("precio"));
        } catch (Exception e) {
            errores = errores.concat("- Falta precio..<br>");
        }
        try {
            unidad = Integer.valueOf(request.getParameter("unidad"));
        } catch (Exception e) {
            errores = errores.concat("- Falta unidad..<br>");
        }
        if (descripcion.isEmpty()) {
            errores = errores.concat("- Falta descripción..<br>");
        }

        if (errores.isEmpty()) {
            int id = Integer.valueOf(request.getParameter("categoria"));
            Categoria cat = (Categoria) servicioBean.buscar(id, new Categoria());
            Producto p = new Producto();
            p.setNombreProducto(nombre);
            p.setPrecioProducto(precio);
            p.setCategoria(cat);
            p.setUnidadesProducto(unidad);
            p.setDescripcionProducto(descripcion);
            if (stream != null) {
                p.setFotoProducto(IOUtils.toByteArray(stream));
            }

            servicioBean.save(p);
            //HIardar el producto en la lista de la categoria
            cat.getProductoList().add(p);
            servicioBean.sincronizar(cat);
            goToProductos(request, response);
        }
    }

    private void goToProductos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Producto> listaProds = servicioBean.getProductos();
        request.setAttribute("productos", listaProds);
        request.getRequestDispatcher("jsp/admin/producto/admin.jsp").forward(request, response);
    }

    private void myCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("jsp/persona/carrito.jsp").forward(request, response);
    }

    private void addToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idRecibido = request.getParameter("idProd");
        if (!idRecibido.isEmpty()) {
            try {
                int id = Integer.valueOf(idRecibido);
                List<Producto> carrito;
                if (request.getSession().getAttribute("carrito") != null) {
                    carrito = (List<Producto>) request.getSession().getAttribute("carrito");
                } else {
                    carrito = new ArrayList<Producto>();
                }
                Producto p = (Producto) servicioBean.buscar(id, new Producto());
                carrito.add(p);
                request.getSession().setAttribute("carrito", carrito);
                request.getSession().setAttribute("cant", carrito.size());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        goToCatalogo(request, response);
    }

    private void goToCatalogo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Producto> catalogo = servicioBean.getProductos();
        request.setAttribute("catalogo", catalogo);
        request.getRequestDispatcher("jsp/persona/catalogo.jsp").forward(request, response);
    }

    private void buy(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] cantidades = request.getParameterValues("cantidad");
        List<Producto> carrito = (List<Producto>) request.getSession().getAttribute("carrito");
        List<Detalleventa> listaDetalles = new ArrayList<>();
        Venta venta = new Venta();
        venta.setUsuario((Usuario) request.getSession().getAttribute("persona"));
        venta.setFechaVenta(new Date());
        int totalVenta = 0;
        
        for (int i = 0; i < cantidades.length; i++) {
            Producto pro = carrito.get(i);
           // if (servicioBean.buscar(pro.getIdProducto(), pro) != null) {
                int cant = Integer.valueOf(cantidades[i]);
                if(cant < pro.getUnidadesProducto()) {
                    Detalleventa dv = new Detalleventa();
                    dv.setProducto(pro);
                    int precio = pro.getPrecioProducto() * cant;
                    dv.setPrecio(precio);
                    dv.setVenta(venta);
                    totalVenta += precio;
                    dv.setCantidad(cant);
                    listaDetalles.add(dv);
            //    }
            }
        }
        
        venta.setTotalVenta(totalVenta);
        venta.setDetalleventaList(listaDetalles);
        servicioBean.comprar(venta);
        servicioBean.sincronizar(venta);
    }

}
