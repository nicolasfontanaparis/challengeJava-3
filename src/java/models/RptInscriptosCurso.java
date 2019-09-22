/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.List;

/**
 *
 * @author Nicolás Fontana
 */
public class RptInscriptosCurso {
    private Curso curso;
    List<InscripcionCurso> alumnos;
    
    
    public RptInscriptosCurso() {
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public List<InscripcionCurso> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<InscripcionCurso> alumnos) {
        this.alumnos = alumnos;
    }
    
    
}
