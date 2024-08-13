drop table Odontologos if exists;

create table Odontologos
(
    Id int Auto_Increment PRIMARY KEY,
    NumeroMatricula INT NOT NULL,
    Nombre VARCHAR(25) NOT NULL,
    Apellido VARCHAR(50) NOT NULL
);

insert into Odontologos values(DEFAULT,12345,'juan','perez');
insert into Odontologos values(DEFAULT,12346,'ana','ronda');
insert into Odontologos values(DEFAULT,12347,'miguel','rojo');
insert into Odontologos values(DEFAULT,12348,'laura','pomelo');
insert into Odontologos values(DEFAULT,12349,'tulio','rodriguez');