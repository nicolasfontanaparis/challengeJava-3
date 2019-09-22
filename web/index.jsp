<%-- 
    Document   : index
    Created on : 17-sep-2019, 21:31:15
    Author     : Nicolás Fontana
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        <script type="text/javascript">
            function gotoAlumnos(){
                $('form_abm').remove();
                $('body')
                    .append('<form id="form_abm"></form>');
                $('#form_abm') 
                    .attr('action','ServABMAlumnos') .attr('method','post') 
                    .append('<input type="hidden" name="goto" value="list">')
                $("#form_abm").submit()
            }
            function goto_rpt_estado_alumno(){
                $('form_ea').remove();
                $('body')
                    .append('<form id="form_ea"></form>');
                $('#form_ea') 
                    .attr('action','ServReportes') .attr('method','post') 
                    .append('<input type="hidden" name="goto" value="rptEstadoAcademico">')
                $("#form_ea").submit()
            }
            function goto_rpt_cursos(){
                $('form_cu').remove();
                $('body')
                    .append('<form id="form_cu"></form>');
                $('#form_cu') 
                    .attr('action','ServReportes') .attr('method','post') 
                    .append('<input type="hidden" name="goto" value="rptInscriptos">')
                $("#form_cu").submit()
            }
        </script>
        <title>Alumnos</title>
    </head>
    <body>
        <div class="jumbotron">
            <h1 class="display-3">App Alumnos</h1>
            <p class="lead">Gestión de alumnos y reportes</p>
            <hr class="my-4">
            <p class="lead">
            <a class="btn btn-primary btn-lg" href="#" role="button" onclick="gotoAlumnos()">ABM Alumnos</a>
            <div class="dropdown">
                <button class="btn btn-primary btn-lg dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                  Reportes
                  <span class="caret"></span>
                </button>
                <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                    <li><a href="#" onclick="goto_rpt_estado_alumno()">Estado Académico</a></li>
                    <li><a href="#" onclick="goto_rpt_cursos()">Cursos</a></li>
                </ul>
            </div>
            </p>
        </div>
    </body>
</html>
