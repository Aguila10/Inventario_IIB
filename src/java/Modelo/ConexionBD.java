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
 *Clase donde se encunetra todo lo relacionado a la base de datos
 * encontramos metodos para insertar , actualizar y dar de baja.
 * @author CaenHiro
 */
public class ConexionBD {

    String driver = "org.postgresql.Driver";
    String connectString = "jdbc:postgresql://localhost:5432/Inventario";
    String user = "darktech";
    String password = "darktech";

    /**
     * Metodo que recibe el login y la contraseña
     * @param login
     * @param pass
     * @return La categoria de la persoan en otro caso regresa error
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
     *Metodo que recibe  el login
     * @param login
     * @return un boolean true si esta false en otro caso
     */
    public boolean regresaBuscaLogin(String login) {
        boolean res = true;
        Statement statement;
        ResultSet resultSet;

        try {
            Connection con = DriverManager.getConnection(connectString, user, password);
            statement = con.createStatement();
            resultSet = statement.executeQuery("SELECT * from   buscaLogin ('" + login + "');");

            while (resultSet.next()) {
                res = resultSet.getBoolean(1);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return res;
    }

    /**
     * Metodo que regresa un arreglo de string con todos los nombres y login de
     * todos los usuarios MENOS del nombre o login que recibe
     * @param login_nombre
     * @return una lista d arreglos en la posicion 0 esta el login en la posicion 1 esta el nombre
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
     * Metodo que inserta un usuario recibe el login , la contraseña , 
     * el nombre y la categoria
     *@param login
     * @param pass
     * @param nombre
     * @param categoria
     * @return un boolean true si fue exitoso , false en otro caso
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
     * Metodo que inserta un equipo recibe los parametros necesarios para
     * insertar un equipo si no pone nada si ponen con la descripcion S/N
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
     * @return un boolean true si fue exitoso , false en otro caso
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
     *Metodo que actualiza un equipo
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
     * @return un boolean true si fue exitoso , false en otro caso
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
     * @param catalogo
     * @return lista con las descripciones de los catalogos
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
     * Metodo que actualiza catalogos recibe el nombre de la tabla, 
     * el identificador de la tubla y la nueva descripcion 
     * @param tabla
     * @param id_catalogo
     * @param descrip
     * @return un boolean true si fue exitoso , false en otro caso
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
     *Metodo que nos regresa el identificador maximo de un catalogo
     * @param tabla
     * @return el numero del identificador del catalogo dado
     */
    public int regresaMaxCatalogo(String tabla) {
      int res = 0;
    
        
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
            PreparedStatement query = con.prepareStatement("select max("+ id_catalogo_tabla +") from " + tabla + ";");

           ResultSet rset = query.executeQuery();
            while (rset.next()) {
                res = (rset.getInt(1));
                
            }

        } catch (SQLException | java.lang.ClassNotFoundException e) {
 
            System.out.println(e.getMessage());
        }

        return res;
    }

    
    /**
     * Metodo que inserta a los catalogos recibe un nombre de la tabla y la
     * descripcion 
     * @param tabla
     * @param descrip
     * @return un boolean false si fue exitoso , true en otro caso
     */
    public boolean insertaCatalogo(String tabla, String descrip) {
        

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

        
        ConexionBD con1 = new ConexionBD();
        
        int id = con1.regresaMaxCatalogo(tabla) +1;
        
        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(connectString, user, password);
            PreparedStatement query = con.prepareStatement("insert into  " + tabla + " ("+ id_catalogo_tabla +" , descripcion) values ( "
               + id  + " ,  '" + descrip + "' );");

            ResultSet rset = query.executeQuery();

        } catch (SQLException | java.lang.ClassNotFoundException e) {
        
            System.out.println(e.getMessage());
        return false;

        }

      return true;
    }

    /**
     *Metodo para insertar un catalogo_familia 
     * este fue diferente ya que tenia asociado otro catalogo
     * @param descrip
     * @param id
     * @return un boolean false si fue exitoso , true en otro caso
     */
    public boolean insertaCatalogoFamilia(String descrip, int id) {
        

          ConexionBD con1 = new ConexionBD();
        String tabla = "catalogo_familia";
        
        int id1 = con1.regresaMaxCatalogo(tabla) +1;
        
        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(connectString, user, password);
            PreparedStatement query = con.prepareStatement("insert into  catalogo_familia (clave_familia , descripcion, clave_tipo) values ( "
               + id1     + " , '" + descrip + "' , " + id + ");");

            ResultSet rset = query.executeQuery();

        } catch (SQLException | java.lang.ClassNotFoundException e) {
            
            System.out.println(e.getMessage());
            
            return false;
        }

