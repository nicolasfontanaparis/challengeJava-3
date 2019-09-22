/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;
import models.tipos.enumTiposDocumentos;

/**
 *
 * @author Nicolás Fontana
 */
public class Alumno extends Persona{
    
    /**
     * identificador del alumno en la base de datos
     */
    private int idAlumno;

    /**
     * legajo del alumno
     */
    private int legajo;
    

    /**
     * Constructor de la clase que instancia un Alumno sin inicializar sus variables de instancia
     */
    public Alumno() {
    }

    
   
   
    /**
     * devuelve el legajo del alumno
     * @return legajo del alumno
     */
    public int getLegajo() {
            return this.legajo;
    }

    /**
     * setea el valor del legajo del alumno
     * @param legajo entero que representa el legajo del alumno
     */
    public void setLegajo(int legajo) {
            this.legajo = legajo;
    }
    /**
     * devuelve el id del alumno
     * @return clave primaria del alumno en la base de datos
     */
    public int getIdentificador() {
        return this.idAlumno;
    }
    
    /**
     * setea le id de la persona
     * @param identificador id del alumno en la base de datos
     */
    public void setIdAlumno(int identificador) {
        this.idAlumno = identificador;
    }
    
}
