/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.postgres;

import Excepciones.ExcepcionAlumnoNoEncontrado;
import Excepciones.ExcepcionErrorConexionBD;
import Excepciones.ExcepcionYaExisteDNI;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Alumno;
import models.Carrera;
import models.CarreraAlumno;
import models.Curso;
import models.CursoAlumno;
import models.Persona;
import models.RptEstadoAcademico;
import models.tipos.enumTiposDocumentos;
import persistencia.IAlumnoDAO;

/**
 *
 * @author Nicolás Fontana
 */
public class AlumnoDAO implements IAlumnoDAO{
    /**
     * Instancia de la conexión a la base de datos
     */
    private Connection conexion;
    
    /**
     * string de consulta sql para listar alumnos
     */
    private String sqlListAlumnos;
    
    /**
     * string de consulta sql para buscar un alumno por id
     */
    private String sqlAlumnoPorId;
    
    private String sqlUpdatePersona;
    private String sqlUpdateAlumno;
    private String sqlInsertAlumno;
    private String sqlExisteDni;
    private String sqlExisteLegajo;
    private String sqlAlumnoPorDni;
    private String sqlRptEstadoAcademico;
    private String sqlGetNextIdAlumno;
    private String sqlGetNextIdPersona;
    private String sqlInsertPersona;
    private String sqlGetNextLegajo;
    private String sqlExisteDniUpdate;
    private String sqlExisteLegajoUpdate;
    /**
     * constructor de la clase que inicializa los string de las consultas y
     * la conexión a al base de datos
     * @param conexion instancia de la conexión a la base de datos
     */
    public AlumnoDAO(Connection conexion){
        this.conexion = conexion;
        this.sqlListAlumnos = "select al.identificador id_alumno, al.legajo, "
                + "pe.identificador id_persona, pe.tipodoc, pe.documento, "
                + "pe.nombre, pe.apellido, pe.fechanac from alumno al "
                + "inner join persona pe on al.idpersona = pe.identificador "
                + "where (pe.documento = ? or -1 = ?) and (trim(upper(pe.apellido))"
                + " like trim(upper(?))) "
                + "and (trim(upper(pe.nombre)) like trim(upper(?)))";
        this.sqlAlumnoPorId = "select al.identificador id_alumno, al.legajo, "
                + "pe.identificador id_persona, pe.tipodoc, pe.documento, "
                + "pe.nombre, pe.apellido, pe.fechanac from alumno al "
                + "inner join persona pe on al.idpersona = pe.identificador "
                + "where al.identificador = ?";
        this.sqlUpdatePersona = "update persona set documento = ?, apellido = ?, "
                + "nombre = ?, fechanac = ? where identificador = ?";
        this.sqlUpdateAlumno = "update alumno set legajo = ? where identificador = ?";
        this.sqlExisteDni = "select 1 from persona where documento = ?";
        this.sqlExisteLegajo = "select 1 from alumno where legajo = ?";
        this.sqlAlumnoPorDni = "select al.identificador id_alumno, al.legajo, "
                + "pe.identificador id_persona, pe.tipodoc, pe.documento, "
                + "pe.nombre, pe.apellido, pe.fechanac from alumno al "
                + "inner join persona pe on al.idpersona = pe.identificador "
                + "where pe.documento = ?";
        this.sqlRptEstadoAcademico = "select ca.identificador id_carrera, "
                + "ca.nombre nombre_carrera, ca.descripcion descripcion_carrera, "
                + "ca.fechadesde fechadesde_carrera, ca.fechahasta fechahasta_carrera, "
                + "in_ca.fechainscripcion fechainscripcion_carrera, "
                + "cursos_alumno.id_curso, cursos_alumno.nombre_curso, "
                + "cursos_alumno.descripcion_curso, cursos_alumno.cupomaximo, "
                + "cursos_alumno.anio, cursos_alumno.fechainscripcion_curso from "
                + "inscripciones_carrera in_ca inner join carrera ca on "
                + "in_ca.idcarrera = ca.identificador left join ("
                + "select cu.identificador id_curso, cu.nombre nombre_curso, "
                + "cu.descripcion descripcion_curso, cu.cupomaximo, cu.anio, "
                + "in_cu.fechainscripcion fechainscripcion_curso, cu.idcarrera, "
                + "in_cu.idalumno from inscripciones_curso in_cu inner join "
                + "curso cu on in_cu.idcurso = cu.identificador) cursos_alumno "
                + "on cursos_alumno.idcarrera = ca.identificador and "
                + "cursos_alumno.idalumno = in_ca.idalumno where in_ca.idalumno = ? "
                + "order by id_carrera, id_curso";
        this.sqlGetNextIdAlumno = "select COALESCE(max(identificador),0) + 1 "
                + "as nextid from alumno";
        this.sqlGetNextIdPersona = "select COALESCE(max(identificador),0) + 1 "
                + "as nextid from persona";
        this.sqlInsertPersona = "insert into persona (identificador, tipodoc, "
                + "documento, nombre, apellido, fechanac) values(?,?,?,?,?,?)";
        this.sqlInsertAlumno = "insert into alumno(identificador, idpersona, legajo)"
                + "values(?,?,?)";
        this.sqlGetNextLegajo = "select COALESCE(max(legajo),0) + 1 "
                + "as nextid from alumno";
        this.sqlExisteDniUpdate = "select 1 from persona where documento = ? and identificador <> ?";
        this.sqlExisteLegajoUpdate  = "select 1 from alumno where legajo = ? and identificador <> ?";
    }
    /**
     * Método que se conecta a la base de datos y consulta un listado de alumnos
     * por parámetros de búsqueda
     * @param pDni dni del alumno a buscar
     * @param pApellido apellido del alumno a buscar
     * @param pNombre nombre del alumno a buscar
     * @return {@link ArrayList ArrayList} de objetos de la clase 
     * {@link Alumno Alumno}
     */
    @Override
    public List<Alumno> getAlumnos(int pDni, String pApellido, String pNombre) {
        ArrayList<Alumno> listaAlumnos = new ArrayList();
        PreparedStatement sentenciaSQL = null;
        try {
            ResultSet rs;
            Statement st;
            st = this.conexion.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            sentenciaSQL = this.conexion.prepareStatement(this.sqlListAlumnos);
            sentenciaSQL.setInt(1, pDni);
            sentenciaSQL.setInt(2, pDni);
            sentenciaSQL.setString(3, pApellido+"%");
            sentenciaSQL.setString(4, pNombre+"%");
            //rs = sentenciaSQL.executeQuery();
            rs = st.executeQuery(sentenciaSQL.toString());
            while(rs.next()){
                Alumno al = new Alumno();
                al.setIdPersona(rs.getInt("id_persona"));
                al.setTipoDocumento(enumTiposDocumentos.dni);
                al.setNroDocumento(rs.getInt("documento"));
                al.setApellido(rs.getString("apellido"));
                al.setNombre(rs.getString("nombre"));
                al.setFechaNacimiento( Date.valueOf(rs.getString("fechanac")));
                al.setIdAlumno(rs.getInt("id_alumno"));
                al.setLegajo(rs.getInt("legajo"));
                listaAlumnos.add(al);
            }
            return listaAlumnos;
        } catch (Exception e) {
        }
        return listaAlumnos;
    }
    
