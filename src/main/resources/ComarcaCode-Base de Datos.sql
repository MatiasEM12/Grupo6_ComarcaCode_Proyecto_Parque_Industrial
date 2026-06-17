DROP DATABASE IF EXISTS parque_industrial_2026;
CREATE DATABASE parque_industrial_2026;
USE parque_industrial_2026;

CREATE TABLE roles (
    codigo INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(30) NOT NULL UNIQUE
);

CREATE TABLE usuario (
    codigo INT PRIMARY KEY AUTO_INCREMENT,
    userName VARCHAR(50) NOT NULL UNIQUE,
    contrasena VARCHAR(50) NOT NULL,
    rol INT NOT NULL,
    gmail VARCHAR(100) NOT NULL,
    FOREIGN KEY (rol) REFERENCES roles(codigo)
);

CREATE TABLE lotes (
    id INT PRIMARY KEY AUTO_INCREMENT,
    latitud BIGINT NOT NULL,
    longitud BIGINT NOT NULL,
    altitud BIGINT NOT NULL,
    superficie DOUBLE NOT NULL,
    estado VARCHAR(30) NOT NULL,
    infraestructura VARCHAR(255) NOT NULL
);

CREATE TABLE Empresa (
    cuit VARCHAR(20) PRIMARY KEY,
    razonSocial VARCHAR(100) NOT NULL,
    contacto VARCHAR(100) NOT NULL,
    contactoRepresentante VARCHAR(100) NOT NULL,
    radicada BOOLEAN NOT NULL
);

CREATE TABLE RepresentanteEmpresa (
    DNI VARCHAR(8) PRIMARY KEY,
    userName VARCHAR(50) NOT NULL,
    cuit_empresa VARCHAR(20) NOT NULL,
    FOREIGN KEY (userName) REFERENCES usuario(userName),
    FOREIGN KEY (cuit_empresa) REFERENCES Empresa(cuit)
);

CREATE TABLE ProyectoProductivo (
    idProyecto INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100),
    descripcion TEXT,
    superficie DOUBLE,
    necesidades TEXT,
    empleabilidad INT,
    materiaPrima VARCHAR(500),
    estado VARCHAR(50),
    cuit_empresa VARCHAR(20),
    id_lote INT NULL,
    FOREIGN KEY (cuit_empresa) REFERENCES Empresa(cuit),
    FOREIGN KEY (id_lote) REFERENCES lotes(id)
);

CREATE TABLE SolicitudRadicacion (
    id INT PRIMARY KEY AUTO_INCREMENT,
    numeroTramite VARCHAR(50) NOT NULL,
    estadoSolicitud VARCHAR(50) NOT NULL,
    fechaCreacion DATE NOT NULL,
    fechaActualizacion DATE NOT NULL,
    dniRepresentante VARCHAR(8) NOT NULL,

    objeto VARCHAR(255) NOT NULL,
    nombreProyecto VARCHAR(100) NOT NULL,
    descripcionServicio TEXT NOT NULL,
    emplazamiento VARCHAR(255) NOT NULL,
    personal VARCHAR(100) NOT NULL,
    tiempoRadicacion VARCHAR(100) NOT NULL,
    m2 VARCHAR(100) NOT NULL,
    areaTrabajo VARCHAR(100) NOT NULL,
    areaDeposito VARCHAR(100) NOT NULL,
    estacionamiento VARCHAR(100),
    planos VARCHAR(100) NOT NULL,
    empleabilidad VARCHAR(100) NOT NULL,
    materiasPrimas VARCHAR(500) NOT NULL,
    destinoProduccion VARCHAR(500) NOT NULL,
    tension VARCHAR(100) NOT NULL,
    potencia VARCHAR(100) NOT NULL,
    agua VARCHAR(100) NOT NULL,
    gas VARCHAR(100) NOT NULL,
    residuos VARCHAR(255) NOT NULL,
    tratamiento VARCHAR(255) NOT NULL,
    balanza VARCHAR(100),
    comedor VARCHAR(100),
    coworking VARCHAR(100),

    idLote INT NULL,
    observacion TEXT NULL,

    FOREIGN KEY (dniRepresentante) REFERENCES RepresentanteEmpresa(DNI),
    FOREIGN KEY (idLote) REFERENCES lotes(id)
);

CREATE TABLE AdministradorDelParque (
    dni VARCHAR(8) PRIMARY KEY,
    nombre VARCHAR(100),
    userName VARCHAR(50),
    FOREIGN KEY (userName) REFERENCES usuario(userName)
);

CREATE TABLE AvanceProyecto (
    id INT PRIMARY KEY AUTO_INCREMENT,
    idProyecto INT NOT NULL,
    fechaCreacion DATE NOT NULL,
    descripcion TEXT NOT NULL,
    estado VARCHAR(50) NOT NULL,
    FOREIGN KEY (idProyecto) REFERENCES ProyectoProductivo(idProyecto)
);

CREATE TABLE Documento (
    id INT PRIMARY KEY AUTO_INCREMENT,
    tipo VARCHAR(50) NOT NULL,
    nombreArchivo VARCHAR(255) NOT NULL,
    rutaArchivo VARCHAR(500) NOT NULL,
    tamanio BIGINT NOT NULL,
    fechaCarga DATE NOT NULL
);

CREATE TABLE informe (
    id_informe INT PRIMARY KEY AUTO_INCREMENT,
    tipo VARCHAR(80) NOT NULL,
    descripcion TEXT NOT NULL,
    fecha DATE NOT NULL,
    usuario_generador INT NOT NULL,

    FOREIGN KEY (usuario_generador)
        REFERENCES usuario(codigo)
);

