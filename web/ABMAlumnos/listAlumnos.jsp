<%-- 
    Document   : default
    Created on : 17-sep-2019, 21:48:39
    Author     : Nicolás Fontana
--%>
<%@page import="controladores.CtrlAlumnos"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="Excepciones.ExcepcionArchivoDePropiedadesNoEncontrado"%>
<%@page import="java.util.logging.Level"%>
<%@page import="Excepciones.ExcepcionErrorConexionBD"%>
<%@page import="java.util.List"%>
<%@page import="persistencia.IAlumnoDAO"%>
<%@page import="models.Alumno"%>
<%@page import="persistencia.DAOFactory"%>
<%@page import="Servlets.ServABMAlumnos" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        <script>
            <%String baseURL = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();%>
            function goto_edit(id_alumno){
                $('form_editar').remove();
                $('body')
                    .append('<form id="form_editar"></form>');
                $('#form_editar') 
                    .attr('action','<%=baseURL%>/ServABMAlumnos') .attr('method','post') 
                    .append('<input type="hidden" name="goto" value="edit">')
                    .append('<input type="hidden" name="idalumno" value="'+id_alumno+'">')
                $("#form_editar").submit()
            }
            function goto_add(){
                $('form_add').remove();
                $('body')
                    .append('<form id="form_add"></form>');
                $('#form_add') 
                    .attr('action','<%=baseURL%>/ServABMAlumnos') .attr('method','post') 
                    .append('<input type="hidden" name="goto" value="add">')
                $("#form_add").submit()
            }
            function goto_principal(){
                $('form_principal').remove();
                $('body')
                    .append('<form id="form_principal"></form>');
                $('#form_principal') 
                    .attr('action','<%=baseURL%>') .attr('method','post') 
                $("#form_principal").submit()
            }
        </script>
        <title>ABM Alumnos</title>
    </head>
    <body>
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
            //--------------------------------------
            String apellido = "";
            if(request.getParameter("apellido")!= null){
                apellido = request.getParameter("apellido");
            }
            //--------------------------------------
            String nombre = "";
            if(request.getParameter("nombre")!= null){
                nombre = request.getParameter("nombre");
            }
            %>
            <form class="form-inline my-2 my-lg-0" method="post" action="<%=baseURL%>/ServABMAlumnos">
                <input type="hidden" name="goto" value="list">
                <input name="documento" value="<%=dni_value%>" class="form-control mr-sm-2" type="text" data-type="number" placeholder="dni...">
                <input name="apellido" value="<%=apellido%>" class="form-control mr-sm-2" type="text" placeholder="apellido...">
                <input name="nombre" value="<%=nombre%>" class="form-control mr-sm-2" type="text" placeholder="nombre...">
                <button class="btn btn-secondary my-2 my-sm-0" type="submit">Buscar</button>
                &nbsp;
                <button class="btn btn-secondary btn-success my-2 my-sm-0" type="button" onclick="goto_add();">Nuevo</button>
                &nbsp;
                <button class="btn btn-secondary btn-warning my-2 my-sm-0" type="button" onclick="goto_principal();">Volver</button>
          </form>
        </div>
        </nav>
        <hr>
        <%
        String msj = request.getParameter("msj");
        if (msj != null){
            %>
            <div class="alert alert-dismissible alert-success">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                <%=msj%>
            </div>
            <%
        }
        %>
        <%
        CtrlAlumnos ctrl = new CtrlAlumnos();
        
        
        List<Alumno> listaAlumnos = ctrl.getListadoAlumnos(dni, apellido,nombre);
        %>
        <div align="center">
            <table class="table">
                <thead>
                    <tr>
                    <th>DNI</th>
                    <th>Legajo</th>
                    <th>Apellido</th>
                    <th>Nombre</th>
                    <th>Fecha de Nacimiento</th>
                    <th>&nbsp;</th>
                    </tr>
                </thead>
                <%for(Alumno al:listaAlumnos){%>
                <tr>
                    <td><%= al.getPersona().getNroDocumento()%></td>
                    <td><%= al.getLegajo()%></td>
                    <td><%= al.getPersona().getApellido()%></td>
                    <td><%= al.getPersona().getNombre()%></td>
                    <td><%= al.getPersona().getFechaNacimiento()%></td>
                    <td><button type="button" class="btn btn-xs btn-primary" onclick="goto_edit('<%=al.getIdentificador()%>')">
                            <span class="glyphicon glyphicon-edit"></span> Editar
                        </button></td>
                </tr>
                <%}%>
            </table>
        </div>
    </body>
</html>
