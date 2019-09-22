<%-- 
    Document   : RptEstadoAcademico
    Created on : 19-sep-2019, 6:23:41
    Author     : Nicolás Fontana
--%>

<%@page import="Excepciones.ExcepcionAlumnoNoEncontrado"%>
<%@page import="models.Curso"%>
<%@page import="models.CursoAlumno"%>
<%@page import="models.CarreraAlumno"%>
<%@page import="models.Carrera"%>
<%@page import="models.RptEstadoAcademico"%>
<%@page import="java.util.List"%>
<%@page import="models.Alumno"%>
<%@page import="controladores.CtrlAlumnos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.10/css/select2.min.css" rel="stylesheet" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.10/js/select2.min.js"></script>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <title>Estado Académico</title>
        <script>
            function goto_inicial(){
                $('form_inicial').remove();
                $('body')
                    .append('<form id="form_inicial"></form>');
                $('#form_inicial') 
                    .attr('action','../ServABMAlumnos') .attr('method','post') 
                $("#form_inicial").submit()
            }
        </script>
    </head>
    <body>
        <h1 class="page-header">Estado académico</h1>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            
        <div class="collapse navbar-collapse" id="navbarColor02">
            <%
            //--------------------------------------
            int dni = -1;
            String dni_value = "";
            if(request.getParameter("dni") != null){
                dni = Integer.parseInt(request.getParameter("dni"));
                dni_value = request.getParameter("dni");
            }
            %>
            <form class="form-inline my-2 my-lg-0" method="post" action="">
                <input name="dni" value="<%=dni_value%>" class="form-control mr-sm-2" type="text" data-type="number" placeholder="dni...">
                <button class="btn btn-secondary my-2 my-sm-0" type="submit">Buscar</button>
                &nbsp;
                <button class="btn btn-secondary btn-warning my-2 my-sm-0" type="button" onclick="goto_inicial()">Volver</button>
            </form>
        </div>
        </nav>
        <%
        if(dni != -1){
            CtrlAlumnos ctrl = new CtrlAlumnos();
            try{
                Alumno al = ctrl.getAlumnoPorDNI(dni);
                List<RptEstadoAcademico> listaRpt = ctrl.getReporteEstadoAcademico(al.getIdentificador());
                %>
                <div align="center">
                <table class="table table-bordered">
                    <thead>
                        <tr>
                        <th>DNI</th>
                        <th>Legajo</th>
                        <th>Apellido</th>
                        <th>Nombre</th>
                        <th>Fecha de Nacimiento</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td><%= al.getNroDocumento()%></td>
                            <td><%= al.getLegajo()%></td>
                            <td><%= al.getApellido()%></td>
                            <td><%= al.getNombre()%></td>
                            <td><%= al.getFechaNacimiento()%></td>
                        </tr>
                    </tbody>
                </table>
                <hr>
                <h2>Carreras</h2>
                <% int id_carrera_anterior= -1; %>
                <% for(RptEstadoAcademico fila:listaRpt){%>
                    <% int id_carrera_actual = fila.getCarrera().getIdentificador();%>
                    <% if (id_carrera_actual != id_carrera_anterior){ %>
                        <h3><%=fila.getCarrera().getNombre()%></h3>
                        <% id_carrera_anterior = id_carrera_actual; %>
                    <%}%>
                    <% if(fila.getCurso() !=null){ %>    
                        <label class="label">Curso:&nbsp;</label><%=fila.getCurso().getNombre()%>
                        <br>
                        <label class="label">Inscripto:&nbsp; </label><%=fila.getFechaInscripcionCurso()%>
                        <hr>
                    <%}%>
                <%}%>
                <%
            }catch(ExcepcionAlumnoNoEncontrado ex){
                %>
                <div class="alert alert-dismissible alert-danger">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    No se encontró el alumno con el dni <%=dni%>
                </div>
                <%
            }
        }
            %>
    </body>
</html>
