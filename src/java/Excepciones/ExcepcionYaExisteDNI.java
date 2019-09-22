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
public class ExcepcionYaExisteDNI extends Exception{
    public ExcepcionYaExisteDNI(){
        super("Ya existe el dni que está intentando ingresar");
    }
}
