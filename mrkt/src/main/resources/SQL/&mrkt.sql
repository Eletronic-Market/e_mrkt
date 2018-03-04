/**
 * Author:  Lenno Sousa
 * Created: 04/03/2018
 */

create extension pgcrypto;

/*Table Usuario*/
create table usuario(
    id_usuario serial NOT NULL,
    email character varying(100) NOT NULL,
    senha character varying(100) NOT NULL,
    tipo_usuario integer NOT NULL,
    status integer NOT NULL,

    CONSTRAINT usuario_pkey PRIMARY KEY (id_usuario),
    CONSTRAINT usuario_email_key UNIQUE (email)
);

/*Table Endereço*/
create table endereco(
    id_endereco serial NOT NULL,
    cep character varying(10) NOT NULL,
    rua character varying(50) NOT NULL,
    numero character varying(20) NOT NULL,
    bairro character varying(50) NOT NULL,
    cidade character varying(50) NOT NULL,
    uf character varying(2) NOT NULL,
    apelido varchar (30),

    CONSTRAINT endereco_pkey PRIMARY KEY (id_endereco)
);

/*Table Documento Pessoa Física*/
create table documento_pf(
    id_documento_pf serial NOT NULL,
    cpf character varying(15) NOT NULL,

    CONSTRAINT documento_pf_pkey PRIMARY KEY (id_documento_pf)
);

/*Table Administrador*/
create table administrador(
    id_administrador serial NOT NULL,
    nome character varying(20) NOT NULL,
    sobrenome character varying(50) NOT NULL,
    usuario integer,

    CONSTRAINT administrador_pkey PRIMARY KEY (id_administrador),
    CONSTRAINT administrador_fkey FOREIGN KEY (usuario) REFERENCES usuario (id_usuario)
);

/*Table Consumidor*/
create table consumidor(
    id_cliente serial NOT NULL,
    nome character varying(20) NOT NULL,
    sobrenome character varying(50) NOT NULL,
    usuario integer,
    endereco integer,
    documento_pf integer,

    CONSTRAINT cliente_pkey PRIMARY KEY (id_cliente),
    CONSTRAINT cliente_usuario_fkey FOREIGN KEY (usuario) REFERENCES usuario (id_usuario),
    CONSTRAINT cliente_endereco_fkey FOREIGN KEY (endereco) REFERENCES endereco (id_endereco),
    CONSTRAINT cliente_documento_fkey FOREIGN KEY (documento_pf) REFERENCES documento_pf (id_documento_pf)
);

/*Table Supermercado*/
create table supermercado(
    id_supermercado serial NOT (NULL,
    cnpj character varying(50) NOT NULL,
    razao_social character varying(50) NOT NULL,
    telefone character varying (20) NOT NULL,
    usuario integer,
    endereco integer,

    CONSTRAINT supermercado_pkey PRIMARY KEY (id_supermercado),
    CONSTRAINT supermercado_usuario_fkey FOREIGN KEY (usuario) REFERENCES usuario (id_usuario),
    CONSTRAINT supermercado_endereco_fkey FOREIGN KEY (endereco) REFERENCES endereco (id_endereco) 
);

create table registro_supermercado(
    id_registro serial NOT NULL,
    razao_social character varying(50) NOT NULL,
    cnpj character varying(50) NOT NULL,
    telefone character varying (20) NOT NULL,
    email character varying(100) NOT NULL,
    notificacao_num_registros integer NOT NULL,
    situacao_supermercado character(20) NOT NULL,

    CONSTRAINT registro_pkey PRIMARY KEY (id_registro)
);

/*Table Transportadora*/
create table transportadora(
    id_transportadora serial NOT NULL,
);

/*Table Receitas*/
create table receita(
    id_receita serial NOT NULL,
);



