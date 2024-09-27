/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Logica.ControlLogica;
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
@WebServlet(name = "SvEliminarUsuario", urlPatterns = {"/SvEliminarUsuario"})
public class SvEliminarUsuario extends HttpServlet {

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
        if (nombre != null && !nombre.isEmpty()) {
            control.eliminarUsuario(nombre);
        }

        String res = request.getParameter("IdUsuario");
        if (res != null && !res.isEmpty()) {
            try {
                int id = Integer.parseInt(res);
                if (id >= 0) {
                    control.eliminarUsuario(id);
                }
            } catch (NumberFormatException e) {
                // Manejo del error de conversión
                System.out.println("Error: IdUsuario no es un número válido");
                // También puedes redirigir o enviar un mensaje de error en la respuesta
            }
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
