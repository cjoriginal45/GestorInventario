
package Logica;


public enum Rol {
    ADMINISTRADOR,EMPLEADO;
    
    public static Rol parse(String text){
        if(ADMINISTRADOR.name().equalsIgnoreCase(text)){
            return ADMINISTRADOR;
        } else {
            return EMPLEADO;
        }
    }
}
