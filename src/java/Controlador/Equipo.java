/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

/**
 *
 * @author caenhiro
 */
public class Equipo {

    int id_equipo;
    int clave_activo_fijo;
    int num_inv_unam;
    String clave_descripcion;
    String clave_modelo;
    String clave_marcar;
    String serie;
    String clave_familia;
    String clave_tipo;
    String clave_proveedor;
    String clase;
    String uso;
    String nivel_de_obsolescencia;
    String estado_físico;
    String clave_area;
    String clave_institucion;

    String fecha_de_resguardo;
    String responsable;
    boolean estado;

 
    public Equipo(int id_equipo, int clave_activo_fijo, int num_inv_unam ,
            String clave_descripcion, String clave_modelo, String clave_marcar, String serie,
            String clave_familia, String clave_tipo, String clave_proveedor, String clase,
            String uso, String nivel_de_obsolescencia, String estado_físico, String clave_area,
            String clave_institucion, String fecha_de_resguardo, String responsable, boolean estado) {

        this.id_equipo = id_equipo;
        this.clave_activo_fijo = clave_activo_fijo;
        this.num_inv_unam = num_inv_unam ;
        this.clave_descripcion = clave_descripcion;
        this.clave_modelo = clave_modelo;
        this.clave_marcar = clave_marcar;
        this.serie = serie;
        this.clave_familia = clave_familia;
        this.clave_tipo = clave_tipo;
        this.clave_proveedor = clave_proveedor;
        this.clase = clase;
        this.uso = uso;
        this.nivel_de_obsolescencia = nivel_de_obsolescencia;
        this.estado_físico = estado_físico;
        this.clave_area = clave_area;
        this.clave_institucion = clave_institucion;

        this.fecha_de_resguardo = fecha_de_resguardo;
        this.responsable = responsable;
        this.estado = estado;

    }

    
        public Equipo( int b, int c,
             String e, String f, String g, String h, String i, 
             String ñ, String o, String q, String r) {

        this.clave_activo_fijo = b;
        this.num_inv_unam = c;
        this.clave_marcar = e;
        this.clave_modelo = f;
        this.serie = g;
        this.clave_familia = h;
        this.clave_tipo = i;
        this.fecha_de_resguardo = ñ;
        this.clave_institucion = o;
        this.clave_area = q;
        this.responsable = r;
        

    }

    
    
    
    
    
    /**
     * @return the id_equipo
     */
    public int getId_equipo() {
        return id_equipo;
    }

    /**
     * @param id_equipo the id_equipo to set
     */
    public void setId_equipo(int id_equipo) {
        this.id_equipo = id_equipo;
    }

    /**
     * @return the clave_activo_fijo
     */
    public int getClave_activo_fijo() {
        return clave_activo_fijo;
    }

    /**
     * @param clave_activo_fijo the clave_activo_fijo to set
     */
    public void setClave_activo_fijo(int clave_activo_fijo) {
        this.clave_activo_fijo = clave_activo_fijo;
    }

    /**
     * @return the num_inv_unam
     */
    public int getNum_inv_unam() {
        return num_inv_unam;
    }

    /**
     * @param num_inv_unam the num_inv_unam to set
     */
    public void setNum_inv_unam(int num_inv_unam) {
        this.num_inv_unam = num_inv_unam;
    }

    /**
     * @return the clave_descripcion
     */
    public String getClave_descripcion() {
        return clave_descripcion;
    }

    /**
     * @param clave_descripcion the clave_descripcion to set
     */
    public void setClave_descripcion(String clave_descripcion) {
        this.clave_descripcion = clave_descripcion;
    }

    /**
     * @return the clave_modelo
     */
    public String getClave_modelo() {
        return clave_modelo;
    }

    /**
     * @param clave_modelo the clave_modelo to set
     */
    public void setClave_modelo(String clave_modelo) {
        this.clave_modelo = clave_modelo;
    }

    /**
     * @return the clave_marcar
     */
    public String getClave_marcar() {
        return clave_marcar;
    }

    /**
     * @param clave_marcar the clave_marcar to set
     */
    public void setClave_marcar(String clave_marcar) {
        this.clave_marcar = clave_marcar;
    }

    /**
     * @return the serie
     */
    public String getSerie() {
        return serie;
    }

    /**
     * @param serie the serie to set
     */
    public void setSerie(String serie) {
        this.serie = serie;
    }

    /**
     * @return the clave_familia
     */
    public String getClave_familia() {
        return clave_familia;
    }

    /**
     * @param clave_familia the clave_familia to set
     */
    public void setClave_familia(String clave_familia) {
        this.clave_familia = clave_familia;
    }

    /**
     * @return the clave_tipo
     */
    public String getClave_tipo() {
        return clave_tipo;
    }

    /**
     * @param clave_tipo the clave_tipo to set
     */
    public void setClave_tipo(String clave_tipo) {
        this.clave_tipo = clave_tipo;
    }

    /**
     * @return the clave_proveedor
     */
    public String getClave_proveedor() {
        return clave_proveedor;
    }

    /**
     * @param clave_proveedor the clave_proveedor to set
     */
    public void setClave_proveedor(String clave_proveedor) {
        this.clave_proveedor = clave_proveedor;
    }

    /**
     * @return the clase
     */
    public String getClase() {
        return clase;
    }

    /**
     * @param clase the clase to set
     */
    public void setClase(String clase) {
        this.clase = clase;
    }

    /**
     * @return the uso
     */
    public String getUso() {
        return uso;
    }

    /**
     * @param uso the uso to set
     */
    public void setUso(String uso) {
        this.uso = uso;
    }

    /**
     * @return the nivel_de_obsolescencia
     */
    public String getNivel_de_obsolescencia() {
        return nivel_de_obsolescencia;
    }

    /**
     * @param nivel_de_obsolescencia the nivel_de_obsolescencia to set
     */
    public void setNivel_de_obsolescencia(String nivel_de_obsolescencia) {
        this.nivel_de_obsolescencia = nivel_de_obsolescencia;
    }

    /**
     * @return the estado_físico
     */
    public String getEstado_físico() {
        return estado_físico;
    }

    /**
     * @param estado_físico the estado_físico to set
     */
    public void setEstado_físico(String estado_físico) {
        this.estado_físico = estado_físico;
    }

    /**
     * @return the clave_area
     */
    public String getClave_area() {
        return clave_area;
    }

    /**
     * @param clave_area the clave_area to set
     */
    public void setClave_area(String clave_area) {
        this.clave_area = clave_area;
    }

    /**
     * @return the clave_institucion
     */
    public String getClave_institucion() {
        return clave_institucion;
    }

    /**
     * @param clave_institucion the clave_institucion to set
     */
    public void setClave_institucion(String clave_institucion) {
        this.clave_institucion = clave_institucion;
    }

    /**
     * @return the fecha_de_resguardo
     */
    public String getFecha_de_resguardo() {
        return fecha_de_resguardo;
    }

    /**
     * @param fecha_de_resguardo the fecha_de_resguardo to set
     */
    public void setFecha_de_resguardo(String fecha_de_resguardo) {
        this.fecha_de_resguardo = fecha_de_resguardo;
    }

    /**
     * @return the responsable
     */
    public String getResponsable() {
        return responsable;
    }

    /**
     * @param responsable the responsable to set
     */
    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    /**
     * @return the estado
     */
    public boolean isEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

}
