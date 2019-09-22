/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import controladores.CtrlAlumnos;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Alumno;
import recursos.utilidades.Redirect;

/**
 *
 * @author Nicolás Fontana
 */
public class ServABMAlumnos extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String view = request.getParameter("goto");
            if(view == null){
                view = "list";
            }
            RequestDispatcher dispatcher;
            CtrlAlumnos ctrlAlumnos = new CtrlAlumnos();
            switch (view){
                case "update":
                    try{
                        String msj_error = "";
                        boolean hay_error = false;
                        if(ctrlAlumnos.existeDni(Integer.parseInt(request.getParameter("documento")), Integer.parseInt(request.getParameter("idpersona")))){
                            msj_error = "Ya existe el documento que est&aacute; intentando cargar";
                            hay_error = true;
                        }
                        if(ctrlAlumnos.existeLegajo(Integer.parseInt(request.getParameter("legajo")), Integer.parseInt(request.getParameter("idalumno")))){
                            msj_error = "Ya existe el legajo que est&aacute; intentando cargar";
                            hay_error = true;
                        }
                        if(hay_error){
                            Map<String, String> map = new HashMap();
                            map.put("documento", request.getParameter("documento"));
                            map.put("apellido", request.getParameter("apellido"));
                            map.put("nombre", request.getParameter("nombre"));
                            map.put("fechanacimiento", request.getParameter("fechanacimiento"));
                            map.put("legajo", request.getParameter("legajo"));
                            map.put("idalumno", request.getParameter("idalumno"));
                            map.put("msj", msj_error);
                            Redirect redirect = new Redirect(map, "ABMAlumnos/editAlumno.jsp");
                            out.print(redirect.stringPost());
                        } else {
                            Alumno al =  ctrlAlumnos.updateAlumno(Integer.parseInt(
                                    request.getParameter("documento")), 
                                    request.getParameter("apellido"), 
                                    request.getParameter("nombre"),
                                    Date.valueOf(request.getParameter("fechanacimiento")),
                                    Integer.parseInt(request.getParameter("legajo")),
                                    Integer.parseInt(request.getParameter("idalumno"))
                                    );
                            Map<String, String> map = new HashMap();
                            map.put("dni", String.valueOf( al.getNroDocumento()));
                            map.put("msj", "Se actualiz&oacute; el alumno con &eacute;xito");
                            Redirect redirect = new Redirect(map, "ABMAlumnos/listAlumnos.jsp");
                            out.print(redirect.stringPost());
                        }
                    } catch (Exception ex) {
                        String a = ex.getMessage();
                    }
                    break;
                case "list":
                    dispatcher = request.getRequestDispatcher("/ABMAlumnos/listAlumnos.jsp");
                    dispatcher.forward(request, response);
                    break;
                case "edit":
                    dispatcher = request.getRequestDispatcher("/ABMAlumnos/editAlumno.jsp");
                    dispatcher.forward(request, response);
                    break;
                case "insert":
                    try{
                        String msj_error = "";
                        boolean hay_error = false;
                        if(ctrlAlumnos.existeDni(Integer.parseInt(request.getParameter("documento")))){
                            msj_error = "Ya existe el documento que est&aacute; intentando cargar";
                            hay_error = true;
                        }
                        if(ctrlAlumnos.existeLegajo(Integer.parseInt(request.getParameter("legajo")))){
                            msj_error = "Ya existe el legajo que est&aacute; intentando cargar";
                            hay_error = true;
                        }
                        if(hay_error){
                            Map<String, String> map = new HashMap();
                            map.put("documento", request.getParameter("documento"));
                            map.put("apellido", request.getParameter("apellido"));
                            map.put("nombre", request.getParameter("nombre"));
                            map.put("fechanacimiento", request.getParameter("fechanacimiento"));
                            map.put("legajo", request.getParameter("legajo"));
                            map.put("msj", msj_error);
                            Redirect redirect = new Redirect(map, "ABMAlumnos/addAlumno.jsp");
                            out.print(redirect.stringPost());
                        } else {
                            Alumno al =  ctrlAlumnos.insertAlumno(Integer.parseInt(
                                    request.getParameter("documento")), 
                                    request.getParameter("apellido"), 
                                    request.getParameter("apellido"), 
                                    Date.valueOf(request.getParameter("fechanacimiento")),
                                    Integer.parseInt(request.getParameter("legajo"))
                                    );
                            Map<String, String> map = new HashMap();
                            map.put("dni", String.valueOf( al.getNroDocumento()));
                            map.put("msj", "Se carg&oacute; el alumno con &eacute;xito");
                            Redirect redirect = new Redirect(map, "ABMAlumnos/listAlumnos.jsp");
                            out.print(redirect.stringPost());
                        }
                    } catch (Exception ex) {
                        String a = ex.getMessage();
                    }
                    break;
                case "add":
                    response.sendRedirect("ABMAlumnos/addAlumno.jsp");
                    break;
                default:
                    response.sendRedirect("../index.jsp");
                    break;
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
