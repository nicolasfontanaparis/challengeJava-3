

CREATE TABLE alumno (
    identificador integer NOT NULL,
    idpersona integer,
    legajo integer NOT NULL
);


ALTER TABLE alumno OWNER TO postgres;

--
-- TOC entry 150 (class 1259 OID 16857)
-- Name: cargo_docente; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE cargo_docente (
    identificador integer NOT NULL,
    descripcion character varying NOT NULL
);


ALTER TABLE cargo_docente OWNER TO postgres;

--
-- TOC entry 142 (class 1259 OID 16741)
-- Name: carrera; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE carrera (
    identificador integer NOT NULL,
    nombre character varying(40) NOT NULL,
    descripcion character varying(250),
    fechadesde date NOT NULL,
    fechahasta date
);


ALTER TABLE carrera OWNER TO postgres;

--
-- TOC entry 143 (class 1259 OID 16746)
-- Name: curso; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE curso (
    identificador integer NOT NULL,
    idcarrera integer,
    nombre character varying(40) NOT NULL,
    descripcion character varying(250),
    cupomaximo smallint NOT NULL,
    anio smallint NOT NULL
);


ALTER TABLE curso OWNER TO postgres;

--
-- TOC entry 149 (class 1259 OID 16844)
-- Name: curso_docente; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE curso_docente (
    idcurso integer NOT NULL,
    iddocente integer NOT NULL,
    idcargodocente integer NOT NULL
);


ALTER TABLE curso_docente OWNER TO postgres;

--
-- TOC entry 148 (class 1259 OID 16834)
-- Name: docente; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE docente (
    identificador integer NOT NULL,
    idpersona integer NOT NULL
);


ALTER TABLE docente OWNER TO postgres;

--
-- TOC entry 147 (class 1259 OID 16814)
-- Name: domicilios_alumno; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE domicilios_alumno (
    identificador integer NOT NULL,
    idalumno integer,
    idtipodomicilio integer,
    docmicilio character varying(200) NOT NULL
);


ALTER TABLE domicilios_alumno OWNER TO postgres;

--
-- TOC entry 144 (class 1259 OID 16756)
-- Name: inscripciones_carrera; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE inscripciones_carrera (
    idalumno integer NOT NULL,
    idcarrera integer NOT NULL,
    fechainscripcion date NOT NULL
);


ALTER TABLE inscripciones_carrera OWNER TO postgres;

--
-- TOC entry 145 (class 1259 OID 16769)
-- Name: inscripciones_curso; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE inscripciones_curso (
    idalumno integer NOT NULL,
    idcurso integer NOT NULL,
    fechainscripcion date NOT NULL
);


ALTER TABLE inscripciones_curso OWNER TO postgres;

--
-- TOC entry 140 (class 1259 OID 16724)
-- Name: persona; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE persona (
    identificador integer NOT NULL,
    tipodoc character(5) NOT NULL,
    documento bigint NOT NULL,
    nombre character varying(40) NOT NULL,
    apellido character varying(40) NOT NULL,
    fechanac date NOT NULL
);


ALTER TABLE persona OWNER TO postgres;

--
-- TOC entry 146 (class 1259 OID 16806)
-- Name: tipo_domicilio; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tipo_domicilio (
    identificador integer NOT NULL,
    descripcion character varying NOT NULL
);


ALTER TABLE tipo_domicilio OWNER TO postgres;

--
-- TOC entry 1843 (class 0 OID 16729)
-- Dependencies: 141
-- Data for Name: alumno; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO alumno (identificador, idpersona, legajo) VALUES (1, 3, 98734);
INSERT INTO alumno (identificador, idpersona, legajo) VALUES (2, 4, 9213);
INSERT INTO alumno (identificador, idpersona, legajo) VALUES (3, 1, 35839);
INSERT INTO alumno (identificador, idpersona, legajo) VALUES (6, 6, 123);
INSERT INTO alumno (identificador, idpersona, legajo) VALUES (7, 7, 123);
INSERT INTO alumno (identificador, idpersona, legajo) VALUES (8, 8, 1234);
INSERT INTO alumno (identificador, idpersona, legajo) VALUES (10, 10, 34);
INSERT INTO alumno (identificador, idpersona, legajo) VALUES (9, 9, 11114);
INSERT INTO alumno (identificador, idpersona, legajo) VALUES (5, 2, 11009);
INSERT INTO alumno (identificador, idpersona, legajo) VALUES (4, 5, 36299);


