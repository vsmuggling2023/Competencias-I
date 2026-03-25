# 🚛 Sistema de Gestión de Camiones

Sistema orientado a la gestión de camiones, conductores y control de kilometraje, incorporando alertas de mantenimiento y administración de servicios realizados.

---

## 📌 Descripción

El sistema permite a los conductores registrar el kilometraje recorrido por cada camión al finalizar un trayecto, asegurando el almacenamiento adecuado de esta información para su posterior consulta y control. Además, es capaz de gestionar la información tanto de los camiones como de los conductores, registrando datos relevantes como marca, modelo, año y patente de los vehículos, junto con los usuarios del sistema que cumplen el rol de conductores, permitiendo también la asignación de estos a un camión específico.

Por otra parte, el sistema genera una alerta automática cuando un camión alcanza o supera los 5000 kilómetros recorridos, indicando la necesidad de realizar un mantenimiento preventivo. En este contexto, también permite la gestión completa de los mantenimientos, facilitando el registro, consulta, actualización y eliminación de los mismos, manteniendo un historial asociado a cada camión.

Finalmente, el sistema contempla la ejecución de pruebas unitarias sobre los distintos módulos desarrollados, con el objetivo de validar que cada componente funcione correctamente de manera independiente y asegurar la calidad del software.

---

## ⚙️ Configuración de Base de Datos

Para el correcto funcionamiento del sistema, se debe configurar la conexión a la base de datos con los siguientes parámetros:

- **Base de datos:** hirata  
- **Usuario:** root  
- **Contraseña:** root2026  
- **Host:** localhost  

---

## 🧠 Modelo de Datos

El sistema está compuesto por las siguientes entidades:

- `usuarios`: almacena la información de los conductores
- `camiones`: contiene los datos de los vehículos
- `asignaciones`: relaciona conductores con camiones
- `kilometraje`: registra los recorridos realizados
- `mantenimientos`: guarda el historial de servicios

---

## 💾 Base de Datos (MySQL)

```sql
CREATE DATABASE gestion_camiones;
USE gestion_camiones;

-- TABLA USUARIOS
CREATE TABLE usuarios (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    tipo_usuario ENUM('ADMIN', 'CONDUCTOR') DEFAULT 'CONDUCTOR',
    estado BOOLEAN DEFAULT TRUE,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- TABLA CAMIONES
CREATE TABLE camiones (
    id_camion INT AUTO_INCREMENT PRIMARY KEY,
    patente VARCHAR(20) UNIQUE NOT NULL,
    marca VARCHAR(50) NOT NULL,
    modelo VARCHAR(50) NOT NULL,
    anio INT NOT NULL,
    kilometraje_acumulado DECIMAL(10,2) DEFAULT 0,
    estado BOOLEAN DEFAULT TRUE
);

-- TABLA ASIGNACIONES
CREATE TABLE asignaciones (
    id_asignacion INT AUTO_INCREMENT PRIMARY KEY,
    id_camion INT NOT NULL,
    id_usuario INT NOT NULL,
    fecha_asignacion DATE NOT NULL,
    FOREIGN KEY (id_camion) REFERENCES camiones(id_camion),
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario)
);

-- TABLA KILOMETRAJE
CREATE TABLE kilometraje (
    id_kilometraje INT AUTO_INCREMENT PRIMARY KEY,
    id_camion INT NOT NULL,
    id_usuario INT NOT NULL,
    kilometros_recorridos DECIMAL(10,2) NOT NULL,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_camion) REFERENCES camiones(id_camion),
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario)
);

-- TABLA MANTENIMIENTOS
CREATE TABLE mantenimientos (
    id_mantenimiento INT AUTO_INCREMENT PRIMARY KEY,
    id_camion INT NOT NULL,
    descripcion TEXT NOT NULL,
    fecha_mantenimiento DATE NOT NULL,
    costo DECIMAL(10,2),
    FOREIGN KEY (id_camion) REFERENCES camiones(id_camion)
);
