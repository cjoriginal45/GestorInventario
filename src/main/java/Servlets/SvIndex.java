
package Servlets;

import Logica.ControlLogica;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Usuario
 */
@WebServlet(name = "SvIndex", urlPatterns = {"/SvIndex"})
public class SvIndex extends HttpServlet {

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
        // Obtener parámetros del formulario
        String username = request.getParameter("nombreUsuario");
        String password = request.getParameter("contrasenia");

        // Lógica de autenticación
        boolean isAuthenticated = control.authenticate(username, password);

        // Redirigir según el resultado de la autenticación
            if (isAuthenticated) {
            // Redirigir a una página de éxito
            response.sendRedirect("PaginaPrincipal.jsp");
        } else {
            // Redirigir de vuelta al login con un mensaje de error
            response.sendRedirect("index.jsp?Usuario o contraseña incorrectos");
        }
    }

}
