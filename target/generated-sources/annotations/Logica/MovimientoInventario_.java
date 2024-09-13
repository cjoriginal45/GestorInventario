package Logica;

import Logica.Producto;
import Logica.TipoMovimiento;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-09-13T14:56:50")
@StaticMetamodel(MovimientoInventario.class)
public class MovimientoInventario_ { 

    public static volatile SingularAttribute<MovimientoInventario, String> descripcion;
    public static volatile SingularAttribute<MovimientoInventario, Date> fecha;
    public static volatile SingularAttribute<MovimientoInventario, TipoMovimiento> movimiento;
    public static volatile SingularAttribute<MovimientoInventario, Integer> id;
    public static volatile SingularAttribute<MovimientoInventario, Producto> producto;
    public static volatile SingularAttribute<MovimientoInventario, Integer> cantidad;

}