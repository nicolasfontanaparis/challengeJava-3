/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import Excepciones.ExcepcionAlumnoNoEncontrado;
import Excepciones.ExcepcionYaExisteDNI;
import java.sql.Date;
import java.util.List;
import models.Alumno;
import models.RptEstadoAcademico;

/**
 * Interfaz que define los métodos a implementar para la gestión de alumnos
 * @author Nicolás Fontana
 */
public interface IAlumnoDAO {
    public List<Alumno> getAlumnos(int pDni, String pApellido, String pNombre);
    public Alumno getAlumnoPorId(int identificadorAlumno) throws Excepciones.ExcepcionAlumnoNoEncontrado;
    public boolean existeDocumento(int dni);
    public boolean existeLegajo(int legajo);
    public Alumno getAlumnoPorDni(int dni) throws ExcepcionAlumnoNoEncontrado;
    public List<RptEstadoAcademico> getEstadoAcademico(int dni)throws ExcepcionAlumnoNoEncontrado;
    public Alumno insertAlumno(int dni, String apellido, String nombre, Date fechaNacimiento, int legajo)throws Excepciones.ExcepcionErrorConexionBD;
    public Alumno updateAlumno(int dni, String apellido, String nombre, Date fechaNacimiento, int legajo, int idAlumno)throws Excepciones.ExcepcionErrorConexionBD;
    public boolean existeDocumentoUpdate(int dni, int idPersona);
    public boolean existeLegajoUpdate(int legajo, int idAlumno);
}   
