
package delivery;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class DB {
    public double sumarPedidos(String fecha, String estado){
    double total =0;
        try{
         Connection c = DriverManager.getConnection("jdbc:mysql://localhost/delivery", "root", "Root");
         PreparedStatement s = c.prepareStatement("select * from pedidos where fecha=? and estado=?");
       
         s.setString(1,fecha);
         s.setString(2, estado);
    //recorremos los resultados
        ResultSet res = s.executeQuery();
            while (res.next()) {

                total = total +res.getDouble("importe");

            }
          
        }catch(Exception e){

                System.out.println(e.getMessage());

        }
    
    return total;
    }
    
    public DefaultTableModel getPedidos(String fecha, String estado){
        
        DefaultTableModel datos = new DefaultTableModel();
      
        datos.addColumn("id");
        datos.addColumn("datosCliente");
        datos.addColumn("detallePedido");
        datos.addColumn("importe");
        
        try{
         Connection c = DriverManager.getConnection("jdbc:mysql://localhost/delivery", "root", "Root");
         PreparedStatement s = c.prepareStatement("select * from pedidos where fecha=? and estado=?");
       
         s.setString(1,fecha);
         s.setString(2, estado);
    //recorremos los resultados
        ResultSet res = s.executeQuery();
            while (res.next()) {

                Object[] fila = new Object[4];

                fila[0] = res.getString("id");
                fila[1] = res.getString("datosCliente");
                fila[2] = res.getString("detallePedido");
                fila[3] = res.getString("importe");

                datos.addRow(fila);

            }
          
        }catch(Exception e){

                System.out.println(e.getMessage());

        }
    return datos;
    }

}