--
-- TOC entry 1852 (class 0 OID 16857)
-- Dependencies: 150
-- Data for Name: cargo_docente; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO cargo_docente (identificador, descripcion) VALUES (1, 'Titular');
INSERT INTO cargo_docente (identificador, descripcion) VALUES (2, 'Jefe de Trabajos Prácticos');
INSERT INTO cargo_docente (identificador, descripcion) VALUES (3, 'Ayudante');


--
-- TOC entry 1844 (class 0 OID 16741)
-- Dependencies: 142
-- Data for Name: carrera; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO carrera (identificador, nombre, descripcion, fechadesde, fechahasta) VALUES (1, 'Ingenieria en sistema de información', 'Carrera a fines a programación y desarrollo de software en general', '1960-01-01', NULL);
INSERT INTO carrera (identificador, nombre, descripcion, fechadesde, fechahasta) VALUES (2, 'Ingenieria civil', 'Carrera a fines a construcción, planificación y desarrollo de obras de desarrollo urbano', '1980-01-01', NULL);


--
-- TOC entry 1845 (class 0 OID 16746)
-- Dependencies: 143
-- Data for Name: curso; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO curso (identificador, idcarrera, nombre, descripcion, cupomaximo, anio) VALUES (1, 1, 'Análisis matemático', 'Curso sobre el desarrollo avanzado de matemáticas', 5, 2018);
INSERT INTO curso (identificador, idcarrera, nombre, descripcion, cupomaximo, anio) VALUES (2, 1, 'Diseño de sistemas', 'Curso sobre diseño de componentes de sistemas de software', 3, 2018);
INSERT INTO curso (identificador, idcarrera, nombre, descripcion, cupomaximo, anio) VALUES (3, 1, 'Java', 'Curso sobre el lenguaje de programación JAVA', 4, 2018);
INSERT INTO curso (identificador, idcarrera, nombre, descripcion, cupomaximo, anio) VALUES (4, 1, 'Base de datos-SQL', 'Curso sobre tipos de base de datos y consultas sql', 10, 2018);
INSERT INTO curso (identificador, idcarrera, nombre, descripcion, cupomaximo, anio) VALUES (5, 2, 'Fisica básica', 'Curso sobre fundamentos base de física', 5, 2018);
INSERT INTO curso (identificador, idcarrera, nombre, descripcion, cupomaximo, anio) VALUES (6, 2, 'Dibujo', 'Curso sobre dibujo de planos', 10, 2018);


--
-- TOC entry 1851 (class 0 OID 16844)
-- Dependencies: 149
-- Data for Name: curso_docente; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 1850 (class 0 OID 16834)
-- Dependencies: 148
-- Data for Name: docente; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO docente (identificador, idpersona) VALUES (1, 11);
INSERT INTO docente (identificador, idpersona) VALUES (2, 12);


--
-- TOC entry 1849 (class 0 OID 16814)
-- Dependencies: 147
-- Data for Name: domicilios_alumno; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 1846 (class 0 OID 16756)
-- Dependencies: 144
-- Data for Name: inscripciones_carrera; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO inscripciones_carrera (idalumno, idcarrera, fechainscripcion) VALUES (1, 1, '2000-01-01');
INSERT INTO inscripciones_carrera (idalumno, idcarrera, fechainscripcion) VALUES (2, 1, '2003-01-01');
INSERT INTO inscripciones_carrera (idalumno, idcarrera, fechainscripcion) VALUES (3, 1, '2004-01-01');
INSERT INTO inscripciones_carrera (idalumno, idcarrera, fechainscripcion) VALUES (4, 1, '2001-01-01');
INSERT INTO inscripciones_carrera (idalumno, idcarrera, fechainscripcion) VALUES (5, 2, '2000-01-01');
INSERT INTO inscripciones_carrera (idalumno, idcarrera, fechainscripcion) VALUES (4, 2, '2000-01-01');


