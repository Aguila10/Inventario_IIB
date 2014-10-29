
package Modelo;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author daemoncc
 */
public class ConexionBD{

    String driver = "org.postgresql.Driver";
    String connectString = "jdbc:postgresql://localhost:5432/inventario";
    String user = "darktech";
    String password = "darktech";
   
 
   /**
     * Metodo que regresa el el login pedido
     *
     * @param login parametro a buscar en la tabla registro
     * @return el login si lo encontro
     */
    public String buscaLogin(String login , String pass) {
       String res ="error";
        Statement statement;
        ResultSet resultSet;
 
        try {
            Connection con = DriverManager.getConnection(connectString, user, password);
            statement = con.createStatement();
            resultSet = statement.executeQuery("SELECT * from  buscaUsuario('" + login + "', '"
                    + pass + "');");
 
            while (resultSet.next()) {
                res = resultSet.getString(1);
            }
 
             
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return res;
    } 
      
    public boolean insertaUsuario(String login, String pass, String nombre, String categoria) {
        boolean res =false ;
        Statement statement;
        ResultSet resultSet;
 
        try {
            Connection con = DriverManager.getConnection(connectString, user, password);
            statement = con.createStatement();
            resultSet = statement.executeQuery("SELECT * from  agregaUsuario('" + nombre + "', "
                    + "'" + login + "','" + pass + "'," + "'"+ categoria+ "'" + ");");
 
            while (resultSet.next()) {
                res = resultSet.getBoolean(1);
            }
 
             
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return res;
    }
 
    public boolean insertaEquipo(int numeroInveInterInfo, int numInvUnam, String descrip,
    int modelo, int marca,  String serie , int familia , int tipo , int prove , int clase ,  int uso ,
    int nivel , int edoFisico , int area , int institu, int persoAsig  ,String fecha, int responsable ) {
        boolean res =false ;
        Statement statement;
        ResultSet resultSet;
        
        try {
            Connection con = DriverManager.getConnection(connectString, user, password);
            statement = con.createStatement();
            resultSet = statement.executeQuery("SELECT * from agregaEquipo(" + numeroInveInterInfo +" , "
                 + numInvUnam + "," + "'"+ descrip+ "'," +  null+ "," +null+ "," + null
            + "," +null+ "," +null+ "," +null+ "," +null+ "," +null+ "," +null
                    + "," +null+ "," +null+ "," +null+ "," +null+ "," +null+ "," +null + ");");
 
            while (resultSet.next()) {
                res = resultSet.getBoolean(1);
            }
 
             
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return res;
    }
 
   public ArrayList regresaCatalogo(String catalogo) {
        String res = "";
        ArrayList lista = new ArrayList();
        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(connectString, user, password);
            PreparedStatement query = con.prepareStatement("select descripcion from " + catalogo);
            
            ResultSet rset = query.executeQuery();
            while (rset.next()) {
                res = (rset.getString(1));
                lista.add(res);
            }
        } catch (SQLException | java.lang.ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
 
        return lista;
    }
 
  
    public static void main(String[] args) {
       
       ConexionBD con = new ConexionBD();
       System.out.println(con.insertaEquipo(2, 2, "aaaasdsr", 0, 0, null,0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, null, 0));
       
        System.out.println( con.insertaUsuario("rene_sec", "holamundo", "rene", "Secretaria"));
        System.out.println( con.insertaUsuario("rene_aca", "holamundo", "rene", "Tecnico Academico"));
        System.out.println( con.insertaUsuario("rene_inv", "holamundo", "rene", "Jefe de inventario"));

        System.out.println(con.buscaLogin("rene_adm", "holamundo"));
        
        
        
               
        
        
        
        
   
    }
    
    
}