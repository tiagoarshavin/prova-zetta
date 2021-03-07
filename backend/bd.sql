create table cargo
(
    id   int primary key auto_increment not null,
    nome varchar(200)
);

create table perfil
(
    id   int primary key auto_increment not null,
    nome varchar(200)
);

create table usuario
(
    id              INT auto_increment NOT NULL,
    nome            varchar(100)       NOT NULL,
    cpf             varchar(100)       NOT NULL,
    data_nascimento DATE               NULL,
    data_cadastro   DATE               NULL,
    sexo            CHAR               NULL,
    id_cargo        integer            not null,
    CONSTRAINT id_pk PRIMARY KEY (id),
    CONSTRAINT `FK_USUARIO_CARGO`
        FOREIGN KEY (`id_cargo`)
            REFERENCES cargo (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

create table usuario_perfil
(
    id INT auto_increment NOT null,
    id_usuario int not null,
    id_perfil  int not null,
    CONSTRAINT usuario_perfil_pk PRIMARY KEY (id),
    CONSTRAINT `FK_USUARIO_PERFIL_USUARIO`
        FOREIGN KEY (`id_usuario`)
            REFERENCES `usuario` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `FK_USUARIO_PERFIL_PERFIL`
        FOREIGN KEY (`id_perfil`)
            REFERENCES `perfil` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);