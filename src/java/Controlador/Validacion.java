
package Controlador;

/**
 *
 * @author rae
 */
public class Validacion {

    /**
     *
     * Clase que sirve para validar las entradas de los diferentes formularios
     * desde el lado del servidor.
     * 
     */
    public Validacion() {
    }

    /**
     *
     * Metodo que nos ayuda a validar un login.
     * 
     * @param login login que queremos validar.
     * @return
     *          true - si el login es valido.
     *          false - si el login no es valido.
     * 
     */
    public static boolean valida_login(String login) {

        String login_pat = "^[A-Za-z0-9ñ_]+$";

        if (login == null) {
            return false;
        }

        return login.matches(login_pat) && login.length() >= 4 && login.length() <= 15;
    }

     /**
      * 
      * Metodo que nos ayuda a validar contraseñas, se le piden al usuario dos veces la contraseña
      * en este metodo se verificaran las dos.
      * 
      * @param contraseniaUno la primera contraseña ( la que el usuario escribe la primera vez ).
      * @param contraseniaDos la segunda contraseña ( la que el usuario escribe la segunda vez ).
      * @return 
      * 
      *     true - si ambas contraseñas son validas y coinciden.
      *     fasle - si alguna de las contraseñas no es valida o no coinciden.
      * 
      */
    public static boolean valida_contrasenia(String contraseniaUno, String contraseniaDos) {

        String contrasenia_pat = "^[^';]+$";

        if (contraseniaUno == null || contraseniaDos == null) {
            return false;
        }

        return contraseniaUno.matches(contrasenia_pat) && contraseniaUno.length() >= 5 && contraseniaUno.length() <= 15
                && contraseniaUno.equals(contraseniaDos);
    }

    /**
     *
     * Metodo que valida el nombre de un usuario.
     * 
     * @param nombre el nombre del usuario a validar.
     * @return
     * 
     *     true - si el nombre de usuario es valido.   
     *     false - si el nombre de usuario no es valido.
     * 
     */
    public static boolean valida_nombre(String nombre) {

        String nombre_pat = "^([A-Za-zñáéíóú])+([\\s]{1}[A-Za-zñáéíóú]+)?([\\s]{1}[A-Za-zñáéíóú]+)?$"; //Nombres de 2 hasta hasta 70

        if (nombre == null) {
            return false;
        }

        return nombre.matches(nombre_pat) && nombre.length() >= 2 && nombre.length() <= 70;
    }

    /**
     *
     * Metodo que valida el correo del usuario.
     * 
     * 
     * @param  email el correo a ser validado.
     * @return
     *     
     *     true - si el correo es valido.
     *     false - si el correo no es valido.
     * 
     */
    public static boolean valida_mail(String email) {
        String mail_pat = "^[A-Za-z0-9_](\\.?[\\w-]+)*@[a-zA-Z]+(\\.[a-zA-z]+){1,2}$";

        if (email == null) {
            return false;
        }

        return email.matches(mail_pat) && email.length() <= 70;
    }

}
