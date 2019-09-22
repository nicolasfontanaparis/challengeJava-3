/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import Excepciones.ExcepcionAlumnoNoEncontrado;
import Excepciones.ExcepcionArchivoDePropiedadesNoEncontrado;
import Excepciones.ExcepcionErrorConexionBD;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import models.Alumno;
import models.RptEstadoAcademico;
import persistencia.DAOFactory;
import persistencia.IAlumnoDAO;

/**
 * Cointrolador que se encarga de obtener lo relacionado al alumno.
 * Se encarga de conectarse a la base de datos y llamar al DAO correspondiente
 * para devolver los solicitado
 * @author Nicolás Fontana
 */
public class CtrlAlumnos {
    /**
     * Método que devuelve la lista completa de alumnos filtrando por alguno
     * de los parámetros
     * @param dni dni del alumno que se desea buscar. Matchea por igual
     * @param apellido apellido del alumno que se desea buscar. Matchea por like
     * @param nombre nombre del alumno que se desea buscar. Matchea por like
     * @return {@link ArrayList ArrayList} que contiene instancias de {@link Alumno Alumno}
     * @throws Exception si ocurre una excepción al conectarse a la base de datos
     */
    public List<Alumno>getListadoAlumnos(int dni, String apellido, String nombre) throws Exception{
        List<Alumno> listaAlumnos = new ArrayList();
        DAOFactory factory = DAOFactory.getDAOFactory();
        try {
            factory.iniciarConexion();
            IAlumnoDAO alumnoDAO = factory.getAlumnoDAO();
            listaAlumnos = alumnoDAO.getAlumnos(dni, apellido, nombre);
            factory.cerrarConexion();
        } catch (ExcepcionErrorConexionBD|ExcepcionArchivoDePropiedadesNoEncontrado ex) {
            throw new Exception("Error al conectarse a la base de datos");
        } 
        return listaAlumnos;
    }
    
