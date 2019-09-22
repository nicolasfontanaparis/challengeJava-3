/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import Excepciones.ExcepcionArchivoDePropiedadesNoEncontrado;
import Excepciones.ExcepcionErrorConexionBD;
import persistencia.postgres.PostgresDAOFactory;

/**
 *
 * @author Nicolás Fontana
 */
public abstract class DAOFactory {
    /**
     * instancia única en toda la ejecución del programa de la implementación
     * de la clase <code>DAOFactory</code>
    */
    private static DAOFactory FACTORY = null;
    /**
     * método de clase que devuelve la instancia del factory de la base de 
     * datos para conectarse, desconectarse, consultar y actualizar datos.
     * @return variable de clase que contiene el acceso a la base de datos.
     */
    public static DAOFactory getDAOFactory(){
        if(DAOFactory.FACTORY == null) {
            return new PostgresDAOFactory();
        } else {
            return DAOFactory.FACTORY;
        }
    }
    /**
     * definición del método que inicia la conexión a la base de datos
     * @throws Excepciones.ExcepcionErrorConexionBD excepción que se lanza
     * si ocurre un error de conexión
     * @throws Excepciones.ExcepcionArchivoDePropiedadesNoEncontrado se lanza 
     * si no se encuentra el archivo de propiedades con los datos para 
     * conectarse a la base de datos
     */
    public abstract void iniciarConexion() throws ExcepcionErrorConexionBD, 
            ExcepcionArchivoDePropiedadesNoEncontrado;
    /**
     * definición del método que cierra la conexión a la base de datos.
     * @throws Excepciones.ExcepcionErrorConexionBD Excepción que se lanza
     * si ocurre un error al desconectarse a la base de datos
     */
    public abstract void cerrarConexion() throws ExcepcionErrorConexionBD;
    
    /**
     * Definición del método para obtener un objeto de acceso a los datos de 
     * los alumnos y lo relacionado a él
     * @return 
     */
    public abstract IAlumnoDAO getAlumnoDAO();
    
    /**
     * Definición del método para obtener un objecto de accedo a los datos de 
     * los cursos y todo lo vinculado a él
     * @return 
     */
    public abstract ICursoDAO getCursoDAO();
}