CREATE TABLE InformeDocumento (
    id_informe INT NOT NULL,
    id_documento INT NOT NULL,

    PRIMARY KEY(id_informe, id_documento),

    FOREIGN KEY(id_informe)
        REFERENCES Informe(id_informe),

    FOREIGN KEY(id_documento)
        REFERENCES Documento(id)
);

CREATE TABLE AvanceDocumento (
    idAvanceProyecto INT NOT NULL,
    idDocumento INT NOT NULL,
    PRIMARY KEY(idAvanceProyecto, idDocumento),
    FOREIGN KEY(idAvanceProyecto) REFERENCES AvanceProyecto(id),
    FOREIGN KEY(idDocumento) REFERENCES Documento(id)
);

CREATE TABLE SolicitudDocumento (
    idSolicitud INT NOT NULL,
    idDocumento INT NOT NULL,
    PRIMARY KEY(idSolicitud, idDocumento),
    FOREIGN KEY(idSolicitud) REFERENCES SolicitudRadicacion(id),
    FOREIGN KEY(idDocumento) REFERENCES Documento(id)
);

CREATE TABLE ProyectoDocumento (
    idProyecto INT NOT NULL,
    idDocumento INT NOT NULL,
    PRIMARY KEY(idProyecto, idDocumento),
    FOREIGN KEY(idProyecto) REFERENCES ProyectoProductivo(idProyecto),
    FOREIGN KEY(idDocumento) REFERENCES Documento(id)
);

CREATE TABLE EvaluacionTecnica (
    id INT AUTO_INCREMENT PRIMARY KEY,
    idProyecto INT NOT NULL,
    descripcion TEXT NOT NULL,
    resultado VARCHAR(50) NOT NULL,
    observaciones TEXT,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (idProyecto) REFERENCES ProyectoProductivo(idProyecto)
);

CREATE TABLE ObservacionSolicitud (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_solicitud INT NOT NULL,
    dni_administrador VARCHAR(8) NOT NULL,
    observacion VARCHAR(250) NOT NULL,
    fecha_creacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (id_solicitud)
        REFERENCES SolicitudRadicacion(id),

    FOREIGN KEY (dni_administrador)
        REFERENCES AdministradorDelParque(DNI)
);

CREATE TABLE IF NOT EXISTS organismopublico (
  saf INT NOT NULL,
  nombre VARCHAR(100) NOT NULL,
  tipoOrganismo VARCHAR(30) NOT NULL,
  userName VARCHAR(50) NOT NULL,
  PRIMARY KEY (saf),
  UNIQUE KEY (userName)
) ENGINE=InnoDB;

-- =========================
-- INSERTS
-- =========================

INSERT INTO roles (codigo, nombre)
VALUES
(1, 'administrador'),
(2, 'representante'),
(3, 'organismo_publico');

INSERT INTO usuario (userName, contrasena, rol, gmail)
VALUES
('pablo_A', '123456', 1, 'admin@gmail.com'),
('jose_R', '123456', 2, 'representante@gmail.com'),
('Fede_O', '123456', 3, 'organismo@gmail.com');

INSERT INTO AdministradorDelParque (dni, nombre, userName)
VALUES
('12345678', 'pablo perez', 'pablo_A');

INSERT INTO `organismopublico` (`saf`, `nombre`, `tipoOrganismo`, `userName`) VALUES
(0, 'Secretaría de Desarrollo Productivo', 'PROVINCIAL', 'Fede_O');

INSERT INTO Empresa (
    cuit,
    razonSocial,
    contacto,
    contactoRepresentante,
    radicada
)
VALUES (
    '20-12345678-9',
    'Empresa Demo',
    'empresa@gmail.com',
    'representante@gmail.com',
    false
);
INSERT INTO RepresentanteEmpresa (DNI, userName,cuit_empresa)
VALUES
('12345678', 'jose_R', '20-12345678-9');


INSERT INTO informe (
    tipo,
    descripcion,
    fecha,
    usuario_generador
)
VALUES (
    'CONSUMO_ELECTRICO',
    'Verificar generadores',
    '2026-06-10',
    1
);

INSERT INTO lotes (
    
    latitud,
    longitud,
    altitud,
    superficie,
    estado,
    infraestructura
)
VALUES
( 1, 2, 3, 1200.0, 'DISPONIBLE', 'Agua, luz y acceso vial'),
( 8, 6, 4, 2500.0, 'DISPONIBLE', 'Agua, luz, gas y acceso vial'),
( 4, 3, 2, 3300.0, 'DISPONIBLE', 'Agua, luz y espacio para carga pesada');

INSERT INTO `solicitudradicacion` (`id`, `numeroTramite`, `estadoSolicitud`, `fechaCreacion`, `fechaActualizacion`, `dniRepresentante`, `objeto`, `nombreProyecto`, `descripcionServicio`, `emplazamiento`, `personal`, `tiempoRadicacion`, `m2`, `areaTrabajo`, `areaDeposito`, `estacionamiento`, `planos`, `empleabilidad`, `materiasPrimas`, `destinoProduccion`, `tension`, `potencia`, `agua`, `gas`, `residuos`, `tratamiento`, `balanza`, `comedor`, `coworking`, `idLote`, `observacion`) VALUES
(1, 'SOL-1', 'PENDIENTE', '2026-06-15', '2026-06-15', '12345678', 'nuevosProductos', 'Produccion de Tornillos', 'Elaboracion de tornillos ', 'alquilado', 'produccion', '12', '1200', '700', '200', '100', 'si', '30', 'acero galvanizado de distribución nacional', 'Venta directa a metalúrgicas y constructoras regionales', 'media', '150', '8000', 'si', 'residuos metálicos y oleosos', 'si', 'no', 'si', 'no', NULL, NULL);