--
-- TOC entry 1847 (class 0 OID 16769)
-- Dependencies: 145
-- Data for Name: inscripciones_curso; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO inscripciones_curso (idalumno, idcurso, fechainscripcion) VALUES (1, 1, '2002-01-01');
INSERT INTO inscripciones_curso (idalumno, idcurso, fechainscripcion) VALUES (1, 2, '2006-01-01');
INSERT INTO inscripciones_curso (idalumno, idcurso, fechainscripcion) VALUES (1, 3, '2002-01-01');
INSERT INTO inscripciones_curso (idalumno, idcurso, fechainscripcion) VALUES (2, 1, '2004-01-01');
INSERT INTO inscripciones_curso (idalumno, idcurso, fechainscripcion) VALUES (2, 3, '2002-01-01');
INSERT INTO inscripciones_curso (idalumno, idcurso, fechainscripcion) VALUES (2, 4, '2004-01-01');
INSERT INTO inscripciones_curso (idalumno, idcurso, fechainscripcion) VALUES (3, 1, '2010-01-01');
INSERT INTO inscripciones_curso (idalumno, idcurso, fechainscripcion) VALUES (3, 3, '2010-01-01');
INSERT INTO inscripciones_curso (idalumno, idcurso, fechainscripcion) VALUES (4, 1, '2010-01-01');
INSERT INTO inscripciones_curso (idalumno, idcurso, fechainscripcion) VALUES (4, 3, '2010-01-01');
INSERT INTO inscripciones_curso (idalumno, idcurso, fechainscripcion) VALUES (5, 3, '2010-01-01');
INSERT INTO inscripciones_curso (idalumno, idcurso, fechainscripcion) VALUES (4, 3, '2010-01-01');
INSERT INTO inscripciones_curso (idalumno, idcurso, fechainscripcion) VALUES (5, 4, '2011-01-01');
INSERT INTO inscripciones_curso (idalumno, idcurso, fechainscripcion) VALUES (4, 4, '2011-01-01');
INSERT INTO inscripciones_curso (idalumno, idcurso, fechainscripcion) VALUES (2, 5, '2011-01-01');
INSERT INTO inscripciones_curso (idalumno, idcurso, fechainscripcion) VALUES (2, 6, '2011-01-01');


--
-- TOC entry 1842 (class 0 OID 16724)
-- Dependencies: 140
-- Data for Name: persona; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO persona (identificador, tipodoc, documento, nombre, apellido, fechanac) VALUES (1, 'DNI  ', 31565839, 'Florencia', 'Maneiro', '1985-06-28');
INSERT INTO persona (identificador, tipodoc, documento, nombre, apellido, fechanac) VALUES (3, 'DNI  ', 20945422, 'Diego', 'Menendez', '1982-04-10');
INSERT INTO persona (identificador, tipodoc, documento, nombre, apellido, fechanac) VALUES (4, 'DNI  ', 30999281, 'Franzo', 'Perez', '1986-02-05');
INSERT INTO persona (identificador, tipodoc, documento, nombre, apellido, fechanac) VALUES (6, 'dni  ', 32296321, 'Fontana', 'Fontana', '1986-06-30');
INSERT INTO persona (identificador, tipodoc, documento, nombre, apellido, fechanac) VALUES (7, 'dni  ', 3296321, 'Fontana', 'Fontana', '1986-01-01');
INSERT INTO persona (identificador, tipodoc, documento, nombre, apellido, fechanac) VALUES (8, 'dni  ', 32296311, 'a', 'a', '1900-01-01');
INSERT INTO persona (identificador, tipodoc, documento, nombre, apellido, fechanac) VALUES (10, 'dni  ', 20945, 'adf', 'adf', '1800-01-01');
INSERT INTO persona (identificador, tipodoc, documento, nombre, apellido, fechanac) VALUES (9, 'dni  ', 1234, '124', '124', '1900-01-04');
INSERT INTO persona (identificador, tipodoc, documento, nombre, apellido, fechanac) VALUES (2, 'DNI  ', 31000123, 'Patricia', 'Brumatti', '1985-01-13');
INSERT INTO persona (identificador, tipodoc, documento, nombre, apellido, fechanac) VALUES (5, 'DNI  ', 24112872, 'Leandro', 'Garcia', '1988-01-03');
INSERT INTO persona (identificador, tipodoc, documento, nombre, apellido, fechanac) VALUES (12, 'dni  ', 12345678, 'María', 'González', '1981-12-12');
INSERT INTO persona (identificador, tipodoc, documento, nombre, apellido, fechanac) VALUES (11, 'dni  ', 12344445, 'Juan', 'Perez', '1980-01-01');


--
-- TOC entry 1848 (class 0 OID 16806)
-- Dependencies: 146
-- Data for Name: tipo_domicilio; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO tipo_domicilio (identificador, descripcion) VALUES (1, 'legal');
INSERT INTO tipo_domicilio (identificador, descripcion) VALUES (2, 'reall');
INSERT INTO tipo_domicilio (identificador, descripcion) VALUES (3, 'postal');


--
-- TOC entry 1729 (class 2606 OID 16735)
-- Name: alumno_idpersona_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY alumno
    ADD CONSTRAINT alumno_idpersona_key UNIQUE (idpersona);


--
-- TOC entry 1731 (class 2606 OID 16733)
-- Name: alumno_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY alumno
    ADD CONSTRAINT alumno_pkey PRIMARY KEY (identificador);


--
-- TOC entry 1743 (class 2606 OID 16864)
-- Name: cargo_docente_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY cargo_docente
    ADD CONSTRAINT cargo_docente_pk PRIMARY KEY (identificador);


