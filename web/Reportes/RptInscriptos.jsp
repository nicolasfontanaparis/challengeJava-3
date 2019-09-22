<%-- 
    Document   : RptInscriptos.jsp
    Created on : 22-sep-2019, 16:00:03
    Author     : Nicolás Fontana
--%>

<%@page import="models.InscripcionCurso"%>
<%@page import="models.DocenteCargoDocente"%>
<%@page import="models.RptInscriptosCurso"%>
<%@page import="java.util.Iterator"%>
<%@page import="controladores.CtrlCursos"%>
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
        <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
        
        <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.10/css/select2.min.css" rel="stylesheet" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.10/js/select2.min.js"></script>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <title>Inscripciones</title>
        <script>
            <%String baseURL = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();%>
            function goto_inicial(){
                $('form_inicial').remove();
                $('body')
                    .append('<form id="form_inicial"></form>');
                $('#form_inicial') 
                    .attr('action','<%=baseURL%>/ServABMAlumnos') .attr('method','post') 
                $("#form_inicial").submit();
            }
            function get_cursos(){
                if($('#carrera :selected').val() == '-1'){
                    $('#curso').empty();
                    $('#curso').append('<option value="-1" selected="selected">seleccione una carrera</option>')
                } else{
                    $.ajax({
                        url: "<%=baseURL%>/ServReportes",
                        data: {
                            goto:"ajaxCuros",
                            idcarrera:$('#carrera :selected').val()
                        },
                        cache: false,
                        dataType:"json",
                        success:(function (data){
                            $('#curso').empty();
                            for(var i = 0; i<data.length; i++){
                                $('#curso').append('<option value="'+data[i].id+'">'+data[i].desc+'</option>')
                            }    
                        })
                    });
                }
            }
        </script>
    </head>
    <body>
        <h1 class="page-header">Estado académico</h1>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="collapse navbar-collapse" id="navbarColor02">
            <%
            CtrlCursos ctrl = new CtrlCursos();
            List<Carrera> carreras = ctrl.getCarreras();
            Iterator it = carreras.iterator();
            //--------------------------------------
            int idcarrera = -1;
            if(request.getParameter("carrera") != null){
                idcarrera = Integer.parseInt(request.getParameter("carrera"));
            }
            //--------------------------------------
            int idcurso = -1;
            if(request.getParameter("curso") != null){
                idcurso = Integer.parseInt(request.getParameter("curso"));
            }
            %>
            <form class="form-inline my-2 my-lg-0" method="post" action="">
                <select class="form-control" name="carrera" id="carrera" onchange="get_cursos();">
                    <option value="-1" onchange="get_cursos()">seleccione...</option>
                    <% String selected= ""; %>
                    <%while(it.hasNext()){%>
                        <% Carrera ca = (Carrera) it.next();%>
                        <%
                        if (ca.getIdentificador() == idcarrera){
                            selected = "selected=\"selected\"";
                        }
                        %>
                        <option <%=selected%> value="<%=ca.getIdentificador()%>"><%=ca.getNombre()%></option>
                    <%}%>
                </select>
                &nbsp:
                <select class="form-control" name="curso" id="curso">
                    <option value="-1">seleccione una carrera...</option>
                </select>
                <button class="btn btn-secondary my-2 my-sm-0" type="submit">Buscar</button>
                &nbsp;
                <button class="btn btn-secondary btn-warning my-2 my-sm-0" type="button" onclick="goto_inicial()">Volver</button>
            </form>
        </div>
        </nav>
        <%
        if(idcarrera != -1 && idcurso != -1){
            RptInscriptosCurso rpt = ctrl.getInscritosCurso(idcarrera, idcurso);
        %>
        <hr>
            <h2><%=rpt.getCurso().getNombre()%></h2>
            <br>
            <% if(rpt.getCurso().getDocentes().isEmpty()){%>
               <div class="alert alert-dismissible alert-danger">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    No existen docentes para el curso
                </div> 
            <%} else{%>
                <table class="table">
                    <thead>
                        <tr>
                            <th colspan="4"><h2>Docentes</h2></th>
                        </tr>
                        <tr>
                            <th>Documento</th>
                            <th>Apellido</th>
                            <th>Nombre</th>
                            <th>Cargo</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for(DocenteCargoDocente dcd:rpt.getCurso().getDocentes()){ %>
                            <tr>
                                <td><%=dcd.getDocente().getNroDocumento()%></td>
                                <td><%=dcd.getDocente().getApellido()%></td>
                                <td><%=dcd.getDocente().getNombre()%></td>
                                <td><%=dcd.getCargoDocente().getDescripcion()%></td>
                            </tr>
                        <%}%>
                    </tbody>
                </table>
            <%}%>
            <br>
            <%if(rpt.getAlumnos().isEmpty()){%>
                <div class="alert alert-dismissible alert-danger">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    No existen alumnos inscriptos al curso
                </div> 
            <%}%>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th colspan="4">Alumnos Inscriptos</th>
                    </tr>
                    <tr>
                        <th>Documento</th>
                        <th>Apellido</th>
                        <th>Nombre</th>
                        <th>Fecha de Inscripción</th>
                    </tr>
                </thead>
                <tbody>
                    <% for(InscripcionCurso ic: rpt.getAlumnos()){ %>
                        <tr>
                            <td><%=ic.getAlumno().getNroDocumento()%></td>
                            <td><%=ic.getAlumno().getApellido()%></td>
                            <td><%=ic.getAlumno().getNombre()%></td>
                            <td><%=ic.getFechaInscripcion()%></td>
                        </tr>
                    <%}%>
                </tbody>
            </table>                
            <%}%>
    </body>
</html>
