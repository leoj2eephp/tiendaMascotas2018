package com.inacap.servlet;

import com.inacap.beans.ServicioBean;
import com.inacap.beans.ServicioBeanLocal;
import com.inacap.entities.*;
import com.inacap.utils.Hash;
import java.io.IOException;
import java.util.*;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "InicioServlet", urlPatterns = {"/InicioServlet"})
public class InicioServlet extends HttpServlet {

    @EJB
    ServicioBeanLocal servicioBean;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "login":
                    login(request, response);
                    break;
                case "nuevaCategoria":
                    nuevaCategoria(request, response);
                    break;
                case "categorias":
                    goToCategorias(request, response);
                    break;
                case "misDatos":
                    goToMisDatos(request, response);
                    break;
            }
        } else {
            response.sendRedirect("index.jsp");
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

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombreUser = request.getParameter("rut");
        String clave = request.getParameter("clave");
        String pass = Hash.md5(clave);
        Usuario u = servicioBean.loggear(nombreUser, pass);
        if (u != null) {
            if (u.getPerfil().getNombrePerfil().equals("administrador")) {
                request.getSession().setAttribute("admin", u); // crear session con datos de usuario
                request.getRequestDispatcher("jsp/admin/inicio.jsp").forward(request, response);
            } else {
                request.getSession().setAttribute("persona", u);
                request.getRequestDispatcher("jsp/persona/inicio.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("msg", "Credenciales incorrectas!!");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    private void goToCategorias(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Categoria> listaCats = servicioBean.getCategorias();
        request.setAttribute("categorias", listaCats);
        request.getRequestDispatcher("jsp/admin/categoria/formulario.jsp").forward(request, response);
    }

    private void nuevaCategoria(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombreCategoria = request.getParameter("nombre");
        if (nombreCategoria != null && !nombreCategoria.equals("")) {
            Categoria categoria = new Categoria();
            categoria.setNombreCategoria(nombreCategoria);
            servicioBean.save(categoria);
            goToCategorias(request, response);
        } else {
            request.setAttribute("msg", "Ingrese nombre..");
            goToCategorias(request, response);
        }
    }

    private void goToMisDatos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("jsp/persona/misDatos.jsp").forward(request, response);
    }

}