--
-- TOC entry 1733 (class 2606 OID 16745)
-- Name: carrera_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY carrera
    ADD CONSTRAINT carrera_pkey PRIMARY KEY (identificador);


--
-- TOC entry 1735 (class 2606 OID 16750)
-- Name: curso_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY curso
    ADD CONSTRAINT curso_pkey PRIMARY KEY (identificador);


--
-- TOC entry 1741 (class 2606 OID 16838)
-- Name: docente_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY docente
    ADD CONSTRAINT docente_pkey PRIMARY KEY (identificador);


--
-- TOC entry 1739 (class 2606 OID 16818)
-- Name: domicilios_alumno_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY domicilios_alumno
    ADD CONSTRAINT domicilios_alumno_pkey PRIMARY KEY (identificador);


--
-- TOC entry 1727 (class 2606 OID 16728)
-- Name: persona_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY persona
    ADD CONSTRAINT persona_pkey PRIMARY KEY (identificador);


--
-- TOC entry 1737 (class 2606 OID 16813)
-- Name: tipo_domicilio_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tipo_domicilio
    ADD CONSTRAINT tipo_domicilio_pkey PRIMARY KEY (identificador);


--
-- TOC entry 1744 (class 2606 OID 16736)
-- Name: alumno_idpersona_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY alumno
    ADD CONSTRAINT alumno_idpersona_fkey FOREIGN KEY (idpersona) REFERENCES persona(identificador);


--
-- TOC entry 1755 (class 2606 OID 16865)
-- Name: curso_docente_idcargodocente_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY curso_docente
    ADD CONSTRAINT curso_docente_idcargodocente_fk FOREIGN KEY (idcargodocente) REFERENCES cargo_docente(identificador);


--
-- TOC entry 1753 (class 2606 OID 16847)
-- Name: curso_docente_idcurso; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY curso_docente
    ADD CONSTRAINT curso_docente_idcurso FOREIGN KEY (idcurso) REFERENCES curso(identificador);


--
-- TOC entry 1754 (class 2606 OID 16852)
-- Name: curso_docente_iddocente; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY curso_docente
    ADD CONSTRAINT curso_docente_iddocente FOREIGN KEY (iddocente) REFERENCES docente(identificador);


--
-- TOC entry 1745 (class 2606 OID 16751)
-- Name: curso_idcarrera_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY curso
    ADD CONSTRAINT curso_idcarrera_fkey FOREIGN KEY (idcarrera) REFERENCES carrera(identificador);


--
-- TOC entry 1752 (class 2606 OID 16839)
-- Name: docente_idpersona; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY docente
    ADD CONSTRAINT docente_idpersona FOREIGN KEY (idpersona) REFERENCES persona(identificador);


--
-- TOC entry 1750 (class 2606 OID 16819)
-- Name: domicilios_alumno_idalumno_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY domicilios_alumno
    ADD CONSTRAINT domicilios_alumno_idalumno_fkey FOREIGN KEY (idalumno) REFERENCES alumno(identificador);


--
-- TOC entry 1751 (class 2606 OID 16824)
-- Name: domicilios_alumno_idtipodomicilio_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY domicilios_alumno
    ADD CONSTRAINT domicilios_alumno_idtipodomicilio_fkey FOREIGN KEY (idtipodomicilio) REFERENCES tipo_domicilio(identificador);


--
-- TOC entry 1746 (class 2606 OID 16759)
-- Name: inscripciones_carrera_idalumno_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY inscripciones_carrera
    ADD CONSTRAINT inscripciones_carrera_idalumno_fkey FOREIGN KEY (idalumno) REFERENCES alumno(identificador);


--
-- TOC entry 1747 (class 2606 OID 16764)
-- Name: inscripciones_carrera_idcarrera_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY inscripciones_carrera
    ADD CONSTRAINT inscripciones_carrera_idcarrera_fkey FOREIGN KEY (idcarrera) REFERENCES carrera(identificador);


--
-- TOC entry 1748 (class 2606 OID 16772)
-- Name: inscripciones_curso_idalumno_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY inscripciones_curso
    ADD CONSTRAINT inscripciones_curso_idalumno_fkey FOREIGN KEY (idalumno) REFERENCES alumno(identificador);


--
-- TOC entry 1749 (class 2606 OID 16777)
-- Name: inscripciones_curso_idcurso_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY inscripciones_curso
    ADD CONSTRAINT inscripciones_curso_idcurso_fkey FOREIGN KEY (idcurso) REFERENCES curso(identificador);

--