    public Alumno getAlumnoPorId(int idAlumno) throws ExcepcionAlumnoNoEncontrado, Exception{
        Alumno al = new Alumno();
        DAOFactory factory = DAOFactory.getDAOFactory();
        try {
            factory.iniciarConexion();
            IAlumnoDAO alumnoDAO = factory.getAlumnoDAO();
            al = alumnoDAO.getAlumnoPorId(idAlumno);
            factory.cerrarConexion();
        } catch (ExcepcionErrorConexionBD|ExcepcionArchivoDePropiedadesNoEncontrado ex) {
            throw new Exception("Error al conectarse a la base de datos");
        }
        return al;
    }
    public Alumno getAlumnoPorDNI(int dni) throws ExcepcionAlumnoNoEncontrado, Exception{
        Alumno al = new Alumno();
        DAOFactory factory = DAOFactory.getDAOFactory();
        try {
            factory.iniciarConexion();
            IAlumnoDAO alumnoDAO = factory.getAlumnoDAO();
            al = alumnoDAO.getAlumnoPorDni(dni);
            factory.cerrarConexion();
        } catch (ExcepcionErrorConexionBD|ExcepcionArchivoDePropiedadesNoEncontrado ex) {
            throw new Exception("Error al conectarse a la base de datos");
        }
        return al;
    }
    public List<RptEstadoAcademico> getReporteEstadoAcademico(int dni) throws ExcepcionAlumnoNoEncontrado, Exception{
        List<RptEstadoAcademico> listaRpt;
        DAOFactory factory = DAOFactory.getDAOFactory();
        try {
            factory.iniciarConexion();
            IAlumnoDAO alumnoDAO = factory.getAlumnoDAO();
            listaRpt = alumnoDAO.getEstadoAcademico(dni);
            factory.cerrarConexion();
        } catch (ExcepcionErrorConexionBD|ExcepcionArchivoDePropiedadesNoEncontrado ex) {
            throw new Exception("Error al conectarse a la base de datos");
        }
        return listaRpt;
    }
    public boolean existeDni(int dni) throws Exception{
        DAOFactory factory = DAOFactory.getDAOFactory();
        boolean existe = false;
        try {
            factory.iniciarConexion();
            IAlumnoDAO alumnoDAO = factory.getAlumnoDAO();
            existe = alumnoDAO.existeDocumento(dni);
            factory.cerrarConexion();
        } catch (ExcepcionErrorConexionBD|ExcepcionArchivoDePropiedadesNoEncontrado ex) {
            throw new Exception("Error al conectarse a la base de datos");
        }
        return existe;
    }
     public boolean existeLegajo(int legajo) throws Exception{
        DAOFactory factory = DAOFactory.getDAOFactory();
        boolean existe = false;
        try {
            factory.iniciarConexion();
            IAlumnoDAO alumnoDAO = factory.getAlumnoDAO();
            existe = alumnoDAO.existeLegajo(legajo);
            factory.cerrarConexion();
        } catch (ExcepcionErrorConexionBD|ExcepcionArchivoDePropiedadesNoEncontrado ex) {
            throw new Exception("Error al conectarse a la base de datos");
        }
        return existe;
    }
    public Alumno insertAlumno(int dni, String apellido, String nombre, Date fechanacimiento, int legajo) throws Exception{
        DAOFactory factory = DAOFactory.getDAOFactory();
        Alumno alumno;
        try {
            factory.iniciarConexion();
            IAlumnoDAO alumnoDAO = factory.getAlumnoDAO();
            alumno = alumnoDAO.insertAlumno(dni, apellido, nombre, fechanacimiento, legajo);
            factory.cerrarConexion();
        } catch (ExcepcionErrorConexionBD|ExcepcionArchivoDePropiedadesNoEncontrado ex) {
            throw new Exception("Error al conectarse a la base de datos");
        }
        return alumno;
    }
    public Alumno updateAlumno(int dni, String apellido, String nombre, Date fechanacimiento, int legajo, int idAlumno) throws Exception{
        DAOFactory factory = DAOFactory.getDAOFactory();
        Alumno alumno;
        try {
            factory.iniciarConexion();
            IAlumnoDAO alumnoDAO = factory.getAlumnoDAO();
            alumno = alumnoDAO.updateAlumno(dni, apellido, nombre, fechanacimiento, legajo, idAlumno);
            factory.cerrarConexion();
        } catch (ExcepcionErrorConexionBD|ExcepcionArchivoDePropiedadesNoEncontrado ex) {
            throw new Exception("Error al conectarse a la base de datos");
        }
        return alumno;
    }
    public boolean existeDni(int dni, int idPersoma) throws Exception{
        DAOFactory factory = DAOFactory.getDAOFactory();
        boolean existe = false;
        try {
            factory.iniciarConexion();
            IAlumnoDAO alumnoDAO = factory.getAlumnoDAO();
            existe = alumnoDAO.existeDocumentoUpdate(dni, idPersoma);
            factory.cerrarConexion();
        } catch (ExcepcionErrorConexionBD|ExcepcionArchivoDePropiedadesNoEncontrado ex) {
            throw new Exception("Error al conectarse a la base de datos");
        }
        return existe;
    }
     public boolean existeLegajo(int legajo, int idAlumno) throws Exception{
        DAOFactory factory = DAOFactory.getDAOFactory();
        boolean existe = false;
        try {
            factory.iniciarConexion();
            IAlumnoDAO alumnoDAO = factory.getAlumnoDAO();
            existe = alumnoDAO.existeLegajoUpdate(legajo, idAlumno);
            factory.cerrarConexion();
        } catch (ExcepcionErrorConexionBD|ExcepcionArchivoDePropiedadesNoEncontrado ex) {
            throw new Exception("Error al conectarse a la base de datos");
        }
        return existe;
    }
}
