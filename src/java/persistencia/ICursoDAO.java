/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import Excepciones.ExcepcionErrorConexionBD;
import java.util.List;
import models.Carrera;
import models.Curso;
import models.RptInscriptosCurso;

/**
 * interfaz para definir los métodos referenctes a los cursos
 * @author Nicolás Fontana
 */
public interface ICursoDAO {
    public RptInscriptosCurso getInscriptosCurso(int idcarrera, int idcurso) throws ExcepcionErrorConexionBD;
    public List<Carrera> getCarreras()throws ExcepcionErrorConexionBD;
    public List<Curso> getCurso(int idCarrera) throws ExcepcionErrorConexionBD;
}