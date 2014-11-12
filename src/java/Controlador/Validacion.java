/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.io.File;
import java.io.IOException;
import javax.servlet.http.HttpSession;

/**
 *
 * @author rae
 */
public class Validacion {

    /**
     *
     */
    public Validacion() {
    }

    /**
     *
     * @param login
     * @return
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
     * @param contraseniaUno
     * @param contraseniaDos
     * @return
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
     * @param nombre
     * @return
     */
    public static boolean valida_nombre(String nombre) {

        String nombre_pat = "^([A-Za-zñ])+([\\s]{1}[A-Za-zñ]+)?([\\s]{1}[A-Za-zñ]+)?$"; //Nombres de 2 hasta hasta 70

        if (nombre == null) {
            return false;
        }

        return nombre.matches(nombre_pat) && nombre.length() >= 2 && nombre.length() <= 70;
    }

    /**
     *
     * @param email
     * @return
     */
    public static boolean valida_mail(String email) {
        String mail_pat = "^[A-Za-z0-9_](\\.?[\\w-]+)*@[a-zA-Z]+(\\.[a-zA-z]+){1,2}$";

        if (email == null) {
            return false;
        }

        return email.matches(mail_pat) && email.length() <= 70;
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        System.out.println(valida_contrasenia("holamundo", "holamundo"));

    }

}
