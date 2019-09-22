/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.postgres;

import Excepciones.ExcepcionAlumnoNoEncontrado;
import Excepciones.ExcepcionErrorConexionBD;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.Alumno;
import models.CargoDocente;
import models.Carrera;
import models.Curso;
import models.Docente;
import models.DocenteCargoDocente;
import models.InscripcionCurso;
import models.RptInscriptosCurso;
import models.tipos.enumTiposDocumentos;
import persistencia.ICursoDAO;

/**
 * 
 * @author Nicolás Fontana
 */
public class CursoDAO implements ICursoDAO {
    
    /**
     * Instancia de la conexión a la base de datos
     */
    private final Connection conexion;
    
    /**
     * string sql para obtener los inscriptos a un curso y su corespondiente 
     * curso.
     */
    private final String sqlInscriptosCurso;
    
    private final String sqlCarreras;
    
    private final String sqlCursos;
    
    /**
     * Constructor de la clase que instancia un cursoDAO para hacer las consulta
     * a la base de datos de los cursos y lo relacionado a éste.
     * @param conexion instancia de la conexión a la base de datos
     */
    public CursoDAO(Connection conexion){
        this.conexion = conexion;
        this.sqlInscriptosCurso = "select ca.identificador id_carrera, ca.nombre nombre_carrera, "
                + "ca.descripcion descripcion_carrera, ca.fechadesde fechadesde_carrera, "
                + "ca.fechahasta fechahasta_carrera,  cu.identificador id_curso, "
                + "cu.nombre nombre_curso, cu.descripcion descripcion_curso, "
                + "cu.cupomaximo cupomaximo_curso, cu.anio anio_curso,  "
                + "al.identificador id_alumno, al.legajo legajo_alumno,  "
                + "pe.identificador id_persona_alumno, pe.documento documento_alumno, "
                + "pe.nombre nombre_alumno, pe.apellido apellido_alumno, pe.fechanac fechanacimiento_alumno,  "
                + "doc.identificador id_docente,  pe_doc.identificador id_persona_docente, "
                + "pe_doc.documento documento_docente, pe_doc.nombre nombre_docente, "
                + "pe_doc.apellido apellido_docente, pe_doc.fechanac "
                + "fechanacimiento_docente, ca_do.identificador id_cargo_docente, "
                + "ca_do.descripcion descripcion_cargo_docente, in_cu.fechainscripcion fechainscripcion_curso "
                + "from curso cu "
                + "inner join carrera ca on cu.idcarrera = ca.identificador  "
                + "left join inscripciones_curso in_cu on in_cu.idcurso = cu.identificador "
                + "left join alumno al on in_cu.idalumno = al.identificador "
                + "left join persona pe on al.idpersona = pe.identificador  "
                + "left join curso_docente cu_do on cu_do.idcurso = cu.identificador  "
                + "left join docente doc on cu_do.iddocente = doc.identificador  "
                + "left join persona pe_doc on pe_doc.identificador = doc.idpersona  "
                + "left join cargo_docente ca_do on ca_do.identificador = cu_do.idcargodocente "
                + "where ca.identificador = ? and cu.identificador = ? "
                + " order by id_docente, id_alumno  ";
        this.sqlCarreras = "select identificador, nombre, descripcion, fechadesde, fechahasta from carrera";
        this.sqlCursos = "select identificador, nombre, descripcion, anio from curso where idcarrera = ?";
    }
    /**
     * Implementación para obtener los inscriptos a un curso a partir de un curso
     * determinado
     * @param idcarrera
     * @param idcurso
     * @param anio
     * @return
     * @throws ExcepcionErrorConexionBD 
     */
    @Override
    public RptInscriptosCurso getInscriptosCurso(int idcarrera, int idcurso) throws ExcepcionErrorConexionBD {
        RptInscriptosCurso rptInscriptosCurso = new RptInscriptosCurso();
        List<InscripcionCurso> listaInscripcion = new ArrayList();
        PreparedStatement sentenciaSQL = null;
        List<DocenteCargoDocente> listaDocentes = new ArrayList();
        
        try {
            ResultSet rs;
            Statement st;
            st = this.conexion.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            sentenciaSQL = this.conexion.prepareStatement(this.sqlInscriptosCurso);
            sentenciaSQL.setInt(1,idcarrera);
            sentenciaSQL.setInt(2,idcurso);
            rs = st.executeQuery(sentenciaSQL.toString());
            Carrera ca = new Carrera();
            rs.next();
            ca.setIdentificador(rs.getInt("id_carrera"));
            ca.setNombre(rs.getString("nombre_carrera"));
            ca.setDescripcion(rs.getString("descripcion_carrera"));
            ca.setFechaDesde(rs.getDate("fechadesde_carrera"));
            ca.setFechaHasta(rs.getDate("fechahasta_carrera"));
            //---------------------------------------------------
            Curso curso = new Curso();
            curso.setIdentificador(rs.getInt("id_curso"));
            curso.setNombre(rs.getString("nombre_curso"));
            curso.setDescripcion(rs.getString("descripcion_curso"));
            curso.setAnio(rs.getInt("anio_curso"));
            curso.setCupoMaximo(rs.getInt("cupomaximo_curso"));
            //---------------------------------------------------
            int id_docente_anterior = -1;
            int id_docente_actual;
            rs.previous();
            while(rs.next()){
                //RptInscriptosCurso filaRpt = new RptInscriptosCurso();
                InscripcionCurso ic = new InscripcionCurso();
                curso.setCarrera(ca);
                //filaRpt.setCurso(curso);
                if((rs.getString("id_docente")) != null){
                    id_docente_actual = rs.getInt(rs.getInt("id_docente"));
                    if(id_docente_actual != id_docente_anterior){
                        Docente docente = new Docente();
                        CargoDocente cargoDocente = new CargoDocente();
                        DocenteCargoDocente docenteCargo = new DocenteCargoDocente();
                        docente.setIdPersona(rs.getInt("id_persona_docente"));
                        docente.setNroDocumento(rs.getInt("documento_docente"));
                        docente.setIdDocente(rs.getInt("id_docente"));
                        docente.setNombre(rs.getString("nombre_docente"));
                        docente.setApellido(rs.getString("apellido_docente"));
                        docente.setFechaNacimiento(rs.getDate("fechanacimiento_docente"));
                        cargoDocente.setIdentificador(rs.getInt("id_cargo_docente"));
                        cargoDocente.setDescripcion(rs.getString("descripcion_cargo_docente"));
                        docenteCargo.setCargoDocente(cargoDocente);
                        docenteCargo.setDocente(docente);
                        docenteCargo.setCurso(curso);
                        listaDocentes.add(docenteCargo);
                        id_docente_anterior = id_docente_actual;
                    }
                }
            }
            curso.setDocentes(listaDocentes);
            rs.first();
            while(rs.next()){
                InscripcionCurso ic = new InscripcionCurso();
                if((rs.getString("id_alumno")) != null){
                    Alumno alumno = new Alumno();
                    alumno.setIdAlumno(rs.getInt("id_alumno"));
                    alumno.setNroDocumento(rs.getInt("documento_alumno"));
                    alumno.setIdPersona(rs.getInt("id_persona_alumno"));
                    alumno.setNombre(rs.getString("nombre_alumno"));
                    alumno.setApellido(rs.getString("apellido_alumno"));
                    alumno.setFechaNacimiento(rs.getDate("fechanacimiento_alumno"));
                    alumno.setLegajo(rs.getInt("legajo_alumno"));
                    alumno.setTipoDocumento(enumTiposDocumentos.dni);
                    ic.setAlumno(alumno);
                    ic.setCurso(curso);
                    ic.setFechaInscripcion(rs.getDate("fechainscripcion_curso"));
                    listaInscripcion.add(ic);
                }
            }
            rptInscriptosCurso.setAlumnos(listaInscripcion);
            rptInscriptosCurso.setCurso(curso);
        } catch(SQLException ex){
            throw new ExcepcionErrorConexionBD("error al conectarse a la base de datos", ex);
        }
        return rptInscriptosCurso;
    }

