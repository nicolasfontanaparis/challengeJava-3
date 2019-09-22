<%-- 
    Document   : addAlumno
    Created on : 19-sep-2019, 19:06:31
    Author     : Nicolás Fontana
--%>

<%@page import="models.Alumno"%>
<%@page import="Excepciones.ExcepcionAlumnoNoEncontrado"%>
<%@page import="controladores.CtrlAlumnos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        <link href="../libs/form-validation/dist/css/formValidation.min.css" rel="stylesheet">
        <script src="../libs/form-validation/dist/js/formValidation.min.js"></script>
        <script src="../libs/form-validation/dist/js/framework/bootstrap.min.js"></script>
        <script src="../libs/form-validation/dist/js/language/es_CL.js"></script>
        <script>
            function goto_volver(){
                $('form_volver').remove();
                $('body')
                    .append('<form id="form_volver"></form>');
                $('#form_volver') 
                    .attr('action','../ServABMAlumnos') .attr('method','post')
                    .append('<input type="hidden" name="goto" value="list">')
                $("#form_volver").submit()
            }
        </script>
        <script>
        $(document).ready(function() {
          $('#form_factura').formValidation({
           framework: 'bootstrap',
           err: {container: 'tooltip'},
           icon: {     valid:'glyphicon glyphicon-ok',
             invalid:'glyphicon glyphicon-remove',
             validating: 'glyphicon glyphicon-refresh gly-spin'
           },
           excluded: ['[readonly]'],
           locale: 'es_CL',
           live: 'submitted',
           message: 'Este valor no es valido',
           autoFocus : true,
           button: {
            selector: '[type="submit"]:not([formnovalidate])',
            disabled: false
           },
                    fields: {
                        documento: {
                            validators: {
                                notEmpty: {
                                    message: 'ingrese un legajo'
                                },
                                stringLength: {
                                    min: 8,
                                    max: 8,
                                    message: 'el legajo debe tener entre 4 y 6 dígitos',
                                },
                            }
                        },
                    }
          }).on('success.form.fv', function(e) {e.preventDefault();});
         });         
        </script>
        <title>Alta de alumno</title>
    </head>
    <body>
        <h1 class="page-header">Nuevo alumno</h1>
        <%
        String documento = request.getParameter("documento");
        if (documento == null){
            documento = "";
        }
        String apellido = request.getParameter("apellido");
        if(apellido == null){
            apellido = "";
        }
        String nombre = request.getParameter("nombre");
        if(nombre == null){
            nombre = "";
        }
        String fechanacimiento = request.getParameter("fechanacimiento");
        String legajo = request.getParameter("legajo");
        if (legajo == null){
            legajo = "";
        }
        
        String msj = request.getParameter("msj");
        if (msj != null){
            %>
            <div class="alert alert-dismissible alert-danger">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                <%=msj%>
            </div>
            <%
        }
        %>
        <form class="form-horizontal" method="post" action="../ServABMAlumnos" id="form_alumno">
            <input type="hidden" name="goto" value="insert">
            <div class="form-group">
              <div class="col-sm-10">
                  <input data-fv-field="documento" name="documento" id="documento" maxlength="8" type="number" class="form-control" placeholder="número de documento..." value="<%=documento%>">
              </div>
            </div>
            <div class="form-group">
              <div class="col-sm-10">
                <input name="apellido" id="apellido" type="text" class="form-control" placeholder="apellido..." required="required" value="<%=apellido%>">
              </div>
            </div>
            <div class="form-group">
              <div class="col-sm-10">
                <input name="nombre" id="nombre" type="text" class="form-control" placeholder="nombre..." required="required" value="<%=nombre%>">
              </div>
            </div>
              <div class="form-group">
              <div class="col-sm-10">
                <input name="fechanacimiento" id="fechanacimiento" type="date" class="form-control" placeholder="fecha de nacimiento..." required="required" value="<%=fechanacimiento%>">
              </div>
            </div>
            <div class="form-group">
              <div class="col-sm-10">
                  <input name="legajo" id="legajo" maxlength="8" type="number" class="form-control" placeholder="legajo..." required="required" value="<%=legajo%>">
              </div>
            </div>
            <div class="form-group">
              <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-success">Guardar</button>
                &nbsp;
                <button type="button" class="btn btn-warning" onclick="goto_volver();">Volver</button>
              </div>
            </div>
          </form>
    </body>
</html>
