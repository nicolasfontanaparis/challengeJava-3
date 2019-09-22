/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import Excepciones.ExcepcionArchivoDePropiedadesNoEncontrado;
import Excepciones.ExcepcionErrorConexionBD;
import java.util.ArrayList;
import java.util.List;
import models.Carrera;
import models.Curso;
import models.RptInscriptosCurso;
import persistencia.DAOFactory;
import persistencia.ICursoDAO;

/**
 * Controlador que se encarga de conectarse a la base de datos y llamar a las
 * consultas a hacer en la base de datos relacionada a los cursos
 * @author Nicolás Fontana
 */
public class CtrlCursos {
    public RptInscriptosCurso getInscritosCurso(int idCarrera, int idCurso) throws ExcepcionErrorConexionBD{
        RptInscriptosCurso rpt;
        DAOFactory factory = DAOFactory.getDAOFactory();
        try {
            factory.iniciarConexion();
            ICursoDAO cursoDAO = factory.getCursoDAO();
            rpt = cursoDAO.getInscriptosCurso(idCarrera, idCurso);
            factory.cerrarConexion();
        } catch (ExcepcionErrorConexionBD|ExcepcionArchivoDePropiedadesNoEncontrado ex) {
            throw new ExcepcionErrorConexionBD("Error al conectarse a la base de datos");
        }
        return rpt;
    }
    
    public List<Carrera> getCarreras() throws ExcepcionErrorConexionBD{
        List<Carrera> carreras = new ArrayList();
        DAOFactory factory = DAOFactory.getDAOFactory();
        try {
            factory.iniciarConexion();
            ICursoDAO cursoDAO = factory.getCursoDAO();
            carreras = cursoDAO.getCarreras();
            factory.cerrarConexion();
        } catch (ExcepcionErrorConexionBD|ExcepcionArchivoDePropiedadesNoEncontrado ex) {
            throw new ExcepcionErrorConexionBD("Error al conectarse a la base de datos");
        }
        return carreras;
    }
    public List<Curso> getCursos(int idCarrera) throws ExcepcionErrorConexionBD{
        List<Curso> cursos = new ArrayList();
        DAOFactory factory = DAOFactory.getDAOFactory();
        try {
            factory.iniciarConexion();
            ICursoDAO cursoDAO = factory.getCursoDAO();
            cursos = cursoDAO.getCurso(idCarrera);
            factory.cerrarConexion();
        } catch (ExcepcionErrorConexionBD|ExcepcionArchivoDePropiedadesNoEncontrado ex) {
            throw new ExcepcionErrorConexionBD("Error al conectarse a la base de datos");
        }
        return cursos;
    }
}
