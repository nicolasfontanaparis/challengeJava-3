/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Date;
import java.util.List;

/**
 * clase utilizada para modelar el estado académico de un alumno
 * @author Nicolás Fontana
 */
public class RptEstadoAcademico {
    private Alumno alumno;
    private Carrera carrera;
    private Curso curso;
    private Date fechaInscripcionCarrera;
    private Date fechaInscripcionCurso;

    public RptEstadoAcademico() {
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Date getFechaInscripcionCarrera() {
        return fechaInscripcionCarrera;
    }

    public void setFechaInscripcionCarrera(Date fechaInscripcionCarrera) {
        this.fechaInscripcionCarrera = fechaInscripcionCarrera;
    }

    public Date getFechaInscripcionCurso() {
        return fechaInscripcionCurso;
    }

    public void setFechaInscripcionCurso(Date fechaInscripcionCurso) {
        this.fechaInscripcionCurso = fechaInscripcionCurso;
    }
    
    
    
}
