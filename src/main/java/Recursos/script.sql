CREATE TABLE Usuario(
Contraseña INTEGER UNIQUE NOT NULL,
Nombre varchar(40) NOT NULL,
Apellido VARCHAR(40)NOT NULL,
Cedula int NOT NULL,
Correo VARCHAR(40)NOT NULL,
primary key(Contraseña));

CREATE TABLE Articulo(
Codigo INTEGER UNIQUE NOT NULL,
Nombre varchar(20) not null,
Precio VARCHAR(40) NOT NULL,
Fecha TIMESTAMP NOT NULL,
Cantidad FLOAT NOT NULL,
Descripcion varchar(30) not null,
primary key(Codigo));
