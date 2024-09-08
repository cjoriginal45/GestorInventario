<%-- 
    Document   : index.jsp
    Created on : 3 sept 2024, 13:23:47
    Author     : Usuario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="styles.css">
        <title>Iniciar Sesion</title>
    </head>
    <body>
        <div class="title-container">
            <h1>Gestion de Inventario</h1>
        </div>  

        <div class="login_container">
            <h2>Inicio de Sesion</h2>
            <form class="formulario" action="SvIndex" method="POST">
                <input type="text" placeholder="Ingrese nombre de usuario" name="nombreUsuario" required>
                <br>
                <input type="password" placeholder="Ingrese contraseña" name="contrasenia" required>
                <input type="submit" value="Iniciar Sesion" class="boton">
                <input type="button" value="Registrarse" class="boton" onclick="window.location.href = 'Registro.jsp'">       
            </form>

        </div>


    </body>
</html>