    /**
     * método qeu se conecta a la base de datos y busca un alumno por clave
     * primaria
     * @param identificadorAlumno clave primaria del alumno
     * @return instancia de {@link Alumno Alumno}
     */
    @Override
    public Alumno getAlumnoPorId(int identificadorAlumno) throws ExcepcionAlumnoNoEncontrado{
        PreparedStatement sentenciaSQL = null;
        Alumno al = new Alumno();
        try {
            ResultSet rs;
            Statement st;
            st = this.conexion.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            sentenciaSQL = this.conexion.prepareStatement(this.sqlAlumnoPorId);
            sentenciaSQL.setInt(1, identificadorAlumno);
            rs = st.executeQuery(sentenciaSQL.toString());
            if(rs.next()){
                al.setIdPersona(rs.getInt("id_persona"));
                al.setTipoDocumento(enumTiposDocumentos.dni);
                al.setNroDocumento(rs.getInt("documento"));
                al.setApellido(rs.getString("apellido"));
                al.setNombre(rs.getString("nombre"));
                al.setFechaNacimiento( Date.valueOf(rs.getString("fechanac")));
                al.setIdAlumno(rs.getInt("id_alumno"));
                al.setLegajo(rs.getInt("legajo"));
            } else{
                throw new ExcepcionAlumnoNoEncontrado();
            }
        } catch(Exception ex){
            throw  new ExcepcionAlumnoNoEncontrado(ex);
        }
        return al;
    }

    @Override
    public boolean existeDocumento(int dni) {
        PreparedStatement sentenciaSQL = null;
        try {
            ResultSet rs;
            Statement st;
            st = this.conexion.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            sentenciaSQL = this.conexion.prepareStatement(this.sqlExisteDni);
            sentenciaSQL.setInt(1, dni);
            //rs = sentenciaSQL.executeQuery();
            rs = st.executeQuery(sentenciaSQL.toString());
            return rs.next();
        } catch (Exception e) {
            return false;
        }
        
    }
    
