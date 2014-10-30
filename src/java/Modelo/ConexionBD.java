package Modelo;

import Controlador.Equipo;
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
public class ConexionBD {

    String driver = "org.postgresql.Driver";
    String connectString = "jdbc:postgresql://localhost:5432/Inventario";
    String user = "darktech";
    String password = "darktech";

    /**
     * Metodo que regresa el el login pedido
     *
     * @param login parametro a buscar en la tabla registro
     * @return el login si lo encontro
     */
    public String buscaLogin(String login, String pass) {
        String res = "error";
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

    
    
     public ArrayList<String> buscaNombreLogin(String login_nombre) {
        String res = "error";
        Statement statement;
        ResultSet resultSet;
        ArrayList<String> resultado = new ArrayList<String>();

        try {
            Connection con = DriverManager.getConnection(connectString, user, password);
            statement = con.createStatement();
            resultSet = statement.executeQuery("SELECT * from  buscaNombreLogin('" + login_nombre + "');");

            while (resultSet.next()) {
                res = resultSet.getString(1) +"     " +resultSet.getString(2);
             resultado.add(res);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return resultado;
    }
    
    
    public boolean insertaUsuario(String login, String pass, String nombre, String categoria) {
        boolean res = false;
        Statement statement;
        ResultSet resultSet;

        try {
            Connection con = DriverManager.getConnection(connectString, user, password);
            statement = con.createStatement();
            resultSet = statement.executeQuery("SELECT * from  agregaUsuario('" + nombre + "', "
                    + "'" + login + "','" + pass + "'," + "'" + categoria + "'" + ");");

            while (resultSet.next()) {
                res = resultSet.getBoolean(1);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return res;
    }

    public boolean insertaEquipo(int numeroInveInterInfo, int numInvUnam, String descrip,
            String modelo, String marca, String serie, String familia, String tipo, String prove, String clase, String uso,
            String nivel, String edoFisico, String area, String institu, String fecha, String responsable) {
        boolean res = false;
        Statement statement;
        ResultSet resultSet;

        try {
            Connection con = DriverManager.getConnection(connectString, user, password);
            statement = con.createStatement();
            resultSet = statement.executeQuery("SELECT * from agregaEquipo(" + numeroInveInterInfo + " , "
                    + numInvUnam + "," + "'" + descrip + "','" + modelo + "','" + marca + "','" + serie
                    + "','" + familia + "','" + tipo + "','" + prove + "','" + clase + "','" + uso + "','" + nivel
                    + "','" + edoFisico + "','" + area + "','" + institu + "','" + fecha + "','" + responsable + "');");

            while (resultSet.next()) {
                res = resultSet.getBoolean(1);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return res;
    }

    public ArrayList<Equipo> buscaEquipo(int clave) {
        int id_equipo, clave_activo_fijo, num_inv_unam;
        String clave_descripcion, clave_modelo, clave_marcar, serie,
                clave_familia, clave_tipo, clave_proveedor, clase,
                uso, nivel_de_obsolescencia, estado_físico,
                clave_area, clave_institucion, id_usuario_asignado,
                fecha_de_resguardo, responsable;
        boolean estado;
        ArrayList<Equipo> res = new ArrayList<Equipo>();
        Statement statement;
        ResultSet resultSet;

        try {
            Connection con = DriverManager.getConnection(connectString, user, password);
            statement = con.createStatement();
            resultSet = statement.executeQuery("SELECT * from buscaEquipo(" + clave + ");");

            while (resultSet.next()) {
                id_equipo = resultSet.getInt(1);
                clave_activo_fijo = resultSet.getInt(2);
                num_inv_unam = resultSet.getInt(3);
                clave_descripcion = resultSet.getString(4);
                clave_modelo = resultSet.getString(5);
                clave_marcar = resultSet.getString(6);
                serie = resultSet.getString(7);
                clave_familia = resultSet.getString(8);
                clave_tipo = resultSet.getString(9);
                clave_proveedor = resultSet.getString(10);
                clase = resultSet.getString(11);
                uso = resultSet.getString(12);
                nivel_de_obsolescencia = resultSet.getString(13);
                estado_físico = resultSet.getString(14);
                clave_area = resultSet.getString(15);
                clave_institucion = resultSet.getString(16);
                id_usuario_asignado = resultSet.getString(17);
                fecha_de_resguardo = resultSet.getString(18);
                responsable = resultSet.getString(19);
                estado = resultSet.getBoolean(20);

                Equipo a = new Equipo(id_equipo, clave_activo_fijo, num_inv_unam,
                        clave_descripcion, clave_modelo, clave_marcar, serie, clave_familia, clave_tipo, clave_proveedor, clase,
                        uso, nivel_de_obsolescencia, estado_físico, clave_area, clave_institucion, id_usuario_asignado,
                        fecha_de_resguardo, responsable, estado);
            
           res.add(a); 
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
//       
//        System.out.println( con.insertaUsuario("rene_sec", "holamundo", "rene", "Secretaria"));
//        System.out.println( con.insertaUsuario("rene_aca", "holamundo", "rene", "Tecnico Academico"));
//        System.out.println( con.insertaUsuario("rene_inv", "holamundo", "rene", "Jefe de inventario"));
//
//        System.out.println(con.buscaLogin("rene_adm", "holamundo"));

//        System.out.println(con.insertaEquipo(
//                111,
//                111,
//                "Descripcion12",
//                "12hffg",
//                "ACTECK",
//                "12345",
//                "INTEL PENTIUM D|COREDUO|CORE2DU| AMD ATHLON| X2DUAL| CR2QUAD| CR2 Y EQUIVALENTE",
//                "Computadora portátil",
//                "FOTO DEL RECUERDO",
//                "Equipo en prestamo de proveedor",
//                "Bajo (Personal Académico y/o investigadores)",
//                "Seminuevo (Windows Server | MAC y linux)",
//                "Malo (equipo requiere incrementos | memoria |disco)",
//                "CATALOGACIÓN FR-BNM",
//                "CENTRO CERRADO DE TELEVICION Y MONITOREO",
//                "12/12/12",
//                "ALFREDO HIDALGO"));
        
//        ArrayList<Equipo> a = con.buscaEquipo(111);
//        
//        for (int i = 0; i < a.size(); i++) {
//            System.out.println( a.get(i).getClave_activo_fijo());
       //}
    
        System.out.println(con.buscaNombreLogin("caen"));
    

    }

}
