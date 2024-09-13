package Servlets;

import Logica.ControlLogica;
import Logica.Rol;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("nombreUsuario");
        String password = request.getParameter("contrasenia");

        // Lógica de autenticación: devolver el rol del usuario o null si no está autenticado
        Rol userRole = control.authenticateAndGetRole(username, password);

        if (userRole!= null) {
            // Usuario autenticado correctamente, ahora redirigimos según su rol
            HttpSession session = request.getSession();
            session.setAttribute("nombreUsuario", username);
            session.setAttribute("rol", userRole);

            // Redirigir según el rol usando el enum
        switch (userRole) {
            case ADMINISTRADOR:
                response.sendRedirect("PrincipalAdmin.jsp");
                break;
            case EMPLEADO:
                response.sendRedirect("PaginaPrincipal.jsp");
                break;
            default:
                // En caso de que el rol no sea reconocido
                response.sendRedirect("index.jsp?error=Rol no reconocido");
                break;
        }
    } else {
        // Usuario o contraseña incorrectos, redirigir al login con un mensaje de error
        response.sendRedirect("index.jsp?error=Usuario o contraseña incorrectos");
    }

    }
}
