<%-- 
    Document   : administrarUsuarios
    Created on : 13 sept 2024, 15:03:07
    Author     : Usuario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="adminUser.css">
    <title>Administrar Usuarios</title>
</head>
<body>
    <header>
        <h1>Administracion de usuarios</h1>
    </header>

    <main>
    <div class="crearUser-container">
        <form class="formulario" >
            <input type="button" value="Crear Usuario" class="boton" onclick="window.location.href='RegistroAdmin.jsp'"> 
            
        </form>
    </div>

    <div class="modificarUser-container">
        <form class="formulario_modificacion" action="SvEditarUsuario">
            <input type="button" value="Modificar Usuario" class="boton" onclick="window.location.href='ModificarUsuario.jsp'"> 
        </form>
    </div>

    <div class="eliminarUser-container">
        <form class="formulario_eliminar" action="SvEliminarUsuario">
            <input type="button" value="Eliminar Usuario" class="boton" onclick="window.location.href='EliminarUsuario.jsp'">
        </form>
    </div>
        
    <div class="Principal-container">
        <form class="formulario" action="">
            <button class="button">Volver a pagina principal</button>
        </form>
    </div>    
    
</main>
</body>
</html>