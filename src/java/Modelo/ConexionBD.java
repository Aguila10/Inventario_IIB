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
     * Metodo que recibe
     *
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
     * Metodo que regresa un arreglo de string con todos los nombres y login de
     * todos los usuarios MENOS del nombre o login que recibe
     *
     * @param login_nombre
     * @return en la posicion 0 esta el login en la posicion 1 esta el nombre
     */
    public ArrayList<String[]> buscaNombreLogin(String login_nombre) {
        String res = "error";
        Statement statement;
        ResultSet resultSet;
        ArrayList<String[]> resultado = new ArrayList<>();
        String[] nombre = new String[2];

        try {
            Connection con = DriverManager.getConnection(connectString, user, password);
            statement = con.createStatement();
            resultSet = statement.executeQuery("SELECT * from  buscaNombreLogin('" + login_nombre + "');");

            while (resultSet.next()) {
                nombre[0] = resultSet.getString(1);
                nombre[1] = resultSet.getString(2);
                String[] nuevo = new String[2];
                nuevo[0] = nombre[0];
                nuevo[1] = nombre[1];
                resultado.add(nuevo);

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return resultado;
    }

    /**
     * Metodo que inserta un usuario
     *
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
     * Metodo que inserta un equipo
     *
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

    /**
     *
     * @param id_equipo
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
     * @return
     */
    public boolean actualizaEquipo(int id_equipo, int numeroInveInterInfo, int numInvUnam, String descrip,
            String modelo, String marca, String serie, String familia, String tipo, String prove, String clase, String uso,
            String nivel, String edoFisico, String area, String institu, String fecha, String responsable) {
        boolean res = false;
        Statement statement;
        ResultSet resultSet;

        try {
            Connection con = DriverManager.getConnection(connectString, user, password);
            statement = con.createStatement();
            resultSet = statement.executeQuery("SELECT * from actualizaEquipo(" + id_equipo + "," + numeroInveInterInfo + " , "
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
     * Metodo que busca Todos los quipos que concida el numero inventario unam o
     * el tipo activo fijo
     *
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
     * Metodo que solo nos regresa la descripcion de los catalogos
     *
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

    /**
     * QUE ONDA CON EL CATALOG RELACIONADO
     *
     * @param tabla
     * @param id_catalogo
     * @param descrip
     * @return
     */
    public boolean actualizaCatalogo(String tabla, int id_catalogo, String descrip) {
        boolean res = true;

        String id_catalogo_tabla = "";

        if ("catalogo_marca".equals(tabla)) {
            id_catalogo_tabla = "clave_marcar";
        }

        if ("catalogo_area".equals(tabla)) {
            id_catalogo_tabla = "clave_area";
        }

        if ("catalogo_institucion".equals(tabla)) {
            id_catalogo_tabla = "clave_institucion";
        }

        if ("catalogo_tipo_equipo".equals(tabla)) {
            id_catalogo_tabla = "clave_tipo";
        }

        if ("catalogo_familia".equals(tabla)) {
            id_catalogo_tabla = "clave_familia";
        }

        if ("catalogo_proveedor".equals(tabla)) {
            id_catalogo_tabla = "clave_proveedor";
        }

        if ("catalogo_clase".equals(tabla)) {
            id_catalogo_tabla = "clave_clase";
        }

        if ("catalogo_uso".equals(tabla)) {
            id_catalogo_tabla = "clave_uso";
        }

        if ("catalogo_nivel".equals(tabla)) {
            id_catalogo_tabla = "clave_nivel";
        }

        if ("catalogo_estado_fisico".equals(tabla)) {
            id_catalogo_tabla = "clave_estado_fisico";
        }

        if ("catalogo_responsable".equals(tabla)) {
            id_catalogo_tabla = "clave_responsable";
        }

        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(connectString, user, password);
            PreparedStatement query = con.prepareStatement("update  " + tabla + " set "
                    + "descripcion = '" + descrip + "' where " + id_catalogo_tabla + " = " + id_catalogo
                    + " RETURNING " + tabla);

            ResultSet rset = query.executeQuery();

        } catch (SQLException | java.lang.ClassNotFoundException e) {
            res = false;
            System.out.println(e.getMessage());
            return res;

        }

        return res;
    }

    /**
     * REALIZAR PROCEDIMIENTO MAS FACIL !!!! QUE ONDA CON EL CATALOG RELACIONADO
     *
     * @param tabla
     * @param descrip
     * @return
     */
    public boolean insertaCatalogo(String tabla, String descrip) {
        boolean res = true;

        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(connectString, user, password);
            PreparedStatement query = con.prepareStatement("insert into  " + tabla + " (descripcion) values ( "
                    + "'" + descrip + "'");

            ResultSet rset = query.executeQuery();

        } catch (SQLException | java.lang.ClassNotFoundException e) {
            res = false;
            System.out.println(e.getMessage());
            return res;

        }

        return res;
    }

    /**
     * Metodo que solo nos regresa los catalogos
     *
     * @param catalogo
     * @return lista de arreglos donde en la primera posicion esta el id en la
     * posicion 2 esta la descripcion
     */
    public ArrayList<String[]> regresaCatalogoConId(String catalogo) {
        String res = "";
        ArrayList<String[]> resultado = new ArrayList<>();
        String[] nombre = new String[2];
        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(connectString, user, password);
            PreparedStatement query = con.prepareStatement("select * from " + catalogo + " order by descripcion");

            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                nombre[0] = resultSet.getString(1);
                nombre[1] = resultSet.getString(2);
                String[] nuevo = new String[2];
                nuevo[0] = nombre[0];
                nuevo[1] = nombre[1];
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
            PreparedStatement query = con.prepareStatement(" select id_equipo serial, clave_activo_fijo , num_inv_unam , clave_descripcion , clave_modelo , catalogo_marca.descripcion, "
                    + "serie , catalogo_familia.descripcion , catalogo_tipo_equipo.descripcion , catalogo_proveedor.descripcion, "
                    + "catalogo_clase.descripcion , catalogo_uso.descripcion, catalogo_nivel.descripcion , catalogo_estado_fisico.descripcion, "
                    + "catalogo_area.descripcion,catalogo_institucion.descripcion , fecha_de_resguardo ,  "
                    + "catalogo_responsable.descripcion , equipo.estado from equipo join catalogo_marca on equipo.clave_marcar = catalogo_marca.clave_marcar "
                    + "join catalogo_familia on equipo.clave_familia = catalogo_familia.clave_familia "
                    + "join catalogo_tipo_equipo on equipo.clave_tipo = catalogo_tipo_equipo.clave_tipo "
                    + "join catalogo_proveedor on equipo.clave_proveedor = catalogo_proveedor.clave_proveedor "
                    + "join catalogo_clase on equipo.clase = catalogo_clase.clave_clase "
                    + "join catalogo_uso on equipo.uso = catalogo_uso.clave_uso "
                    + "join catalogo_nivel on equipo.nivel_de_obsolescencia = catalogo_nivel.clave_nivel\n"
                    + "join catalogo_estado_fisico on equipo.estado_físico = catalogo_estado_fisico.clave_estado_fisico "
                    + "join catalogo_area on equipo.clave_area = catalogo_area.clave_area "
                    + "join catalogo_institucion on equipo.clave_institucion = catalogo_institucion.clave_institucion "
                    + "join catalogo_responsable on equipo.responsable = catalogo_responsable.clave_responsable where  id_equipo = " + id_equipo);

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
     * Metodo que elimina un usuario
     *
     * @param login
     * @return un boolean
     */
    public boolean eliminaUsuario(String login) {
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

    /**
     *
     * @param id_usuario
     * @param id_equipo
     * @param descripcion
     * @param fecha
     * @return
     */
    public boolean insertaMovimientos(int id_usuario, int id_equipo, String descripcion, String fecha) {
        boolean res = true;
        Statement statement;
        ResultSet resultSet;

        try {
            Connection con = DriverManager.getConnection(connectString, user, password);
            statement = con.createStatement();
            resultSet = statement.executeQuery("SELECT * from  movimientoEquipo(" + id_usuario + " , "
                    + id_equipo + ", '" + descripcion + "' , '" + fecha + "');");
            while (resultSet.next()) {
                res = resultSet.getBoolean(1);
            }

        } catch (SQLException ex) {
            res = false;
            System.err.println(ex.getMessage());
        }
        return res;
    }

    /**
     *
     * @param numero
     * @return
     */
    public ArrayList<String[]> regresaMarcaSerieDeparta(int numero) {
        String res = "";
        ArrayList<String[]> resultado = new ArrayList<>();
        String[] nombre = new String[4];
        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(connectString, user, password);
            PreparedStatement query = con.prepareStatement("  select  catalogo_marca.descripcion,serie ,catalogo_institucion.descripcion , equipo.id_equipo "
                    + "from equipo join catalogo_marca on equipo.clave_marcar = catalogo_marca.clave_marcar "
                    + "join catalogo_familia on equipo.clave_familia = catalogo_familia.clave_familia "
                    + "join catalogo_tipo_equipo on equipo.clave_tipo = catalogo_tipo_equipo.clave_tipo "
                    + "join catalogo_proveedor on equipo.clave_proveedor = catalogo_proveedor.clave_proveedor "
                    + "join catalogo_clase on equipo.clase = catalogo_clase.clave_clase "
                    + "join catalogo_uso on equipo.uso = catalogo_uso.clave_uso "
                    + "join catalogo_nivel on equipo.nivel_de_obsolescencia = catalogo_nivel.clave_nivel "
                    + "join catalogo_estado_fisico on equipo.estado_físico = catalogo_estado_fisico.clave_estado_fisico "
                    + "join catalogo_area on equipo.clave_area = catalogo_area.clave_area "
                    + "join catalogo_institucion on equipo.clave_institucion = catalogo_institucion.clave_institucion "
                    + "join catalogo_responsable on equipo.responsable = catalogo_responsable.clave_responsable "
                    + "where num_inv_unam =  " + numero + "or" + " clave_activo_fijo = " + numero);

            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                nombre[0] = resultSet.getString(1);
                nombre[1] = resultSet.getString(2);
                nombre[2] = resultSet.getString(3);
                nombre[3] = resultSet.getString(4);

                String[] nuevo = new String[4];
                nuevo[0] = nombre[0];
                nuevo[1] = nombre[1];
                nuevo[2] = nombre[2];
                nuevo[3] = nombre[3];
                resultado.add(nuevo);
            }
        } catch (SQLException | java.lang.ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return resultado;
    }

    /**
     *
     * @param login
     * @return
     */
    public String regresaNombre(String login) {
        String res = "";
        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(connectString, user, password);
            PreparedStatement query = con.prepareStatement("  select usuarios.nombre from usuarios join"
                    + " registro on usuarios.id_usuario = registro.id_usuario where registro.login = '" + login + "'");

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
     * @param login
     * @return
     */
    public String regresaIDNombre(String login) {
        String res = "";
        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(connectString, user, password);
            PreparedStatement query = con.prepareStatement("  select usuarios.id_usuario from usuarios join"
                    + " registro on usuarios.id_usuario = registro.id_usuario where registro.login = '" + login + "'");

            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                res = resultSet.getString(1);
            }
        } catch (SQLException | java.lang.ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return res;
    }

    public ArrayList<Equipo> reportes(String marca,  String serie, String familia, String tipo_equipo,
            String fechaInicio, String fechaFin, String institucion, String area, String responsable,
            String estado) {
        
        ArrayList<Equipo>  resultado = new ArrayList<Equipo>();
        marca= marca.equals("") ? "%": marca;
        serie= serie.equals("") ? "%": serie;
        familia= familia.equals("") ? "%": familia;
        tipo_equipo= tipo_equipo.equals("") ? "%": tipo_equipo;
        fechaInicio= fechaInicio.equals("") ? "12/12/86": fechaInicio;
        fechaFin= fechaFin.equals("") ? "12/12/30": fechaFin;
        institucion= institucion.equals("") ? "%": institucion;
        area= area.equals("") ? "%": area;
        responsable= responsable.equals("") ? "%": responsable;
        estado= estado.equals("") ? "%": estado;
        
      
        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(connectString, user, password);
            PreparedStatement query = con.prepareStatement("select distinct  clave_activo_fijo , num_inv_unam ,  catalogo_marca.descripcion, clave_modelo,"
                    + "serie , catalogo_familia.descripcion , catalogo_tipo_equipo.descripcion , fecha_de_resguardo ,"
                    + "catalogo_institucion.descripcion ,  catalogo_area.descripcion, catalogo_responsable.descripcion "
                    + "from equipo join catalogo_marca on equipo.clave_marcar = catalogo_marca.clave_marcar "
                    + "join catalogo_familia on equipo.clave_familia = catalogo_familia.clave_familia "
                    + "join catalogo_tipo_equipo on equipo.clave_tipo = catalogo_tipo_equipo.clave_tipo "
                    + "join catalogo_area on equipo.clave_area = catalogo_area.clave_area "
                    + "join catalogo_institucion on equipo.clave_institucion = catalogo_institucion.clave_institucion "
                    + "join catalogo_responsable on equipo.responsable = catalogo_responsable.clave_responsable "
                    + "join movimientos on equipo.id_equipo = movimientos.id_equipo "
                    + "where  catalogo_marca.descripcion like ?   and serie like ? and catalogo_familia.descripcion like ? and  "
                    + "catalogo_tipo_equipo.descripcion like  ? and fecha_de_resguardo  between '"+fechaInicio + "' "
                    + " and '"+fechaFin + "' and catalogo_institucion.descripcion  like  ? and "
                    + "catalogo_area.descripcion like  ? and catalogo_responsable.descripcion like  ? and  movimientos.descripcion like ?");
      
        
            query.setString(1, marca);
            query.setString(2, serie);
            query.setString(3, familia);
            query.setString(4, tipo_equipo);
            query.setString(5, institucion);
            query.setString(6, area);
            query.setString(7, responsable);
            query.setString(8, estado);
            
            ResultSet rset = query.executeQuery();
               
            while (rset.next()) {
        int clave_activo_fijo = rset.getInt(1);
        int num_inv_unam = rset.getInt(2);
        String clave_marcar = rset.getString(3);
        String clave_modelo = rset.getString(4);
        String serie1 = rset.getString(5);
        String clave_familia = rset.getString(6);
        String clave_tipo = rset.getString(7);
        String fecha_de_resguardo = rset.getString(8);
        String clave_institucion = rset.getString(9);
        String clave_area = rset.getString(10);
        String responsable1 = rset.getString(11);
         
        Equipo a = new Equipo(clave_activo_fijo, num_inv_unam,
                 clave_marcar, clave_modelo, serie1, clave_familia, clave_tipo, fecha_de_resguardo,
                clave_institucion, clave_area,  responsable1);
            
                resultado.add(a);
                
                
            }
        } catch (SQLException | java.lang.ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return resultado;
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        ConexionBD con = new ConexionBD();
//   

//        con.insertaUsuario("rene", "holamundo","rene","Administrador");
//        System.out.println(con.buscaLogin("rene", "holamundo"));
//       ArrayList<String[]> lista =  con.buscaNombreLogin("caen");
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
        // System.out.println(con.actualizaCatalogo("catalogo_marca",1,"hola"));
        //       System.out.println(con.actualizaCatalogo("catalogo_marca",1,"ACER"));
        //System.out.println(con.insertaCatalogo("catalogo_marca","ACERRRRRR"));
        //System.out.println(con.regresaIDNombre("caen"));
//        System.out.println(con.insertaMovimientos(1, 1,"Baja", "12/12/1999"));
        
//     ArrayList<Equipo> a=   con.reportes("", "", "", "", "12/12/12", "", "", "", "", "");
//        System.out.println(a.size());
//    }

    }
}