       return true; 
    }

    /**
     * Metodo que solo nos regresa los catalogos con su identificador
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
     *Metodo que nos regresa un equipo recibe el identificar del equipo
     * @param id_equipo
     * @return un objeto equipo con todos los valores de id que se pide
     */
    public Equipo regresaEquipo(int id_equipo) {

        int id_equipo1, clave_activo_fijo, num_inv_unam;
        String clave_descripcion, clave_modelo, clave_marcar, serie,
                clave_familia, clave_tipo, clave_proveedor, clase,
                uso, nivel_de_obsolescencia, estado_físico,
                clave_area, clave_institucion, id_usuario_asignado,
                fecha_de_resguardo, responsable;
        boolean estado;
        Equipo equipoResultado = null;
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

                equipoResultado = new Equipo(id_equipo1, clave_activo_fijo, num_inv_unam,
                        clave_descripcion, clave_modelo, clave_marcar, serie, clave_familia, clave_tipo, clave_proveedor, clase,
                        uso, nivel_de_obsolescencia, estado_físico, clave_area, clave_institucion,
                        fecha_de_resguardo, responsable, estado);
            }
        } catch (SQLException | java.lang.ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return equipoResultado;
    }

    /**
     * Metodo que elimina un usuario recibe el login de usuario a eliminar
     * @param login
     * @return un boolean true si fue exitoso , false en otro caso
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
     *Metodo que inserta un movimiento recibe id del usuario
     * id del equipo , la descripcion y la fecha
     * @param id_usuario
     * @param id_equipo
     * @param descripcion
     * @param fecha
     * @return un boolean true si fue exitoso , false en otro caso
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
     *Metodo que regresa la marca , serie y departamento de un  equipo
     * @param id_equipo
     * @return una lista de arreglos en la posicion 0 esta la marca ,
     * en la posicion 1 esta la serie , en la posicon 2 esta departamento , 
     * en la posicion 3 esta el id del equipo
     */
    public ArrayList<String[]> regresaMarcaSerieDeparta(int id_equipo) {
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
                    + "where num_inv_unam =  " + id_equipo + "or" + " clave_activo_fijo = " + id_equipo);

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
     *Metodo que recibe
     * @param login
     * @return regresa el nombre del login recibido 
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
     *Metodo que recibe el 
     * @param login
     * @return no regresa el idenificador del login recibido 
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

    /**
     *Metodo que genera los reportes recibe
     * @param marca
     * @param serie
     * @param familia
     * @param tipo_equipo
     * @param fechaInicio
     * @param fechaFin
     * @param institucion
     * @param area
     * @param responsable
     * @param estado
     * @return nos regresa una lista d quipos con todas las coincidencia pedidas
     */
    public ArrayList<Equipo> reportes(String marca, String serie, String familia, String tipo_equipo,
            String fechaInicio, String fechaFin, String institucion, String area, String responsable,
            String estado) {

        ArrayList<Equipo> resultado = new ArrayList<Equipo>();
        marca = marca.equals("") ? "%" : marca;
        serie = serie.equals("") ? "%" : serie;
        familia = familia.equals("") ? "%" : familia;
        tipo_equipo = tipo_equipo.equals("") ? "%" : tipo_equipo;
        fechaInicio = fechaInicio.equals("") ? "12/12/86" : fechaInicio;
        fechaFin = fechaFin.equals("") ? "12/12/30" : fechaFin;
        institucion = institucion.equals("") ? "%" : institucion;
        area = area.equals("") ? "%" : area;
        responsable = responsable.equals("") ? "%" : responsable;
        estado = estado.equals("") ? "%" : estado;

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
                    + "catalogo_tipo_equipo.descripcion like  ? and fecha_de_resguardo  between '" + fechaInicio + "' "
                    + " and '" + fechaFin + "' and catalogo_institucion.descripcion  like  ? and "
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
                        clave_institucion, clave_area, responsable1);

                resultado.add(a);

            }
        } catch (SQLException | java.lang.ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return resultado;
    }

   
}
