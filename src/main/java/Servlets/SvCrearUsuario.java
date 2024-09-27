/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Logica.ControlLogica;
import Logica.Rol;
import Logica.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Usuario
 */
@WebServlet(name = "SvCrearUsuario", urlPatterns = {"/SvCrearUsuario"})
public class SvCrearUsuario extends HttpServlet {

    ControlLogica control = new ControlLogica();
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         String nombre = request.getParameter("nombreUsuario");
        String contrasenia = request.getParameter("contrasenia");
        String str = request.getParameter("rolUsuario");
        Rol rol = null;
                
        rol.parse(str);
        
        

        if (nombre == null || nombre.isEmpty() || contrasenia == null || contrasenia.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Nombre de usuario o contraseña no válidos");
            return;
        }

        try {

            Usuario user = new Usuario(nombre, contrasenia, rol);

            control.crearUsuario(user);

            response.sendRedirect("administrarUsuarios.jsp");

        } catch (Exception e) {
            // Manejar otros errores generales (por ejemplo, problemas con la base de datos)
            System.out.println("Error al crear usuario: " + e.getMessage());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error interno al crear el usuario");
        }
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
