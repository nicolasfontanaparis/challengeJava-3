/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;
    import java.util.Date;

import models.tipos.enumTiposDocumentos;
/**
 * clase que representa una persona
 * @author Nicolás Fontana
 */
public abstract class Persona {
    
    /**
     * id de la persona que es clave primaria en la base de datos
     */
    protected int idPersona;
    /**
    * tipo de documento de la persona
    */
   protected enumTiposDocumentos TipoDocumento ;
   /**
    * Número de documento de la persona
    */
   protected long NroDocumento;

   /**
    * nombre de la persona
    */
   protected String Nombre;

   /**
    * Apellido de la persona
    */
   protected String Apellido;

   /**
    * Fecha de Nacimiento de la persona
    */
   protected Date FechaNacimiento;

/**
    * devuelve el tipo de documento de la persona
    * @return tipo de documento de la persona. Devuelve un enum del tipo {@link tipos.enumTiposDocumentos enumTiposDocumentos}
    */
   public enumTiposDocumentos getTipoDocumento() {
           return this.TipoDocumento;
   }

   /**
    * devuelve el tipo de documento en formato string
    * @return tipo de documento en formato string
    */
   public String getTipoDocumentoString() {
           return this.TipoDocumento.toString();
   }

   /**
    * setea el tipo de documento de la persona. 
    * @param tipoDocumento tipo de documento a setear del tipo {@link tipos.enumTiposDocumentos enumTiposDocumentos}
    */
   public void setTipoDocumento(enumTiposDocumentos tipoDocumento) {
           this.TipoDocumento = tipoDocumento;
   }

   /**
    * devuelve el número de documento de la persona
    * @return número de documento de la persona
    */
   public long getNroDocumento() {
           return this.NroDocumento;
   }

   /**
    * setea el número de documento de la persona
    * @param nroDocumento valor entero que representa el número de documento de la persona
    */
   public void setNroDocumento(int nroDocumento) {
           this.NroDocumento = nroDocumento;
   }

   /**
    * devuelve el nombre de la p ersona
    * @return nombre de la persona
    */
   public String getNombre() {
           return this.Nombre;
   }

   /**
    * setea el nombre de la persona
    * @param nombre nombre de la persona
    */
   public void setNombre(String nombre) {
           this.Nombre = nombre;
   }

   /**
    * devuelve el apellido de la persona
    * @return apellido de la persona
    */
   public String getApellido() {
           return this.Apellido;
   }

   /**
    * setea el apellido de la persona
    * @param apellido apellido de la persona a setear
    */
   public void setApellido(String apellido) {
           this.Apellido = apellido;
   }

   /**
    *  devuelve la fecha de nacimiento de la persona
    * @return fecha de nacimiento de la persona
    */
   public Date getFechaNacimiento() {
           return this.FechaNacimiento;
   }

   /**
    * setea la fecha de nacimiento de la persona
    * @param fechaNacimiento fecha de nacimiento de la persona a setear
    */
   public void setFechaNacimiento(Date fechaNacimiento) {
           this.FechaNacimiento = fechaNacimiento;
   }
   /**
    * devuelve el id de la persona  
    * @return id de la persona que es clave primaria en la base de datos
    */
    public int getIdPersona() {
        return this.idPersona;
    }
    /**
     * setea el id de persona
     * @param identificador id de persona que es clave primaria en la base
     * de datos
     */
    public void setIdPersona(int identificador) {
        this.idPersona = identificador;
    }
}
