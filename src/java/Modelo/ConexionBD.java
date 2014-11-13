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
     *Metodo que recibe 
     * @param login
     * @param pass
     * @return la categoria de la persoan en otro caso regresa error
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

    /**
     *Metodo que regresa un arreglo de string con todos los nombres y
     * login de todos los usuarios MENOS del nombre o login que recibe
     * @param login_nombre
     * @return en la posicion 0 esta el login
     *  en la posicion 1 esta el nombre
     */
    public ArrayList<String[]>  buscaNombreLogin(String login_nombre) {
        String res = "error";
        Statement statement;
        ResultSet resultSet;
        ArrayList<String[]> resultado = new ArrayList<String[]>();
        String [] nombre = new String[2];

        try {
            Connection con = DriverManager.getConnection(connectString, user, password);
            statement = con.createStatement();
            resultSet = statement.executeQuery("SELECT * from  buscaNombreLogin('" + login_nombre + "');");

            while (resultSet.next()) {
                nombre[0] = resultSet.getString(1);
                nombre[1]= resultSet.getString(2);
               String[] nuevo = new String[2];
               nuevo[0]= nombre[0];
               nuevo[1]= nombre[1];
                resultado.add(nuevo);
                
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return resultado;
    }
    
    /**
     *Metodo que inserta un usuario
     * @param login
     * @param pass
     * @param nombre
     * @param categoria
     * @return un boolean
     */
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

    /**
     *Metodo que inserta un equipo
     * @param numeroInveInterInfo
     * @param numInvUnam
     * @param descrip
     * @param modelo
     * @param marca
     * @param serie
     * @param familia
     * @param tipo
     * @param prove
     * @param clase
     * @param uso
     * @param nivel
     * @param edoFisico
     * @param area
     * @param institu
     * @param fecha
     * @param responsable
     * @return un boolean
     */
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

    public boolean actualizaEquipo(int id_equipo,int numeroInveInterInfo, int numInvUnam, String descrip,
            String modelo, String marca, String serie, String familia, String tipo, String prove, String clase, String uso,
            String nivel, String edoFisico, String area, String institu, String fecha, String responsable) {
        boolean res = false;
        Statement statement;
        ResultSet resultSet;

        try {
            Connection con = DriverManager.getConnection(connectString, user, password);
            statement = con.createStatement();
            resultSet = statement.executeQuery("SELECT * from actualizaEquipo("+id_equipo+","+ numeroInveInterInfo + " , "
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
    /**
     *Metodo que busca Todos los quipos que concida el numero inventario unam
     * o el tipo activo fijo
     * @param clave
     * @return lista de equipos con todos los resultados
     */
    public ArrayList<Equipo> buscaEquipo(int clave) {
        int id_equipo, clave_activo_fijo, num_inv_unam;
        String clave_descripcion, clave_modelo, clave_marcar, serie,
                clave_familia, clave_tipo, clave_proveedor, clase,
                uso, nivel_de_obsolescencia, estado_físico,
                clave_area, clave_institucion, 
                fecha_de_resguardo, responsable;
        boolean estado;
        ArrayList<Equipo> res = new ArrayList<>();
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
               fecha_de_resguardo = resultSet.getString(17);
                responsable = resultSet.getString(18);
                estado = resultSet.getBoolean(19);

                Equipo a = new Equipo(id_equipo, clave_activo_fijo, num_inv_unam,
                        clave_descripcion, clave_modelo, clave_marcar, serie, clave_familia, clave_tipo, clave_proveedor, clase,
                        uso, nivel_de_obsolescencia, estado_físico, clave_area, clave_institucion, 
                        fecha_de_resguardo, responsable, estado);
            
           res.add(a); 
            }
            
           

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return res;
    }

    /**
     *Metodo que solo nos regresa la descripcion de los catalogos
     * @param catalogo
     * @return lista con las descripciones
     */
    public ArrayList regresaCatalogo(String catalogo) {
        String res = "";
        ArrayList lista = new ArrayList();
        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(connectString, user, password);
            PreparedStatement query = con.prepareStatement("select descripcion from " + catalogo + " order by descripcion");

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
    
    
    
    
    
     public ArrayList actualizaCatalogo(String tabla , int id_catalogo , String   descrip) {
        String res = "";
        ArrayList lista = new ArrayList();
        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(connectString, user, password);
            PreparedStatement query = con.prepareStatement("update  " + tabla + " set "
                    + "descripcion = "+ descrip+" where id_catalogo = " + id_catalogo );

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
    

    /**
     *Metodo que solo nos regresa  los catalogos
     * @param catalogo
     * @return lista de arreglos donde en la primera posicion esta el id 
     * en la posicion 2 esta la descripcion
     */
    public ArrayList<String[]> regresaCatalogoConId(String catalogo) {
        String res = "";
        ArrayList<String[]> resultado = new ArrayList<>();
        String [] nombre = new String[2];
        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(connectString, user, password);
            PreparedStatement query = con.prepareStatement("select * from " + catalogo + " order by descripcion");

            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                nombre[0] = resultSet.getString(1);
                nombre[1]= resultSet.getString(2);
               String[] nuevo = new String[2];
               nuevo[0]= nombre[0];
               nuevo[1]= nombre[1];
                resultado.add(nuevo);
            }
        } catch (SQLException | java.lang.ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return resultado;
    }
  
     
    /**
     *
     * @param id_equipo
     * @return
     */
    public Equipo regresaEquipo(int id_equipo) {
        String res = "";
        int id_equipo1, clave_activo_fijo, num_inv_unam;
        String clave_descripcion, clave_modelo, clave_marcar, serie,
                clave_familia, clave_tipo, clave_proveedor, clase,
                uso, nivel_de_obsolescencia, estado_físico,
                clave_area, clave_institucion, id_usuario_asignado,
                fecha_de_resguardo, responsable;
        boolean estado;
        Equipo a = null;
        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(connectString, user, password);
            PreparedStatement query = con.prepareStatement(" select id_equipo serial, clave_activo_fijo , num_inv_unam , clave_descripcion , clave_modelo , catalogo_marca.descripcion, " +
"serie , catalogo_familia.descripcion , catalogo_tipo_equipo.descripcion , catalogo_proveedor.descripcion, " +
"catalogo_clase.descripcion , catalogo_uso.descripcion, catalogo_nivel.descripcion , catalogo_estado_fisico.descripcion, " +
"catalogo_area.descripcion,catalogo_institucion.descripcion , fecha_de_resguardo ,  " +
"catalogo_responsable.descripcion , equipo.estado from equipo join catalogo_marca on equipo.clave_marcar = catalogo_marca.clave_marcar " +
"join catalogo_familia on equipo.clave_familia = catalogo_familia.clave_familia " +
"join catalogo_tipo_equipo on equipo.clave_tipo = catalogo_tipo_equipo.clave_tipo " +
"join catalogo_proveedor on equipo.clave_proveedor = catalogo_proveedor.clave_proveedor " +
"join catalogo_clase on equipo.clase = catalogo_clase.clave_clase " +
"join catalogo_uso on equipo.uso = catalogo_uso.clave_uso " +
"join catalogo_nivel on equipo.nivel_de_obsolescencia = catalogo_nivel.clave_nivel\n" +
"join catalogo_estado_fisico on equipo.estado_físico = catalogo_estado_fisico.clave_estado_fisico " +
"join catalogo_area on equipo.clave_area = catalogo_area.clave_area " +
"join catalogo_institucion on equipo.clave_institucion = catalogo_institucion.clave_institucion " +
"join catalogo_responsable on equipo.responsable = catalogo_responsable.clave_responsable where  id_equipo = " + id_equipo);

            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                 id_equipo1 = resultSet.getInt(1);
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
                fecha_de_resguardo = resultSet.getString(17);
                responsable = resultSet.getString(18);
                estado = resultSet.getBoolean(19);

                 a = new Equipo(id_equipo1, clave_activo_fijo, num_inv_unam,
                        clave_descripcion, clave_modelo, clave_marcar, serie, clave_familia, clave_tipo, clave_proveedor, clase,
                        uso, nivel_de_obsolescencia, estado_físico, clave_area, clave_institucion, 
                        fecha_de_resguardo, responsable, estado);
            }
        } catch (SQLException | java.lang.ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return a;
    }
    
    /**
     *Metodo que elimina un usuario
     * @param login
     * @return un boolean
     */
    public boolean  eliminaUsuario(String login) {
        boolean res = true;
        Statement statement;
        ResultSet resultSet;
       
        try {
            Connection con = DriverManager.getConnection(connectString, user, password);
            statement = con.createStatement();
            resultSet = statement.executeQuery("SELECT * from  eliminaUsuario('" + login + "');");
 while (resultSet.next()) {
                 res = resultSet.getBoolean(1);
             }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return res;
    }
     
    
    
    
    public boolean  insertaMovimientos(int id_usuario , int id_equipo , String descripcion , String fecha ) {
        boolean res = true;
        Statement statement;
        ResultSet resultSet;
       
        try {
            Connection con = DriverManager.getConnection(connectString, user, password);
            statement = con.createStatement();
            resultSet = statement.executeQuery("SELECT * from  movimientoEquipo(" + id_usuario + " , " 
                    + id_equipo+ "," + "'"+descripcion+"'"+ "'"+fecha+"');");
            while (resultSet.next()) {
                 res = resultSet.getBoolean(1);
             }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return res;
    }
     
 
   
    
    
    
    
     public ArrayList<String[]> regresaMarcaSerieDeparta(int numero) {
        String res = "";
        ArrayList<String[]> resultado = new ArrayList<>();
        String [] nombre = new String[3];
        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(connectString, user, password);
            PreparedStatement query = con.prepareStatement("  select  catalogo_marca.descripcion,serie ,catalogo_institucion.descripcion "
                    + "from equipo join catalogo_marca on equipo.clave_marcar = catalogo_marca.clave_marcar " +
"join catalogo_familia on equipo.clave_familia = catalogo_familia.clave_familia " +
"join catalogo_tipo_equipo on equipo.clave_tipo = catalogo_tipo_equipo.clave_tipo " +
"join catalogo_proveedor on equipo.clave_proveedor = catalogo_proveedor.clave_proveedor " +
"join catalogo_clase on equipo.clase = catalogo_clase.clave_clase " +
"join catalogo_uso on equipo.uso = catalogo_uso.clave_uso " +
"join catalogo_nivel on equipo.nivel_de_obsolescencia = catalogo_nivel.clave_nivel " +
"join catalogo_estado_fisico on equipo.estado_físico = catalogo_estado_fisico.clave_estado_fisico " +
"join catalogo_area on equipo.clave_area = catalogo_area.clave_area " +
"join catalogo_institucion on equipo.clave_institucion = catalogo_institucion.clave_institucion " +
"join catalogo_responsable on equipo.responsable = catalogo_responsable.clave_responsable " +
"where num_inv_unam =  " + numero+ "or" + " clave_activo_fijo = "+ numero);

            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                nombre[0] = resultSet.getString(1);
                nombre[1]= resultSet.getString(2);
                nombre[2] = resultSet.getString(3);
               String[] nuevo = new String[3];
               nuevo[0]= nombre[0];
               nuevo[1]= nombre[1];
               nuevo[2] = nombre[2];
                resultado.add(nuevo);
            }
        } catch (SQLException | java.lang.ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return resultado;
    }
    
    
     
     
        
    
     public String regresaNombre(String login) {
        String res = "";
        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(connectString, user, password);
            PreparedStatement query = con.prepareStatement("  select usuarios.nombre from usuarios join"
                    + " registro on usuarios.id_usuario = registro.id_usuario where registro.login = '"+ login + "'");

            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
              res = resultSet.getString(1);
            }
        } catch (SQLException | java.lang.ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return res;
    }
    
     
    
    
    
    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        ConexionBD con = new ConexionBD();
//   
        
//        con.insertaUsuario("rene", "holamundo","rene","Administrador");
    
//        ArrayList<String[]> lista =  con.buscaNombreLogin("caen");
//        
//        for (int i = 0; i < lista.size(); i++) {
//         
//        System.out.println(lista.get(i)[0] + " , " + lista.get(i)[1] );
//           
//        }
        
       /// System.out.println(con.eliminaUsuario("rene"));
        
//      System.out.println(con.regresaEquipo(1).getClave_area());
        
        
//        System.out.println(con.insertaEquipo(1231 ,
//10 ,
//"Descripcion12",
//"12hffg",
//"ACTECK" , 
//"12345",
//"INTEL PENTIUM D|COREDUO|CORE2DU| AMD ATHLON| X2DUAL| CR2QUAD| CR2 Y EQUIVALENTE",
//"Computadora portátil",
//"FOTO DEL RECUERDO" ,
//"Equipo en prestamo de proveedor",
//"Bajo (Personal Académico y/o investigadores)",
//"Seminuevo (Windows Server | MAC y linux)" ,
//"Malo (equipo requiere incrementos | memoria |disco)",
//"CATALOGACIÓN FR-BNM" ,
//"CENTRO CERRADO DE TELEVICION Y MONITOREO" ,
//"12/12/12" ,
//"ALFREDO HIDALGO"));
//        
//        for (int i = 0; i < con.regresaMarcaSerieDeparta(123).size(); i++) {
//            
//        
//        
//        System.out.println(con.regresaMarcaSerieDeparta(123).get(1)[0]);
//    
//        }
        
        
//        System.out.println(con.regresaNombre("caen"));
        
        }

}