    @Override
    public List<Carrera> getCarreras() throws ExcepcionErrorConexionBD {
        PreparedStatement sentenciaSQL = null;
        List<Carrera> listaCarreras = new ArrayList();
        try {
            ResultSet rs;
            Statement st;
            st = this.conexion.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            sentenciaSQL = this.conexion.prepareStatement(this.sqlCarreras);
            rs = st.executeQuery(sentenciaSQL.toString());
            while(rs.next()){
                Carrera carrera = new Carrera();
                carrera.setIdentificador(rs.getInt("identificador"));
                carrera.setDescripcion(rs.getString("descripcion"));
                carrera.setNombre(rs.getString("nombre"));
                carrera.setFechaDesde(rs.getDate("fechadesde"));
                carrera.setFechaHasta(rs.getDate("fechahasta"));
                listaCarreras.add(carrera);
            }
        } catch(SQLException ex){
            throw  new ExcepcionErrorConexionBD("Error de conexión a la base de datos", ex);
        }
        return listaCarreras;
    }

    @Override
    public List<Curso> getCurso(int idCarrera) throws ExcepcionErrorConexionBD {
        PreparedStatement sentenciaSQL = null;
        List<Curso> listaCurso = new ArrayList();
        try {
            ResultSet rs;
            Statement st;
            st = this.conexion.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            sentenciaSQL = this.conexion.prepareStatement(this.sqlCursos);
            sentenciaSQL.setInt(1, idCarrera);
            rs = st.executeQuery(sentenciaSQL.toString());
            while(rs.next()){
                Curso cu = new Curso();
                cu.setIdentificador(rs.getInt("identificador"));
                cu.setDescripcion(rs.getString("descripcion"));
                cu.setNombre(rs.getString("nombre"));
                cu.setAnio(rs.getInt("anio"));
                listaCurso.add(cu);
            }
        } catch(SQLException ex){
            throw  new ExcepcionErrorConexionBD("Error de conexión a la base de datos", ex);
        }
        return listaCurso;
    }
    
}
