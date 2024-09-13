<%-- 
    Document   : PrincipalAdmin
    Created on : 13 sept 2024, 14:24:27
    Author     : Usuario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pagina Principal Administrador</title>
        <link rel="stylesheet" href="principalAdmin.css">
    </head>
    <body>
        <header>
            <h1>Bienvenido a la pagina Principal de Administradores</h1>
        </header>
        <main>

            <div class="productos-container">
                <button class="button" class="boton" onclick="window.location.href = 'gestorProductos.jsp'">Gestion de Productos</button>
            </div>

            <div class="stock-container">
                <button class="button" class="boton" onclick="window.location.href = 'controlStock.jsp'">Control de stock</button>
            </div>

            <div class="registros-container">
                <button class="button" class="boton" onclick="window.location.href = 'registrosES.jsp'">Registros de E/S</button>
            </div>

            <div class="reportes-container">
                <button class="button reporte--boton" class="boton" onclick="window.location.href = 'reportes.jsp'">Generación de reportes</button>
            </div>
            
            <div class="administrar-usuarios-container">
                <button class="button reporte--boton" class="boton" onclick="window.location.href = 'administrarUsuarios.jsp'">Administracion de usuarios</button>
            </div>

        </main>
    </body>
</html>
