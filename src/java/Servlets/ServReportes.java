/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Excepciones.ExcepcionErrorConexionBD;
import controladores.CtrlAlumnos;
import controladores.CtrlCursos;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.DefaultEditorKit;
import models.Curso;

/**
 *
 * @author Nicolás Fontana
 */
public class ServReportes extends HttpServlet {

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
        response.setContentType("text/html;charset=ISO-8859-1");
        CtrlCursos ctrl = new CtrlCursos();
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String view = request.getParameter("goto");
            switch (view){
                case "ajaxCuros":
                    List<Curso> lista = ctrl.getCursos(Integer.parseInt(request.getParameter("idcarrera")));
                    Iterator it = lista.iterator();
                    String resp = "[";
                    String coma = "";
                    while(it.hasNext()){
                        Curso cu = (Curso)it.next();
                        resp += coma + "{\"id\":\""+cu.getIdentificador()+"\",\"desc\":\""+cu.getNombre()+ "-" + cu.getAnio()+"\"}";
                        coma = ",";
                    }
                    resp += "]";
                    out.print(resp);
                    break;
                case "rptInscriptos":
                    response.sendRedirect("Reportes/RptInscriptos.jsp");
                    break;
                case "rptEstadoAcademico":
                    response.sendRedirect("Reportes/RptEstadoAcademico.jsp");
                    break;
                default:
                    response.sendRedirect("index.jsp");
                    break;
            }
        } catch (ExcepcionErrorConexionBD ex) {
            Logger.getLogger(ServReportes.class.getName()).log(Level.SEVERE, null, ex);
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
