<%-- 
    Document   : Registro
    Created on : 5 sept 2024, 14:48:50
    Author     : Usuario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="registro.css">
    </head>
    <body>
        <div class="title-container">
            <h1>Registro de usuario</h1>
        </div>  

        <div class="login_container">
            <h2>Complete los datos</h2>
            
            <form class="formulario" action="SvRegistro" method="POST">
                <input type="text" name="nombreUsuario" placeholder="Ingresar nombre de usuario" required>
                <input type="password" name="contrasenia" placeholder="Ingresar contraseña" required>
                <label for="enumSelect">Tipo de usuario:</label>
                <select class="selector" id="enumSelect" name="enumOptions">
                    <option value="EMPLEADO">EMPLEADO</option>
                    <option value="ADMINISTRADOR">ADMINISTRADOR</option>
                </select>
                <input type="submit" value="Registrarse" class="boton">
                <input type="button" value="volver a inicio sesion" class="boton" onclick="window.location.href='index.jsp'"> 
            </form>

        </div>

    </body>
</html>