    /**
     * método que devuelve true si encutra en la base de datos el legajo 
     * del alumno
     * @param legajo legajo de un alumno a buscar en la base de datos para saber
     * si existe
     * @return true en caso de que exista el legajo cargado en la base de datos
     * false en caso contrario
     */
    @Override
    public boolean existeLegajo(int legajo) {
        PreparedStatement sentenciaSQL = null;
        try {
            ResultSet rs;
            Statement st;
            st = this.conexion.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            sentenciaSQL = this.conexion.prepareStatement(this.sqlExisteLegajo);
            sentenciaSQL.setInt(1, legajo);
            rs = st.executeQuery(sentenciaSQL.toString());
            return rs.next();
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<RptEstadoAcademico> getEstadoAcademico(int idAlumno) throws ExcepcionAlumnoNoEncontrado {
        List<RptEstadoAcademico> listaRpt = new ArrayList();
        PreparedStatement sentenciaSQL = null;
        Alumno al = this.getAlumnoPorId(idAlumno);
        try {
            ResultSet rs;
            Statement st;
            st = this.conexion.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            sentenciaSQL = this.conexion.prepareStatement(this.sqlRptEstadoAcademico);
            sentenciaSQL.setInt(1,idAlumno);
            rs = st.executeQuery(sentenciaSQL.toString());
            while(rs.next()){
                RptEstadoAcademico rpt = new RptEstadoAcademico();
                rpt.setAlumno(al);
                //----------------------
                Carrera carrera = new Carrera();
                Curso curso = new Curso();
                //-----------------------------
                //-----------------------------
                carrera.setIdentificador(rs.getInt("id_carrera"));
                carrera.setNombre(rs.getString("nombre_carrera"));
                carrera.setDescripcion(rs.getString("descripcion_carrera"));
                carrera.setFechaDesde(rs.getDate("fechadesde_carrera"));
                carrera.setFechaHasta(rs.getDate("fechahasta_carrera"));
                //------------------------------------
                if((rs.getString("id_curso"))!= null){
                    curso.setIdentificador(rs.getInt("id_curso"));
                    curso.setAnio(rs.getInt("anio"));
                    curso.setCupoMaximo(rs.getInt("cupomaximo"));
                    curso.setNombre(rs.getString("nombre_curso"));
                    curso.setDescripcion(rs.getString("descripcion_curso"));
                    curso.setCarrera(carrera);
                    rpt.setCurso(curso);
                }
                //-------------------------------
                rpt.setFechaInscripcionCarrera(rs.getDate("fechainscripcion_carrera"));
                rpt.setFechaInscripcionCurso(rs.getDate("fechainscripcion_curso"));
                rpt.setCarrera(carrera);
                listaRpt.add(rpt);
            }
        } catch(Exception ex){
            throw  new ExcepcionAlumnoNoEncontrado(ex);
        }
        return listaRpt;
    }

    @Override
    public Alumno getAlumnoPorDni(int dni) throws ExcepcionAlumnoNoEncontrado {
        PreparedStatement sentenciaSQL = null;
        Alumno al = new Alumno();
        try {
            ResultSet rs;
            Statement st;
            st = this.conexion.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            sentenciaSQL = this.conexion.prepareStatement(this.sqlAlumnoPorDni);
            sentenciaSQL.setInt(1, dni);
            rs = st.executeQuery(sentenciaSQL.toString());
            if(rs.next()){
                al.setIdPersona(rs.getInt("id_persona"));
                al.setTipoDocumento(enumTiposDocumentos.dni);
                al.setNroDocumento(rs.getInt("documento"));
                al.setApellido(rs.getString("apellido"));
                al.setNombre(rs.getString("nombre"));
                al.setFechaNacimiento( Date.valueOf(rs.getString("fechanac")));
                al.setIdAlumno(rs.getInt("id_alumno"));
                al.setLegajo(rs.getInt("legajo"));
            } else{
                throw new ExcepcionAlumnoNoEncontrado();
            }
        } catch(Exception ex){
            throw  new ExcepcionAlumnoNoEncontrado(ex);
        }
        return al;
    }

    @Override
    public Alumno insertAlumno(int dni, String apellido, String nombre, Date fechaNacimiento, int legajo) throws ExcepcionErrorConexionBD {
        PreparedStatement sentenciaInsertPersona= null;
        PreparedStatement sentenciaInsertAlumno = null;
        int nextIdAlumno;
        try {
            sentenciaInsertPersona = this.conexion.prepareStatement(this.sqlInsertPersona);
            sentenciaInsertAlumno = this.conexion.prepareStatement(this.sqlInsertAlumno);
            this.conexion.setAutoCommit(false);
            int nextidPersona = this.getNextIdPersona();
            sentenciaInsertPersona.setInt(1, nextidPersona);
            sentenciaInsertPersona.setString(2, enumTiposDocumentos.dni.toString());
            sentenciaInsertPersona.setInt(3, dni);
            sentenciaInsertPersona.setString(4, nombre);
            sentenciaInsertPersona.setString(5, apellido);
            sentenciaInsertPersona.setDate(6, fechaNacimiento);
            sentenciaInsertPersona.executeUpdate();
            //-------------------------------------
            nextIdAlumno = this.getNextIdAlumno();
            sentenciaInsertAlumno.setInt(1, nextIdAlumno);
            sentenciaInsertAlumno.setInt(2, nextidPersona);
            sentenciaInsertAlumno.setInt(3, legajo);
            sentenciaInsertAlumno.executeUpdate();
            this.conexion.commit();
            return this.getAlumnoPorId(nextIdAlumno);
        } catch (ExcepcionErrorConexionBD|SQLException|ExcepcionAlumnoNoEncontrado ex) {
            try {
                this.conexion.rollback();
                throw new ExcepcionErrorConexionBD("Error al guardar el alumno", ex);
            } catch (SQLException e) {
                throw new ExcepcionErrorConexionBD("Error al guardar el alumno", ex);
            }
        }
        
    }
    
    private int getNextIdAlumno() throws ExcepcionErrorConexionBD{
        PreparedStatement sentenciaSQL = null;
        int nextid;
        try{
            ResultSet rs;
            Statement st;
            st = this.conexion.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            sentenciaSQL = this.conexion.prepareStatement(this.sqlGetNextIdAlumno);
            rs = st.executeQuery(sentenciaSQL.toString());
            rs.next();
            nextid = rs.getInt("nextid");
        } catch(SQLException ex){
            throw new ExcepcionErrorConexionBD(ex);
        }
        return nextid;
    }
    
    private int getNextIdPersona() throws ExcepcionErrorConexionBD{
        PreparedStatement sentenciaSQL = null;
        int nextid;
        try{
            ResultSet rs;
            Statement st;
            st = this.conexion.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            sentenciaSQL = this.conexion.prepareStatement(this.sqlGetNextIdPersona);
            rs = st.executeQuery(sentenciaSQL.toString());
            rs.next();
            nextid = rs.getInt("nextid");
        } catch(SQLException ex){
            throw new ExcepcionErrorConexionBD(ex);
        }
        return nextid;
    }

    @Override
    public Alumno updateAlumno(int dni, String apellido, String nombre, Date fechaNacimiento, int legajo, int idAlumno) throws ExcepcionErrorConexionBD {
        PreparedStatement sentenciaUpdatePersona = null;
        PreparedStatement sentenciaInsertAlumno = null;
        try {
            Alumno al = getAlumnoPorId(idAlumno);
            sentenciaUpdatePersona = this.conexion.prepareStatement(this.sqlUpdatePersona);
            sentenciaInsertAlumno = this.conexion.prepareStatement(this.sqlUpdateAlumno);
            this.conexion.setAutoCommit(false);
            sentenciaUpdatePersona.setInt(1, dni);
            sentenciaUpdatePersona.setString(2, apellido);
            sentenciaUpdatePersona.setString(3, nombre);
            sentenciaUpdatePersona.setDate(4, fechaNacimiento);
            sentenciaUpdatePersona.setInt(5, al.getIdPersona());
            sentenciaUpdatePersona.executeUpdate();
            //-------------------------------------
            sentenciaInsertAlumno.setInt(1, legajo);
            sentenciaInsertAlumno.setInt(2, idAlumno);
            sentenciaInsertAlumno.executeUpdate();
            this.conexion.commit();
            return this.getAlumnoPorId(idAlumno);
        } catch (SQLException|ExcepcionAlumnoNoEncontrado ex) {
            try {
                this.conexion.rollback();
                throw new ExcepcionErrorConexionBD("Error al guardar el alumno", ex);
            } catch (SQLException e) {
                throw new ExcepcionErrorConexionBD("Error al guardar el alumno", ex);
            }
        }
    }

    @Override
    public boolean existeDocumentoUpdate(int dni, int idPersona) {
        PreparedStatement sentenciaSQL = null;
        try {
            ResultSet rs;
            Statement st;
            st = this.conexion.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            sentenciaSQL = this.conexion.prepareStatement(this.sqlExisteDni);
            sentenciaSQL.setInt(1, dni);
            sentenciaSQL.setInt(2, idPersona);
            rs = st.executeQuery(sentenciaSQL.toString());
            return rs.next();
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean existeLegajoUpdate(int legajo, int idAlumno) {
        PreparedStatement sentenciaSQL = null;
        try {
            ResultSet rs;
            Statement st;
            st = this.conexion.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            sentenciaSQL = this.conexion.prepareStatement(this.sqlExisteLegajoUpdate);
            sentenciaSQL.setInt(1, legajo);
            sentenciaSQL.setInt(2, idAlumno);
            rs = st.executeQuery(sentenciaSQL.toString());
            return rs.next();
        } catch (Exception e) {
            return false;
        }
    }


}
