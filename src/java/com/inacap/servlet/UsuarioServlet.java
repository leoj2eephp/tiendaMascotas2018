package com.inacap.servlet;

import com.inacap.beans.ServicioBeanLocal;
import com.inacap.entities.Usuario;
import com.inacap.utils.Hash;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "UsuarioServlet", urlPatterns = {"/UsuarioServlet"})
public class UsuarioServlet extends HttpServlet {

    @EJB
    ServicioBeanLocal servicioBean;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "editarUsuario":
                editarUsuario(request, response);
                break;
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

    private void editarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rut = request.getParameter("rutUser");
        String nombre = request.getParameter("nombreUser");
        String apellido = request.getParameter("apellidoUser");
        String mail = request.getParameter("emailUser");
        String clave = request.getParameter("clave");
        String clave2 = request.getParameter("claveConfirm");
        String errors = "";
        
        Usuario editedUsuario = (Usuario) servicioBean.buscar(rut, new Usuario());
        editedUsuario.setRutUser(rut);
        editedUsuario.setNombreUser(nombre);
        editedUsuario.setApellidoUser(apellido);
        if (!mail.equals("")) {
            editedUsuario.setEmailUser(mail);
        }
        if (clave.equals(clave2)) {
            editedUsuario.setClave(Hash.md5(clave));
        } else {
            errors += "- Claves no coinciden..";
        }
        
        if (errors.isEmpty()) {
            servicioBean.sincronizar(editedUsuario);
            request.setAttribute("msg", "Datos guardados exitosamente = )!");
        } else {
            request.setAttribute("errors", errors);
        }
        request.getRequestDispatcher("jsp/persona/misDatos.jsp").forward(request, response);
    }

}
