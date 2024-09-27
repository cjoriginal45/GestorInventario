<%-- 
    Document   : RegistroAdmin
    Created on : 27 sept 2024, 13:55:24
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
            
            <form class="formulario" action="SvCrearUsuario" method="POST">
                <input type="text" name="nombreUsuario" placeholder="Ingresar nombre de usuario" required>
                <input type="password" name="contrasenia" placeholder="Ingresar contraseña" required>
                <select name="rolUsuario">
                    <option>ADMINISTRADOR</option>
                    <option>EMPLEADO</option>
                </select>
                <input type="submit" value="Registrarse" class="boton">
                <input type="button" value="volver a inicio sesion" class="boton" onclick="window.location.href='index.jsp'"> 
                
            </form>

        </div>

    </body>
</html>

