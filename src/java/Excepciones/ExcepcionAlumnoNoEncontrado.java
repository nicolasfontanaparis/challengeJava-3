/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Excepciones;

/**
 *
 * @author Nicolás Fontana
 */
public class ExcepcionAlumnoNoEncontrado extends Exception{
    /**
     * Constructor que instanacia la excepción con un mensaje de error por 
     * defecto y la excepción original lanzada por la aplicación
     * @param excepcionOriginal excecpón original que se catchea y se pasa por
     * parámetro
     */
    public ExcepcionAlumnoNoEncontrado(
            Exception excepcionOriginal){
        super("No se pudo encontrar el alumno según los parámeteos seleccionados",
                excepcionOriginal);
    }
    /**
     * Constructor que instancia la excepción con un mensaje de error pasado
     * por parámetro. Se utiliza para especificar mejor el error que ocurre.
     * También inicializa el error original
     * @param excepcionOriginal instancia del error original que ocurrió
     * @param mensajeError mensaje de error
     */
    public ExcepcionAlumnoNoEncontrado(
            Exception excepcionOriginal, String mensajeError) {
        super(mensajeError, excepcionOriginal);
    }
    /**
     * Constructor que isntancia una excepción con un mensaje de error por 
     * defecto
     */
    public ExcepcionAlumnoNoEncontrado(){
        super("No se pudo encontrar el alumno según los parámeteos seleccionados");
    }
}
