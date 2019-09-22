/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos.utilidades;

import Excepciones.ExcepcionArchivoDePropiedadesNoEncontrado;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;

/**
 * Clase que representa un pool de conexiones, utilizado para gestionar el
 * objeto DataSource que contiene los datos de conexión a la base de datos.
 * @author Accornero, Fontana, García, Pascal
 */
public class PoolDeConexiones {
    /**
     * Pool de conexiones
     */
    private DataSource dataSource;
    /**
     * Archivo de propiedades que contiene los datos de conexión a la 
     * base de datos.
     */
    private Properties propiedades;
    /**
     * driver de conexión a la base de datos
     */
    private String driver;
    /**
     * instancia única del pool de conexiones
     */
    private static PoolDeConexiones POOL = null;
    /**
     * Constructor de la clase que instancia un pool de conexiones, instanciando
     * un DataSource con los datos de la conexión a la base de datos que se
     * encuentra en el archivo propiedadesPostgres.properties
     * @throws Excepciones.ExcepcionArchivoDePropiedadesNoEncontrado Excepcion
     * que se lanza si ocurre un error al abrir el archivo de propiedades
     */
    private PoolDeConexiones() throws ExcepcionArchivoDePropiedadesNoEncontrado {
        this.inicializarPropiedades();
        try {
            this.dataSource = 
                    BasicDataSourceFactory.createDataSource(this.propiedades);
            this.driver = this.propiedades.getProperty("driverClassName");
            
        } catch (Exception ex) {
            throw new ExcepcionArchivoDePropiedadesNoEncontrado(ex);
        }
        
    }
    /**
     * método estático que devuelve la única instancia del pool de conexiones
     * @return instancia única de la misma clase
     * @throws ExcepcionArchivoDePropiedadesNoEncontrado excepción qeu se lanza 
     * si no se encuentra el archivo de propiedades
     */
    public static PoolDeConexiones getPoolDeConexiones() throws ExcepcionArchivoDePropiedadesNoEncontrado{
        if (PoolDeConexiones.POOL == null) {
            PoolDeConexiones.POOL =  new PoolDeConexiones();
            return PoolDeConexiones.POOL;
        } else {
            return PoolDeConexiones.POOL;
        }
    }
    /**
     * Método privado que abre el archivo de propiedades y se lo asigna a la 
     * instancia privada de propiedades.
     * @throws ExcepcionArchivoDePropiedadesNoEncontrado Excepción que se lanza
     * si ocurre un error al buscar el archivo o al intentar abrirlo
     */
    private void inicializarPropiedades() throws 
            ExcepcionArchivoDePropiedadesNoEncontrado{
        try {
            //FileInputStream f =new FileInputStream(
              //      "C:/Users/Dev/Desktop/Ff/Desa/alumnos/src/java/Propiedades/propiedadesPostgres.properties");
            FileInputStream f =new FileInputStream("C:/Users/Dev/Desktop/Ff/Desa/alumnos/src/java/Propiedades/propiedadesPostgre.properties");
            this.propiedades = new Properties();
            this.propiedades.load(f);
            f.close();
        } catch (FileNotFoundException  ex) {
            throw new ExcepcionArchivoDePropiedadesNoEncontrado(ex);
        } catch (IOException ex) {
            throw new ExcepcionArchivoDePropiedadesNoEncontrado(ex);
        }
    }
    /**
     * Método que devuelve la instancia de DataSource
     * @return instancia de DataSource
     */
    public DataSource getDataSource() {
        return this.dataSource;
    }
    /**
     * Método que retorna el driver de conexión a la base de datos
     * @return driver de conexión a la base de datos
     */
    public String getDriver() {
        return this.driver;
    }
    
}
