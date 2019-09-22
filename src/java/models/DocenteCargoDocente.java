/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Nicolás Fontana
 */
public class DocenteCargoDocente {
    private Docente docente;
    private CargoDocente cargoDocente;
    private Curso curso;

    public DocenteCargoDocente() {
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public CargoDocente getCargoDocente() {
        return cargoDocente;
    }

    public void setCargoDocente(CargoDocente cargoDocente) {
        this.cargoDocente = cargoDocente;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
    
    
}
