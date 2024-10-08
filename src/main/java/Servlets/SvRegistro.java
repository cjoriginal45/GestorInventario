package Servlets;

import Logica.ControlLogica;
import Logica.Rol;
import static Logica.Rol.EMPLEADO;
import Logica.Usuario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvRegistro", urlPatterns = {"/SvRegistro"})
public class SvRegistro extends HttpServlet {

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

        if (nombre == null || nombre.isEmpty() || contrasenia == null || contrasenia.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Nombre de usuario o contraseña no válidos");
            return;
        }

        try {

            Usuario user = new Usuario(nombre, contrasenia, EMPLEADO);

            control.crearUsuario(user);

            response.sendRedirect("index.jsp");

        } catch (Exception e) {
            // Manejar otros errores generales (por ejemplo, problemas con la base de datos)
            System.out.println("Error al crear usuario: " + e.getMessage());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error interno al crear el usuario");
        }

    }

}
