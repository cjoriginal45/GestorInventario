<%-- 
    Document   : EliminarUsuario
    Created on : 27 sept 2024, 15:13:22
    Author     : Usuario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="">
    <title>Document</title>
</head>
<body>
    <header>
        <h1>Administracion de usuarios</h1>
    </header>


    <div class="eliminacion_nombre">
        <h2>Eliminacion de cuenta por nombre</h2>
        <form class="formulario" action="SvEliminarUsuario" method="POST">
          <input type="text" name="nombreUsuario" placeholder="Ingresar nombre de usuario">
          <input type="submit" value="Eliminar" class="boton">
          <input type="button" value="volver a administrar usuarios" class="boton" onclick="window.location.href='administrarUsuarios.jsp'"> 
        </form>
      </div>

      <div class="eliminacion_id">
        <h2>Eliminacion de cuenta por id</h2>
        <form class="formulario" action="SvEliminarUsuario" method="POST">
          <input type="text" name="IdUsuario" placeholder="Ingresar ID del usuario">
          <input type="submit" value="Eliminar" class="boton">
          <input type="button" value="volver a administrar usuarios" class="boton" onclick="window.location.href='administrarUsuarios.jsp'"> 
        </form>
      </div>
    

</main>
</body>
</html>
