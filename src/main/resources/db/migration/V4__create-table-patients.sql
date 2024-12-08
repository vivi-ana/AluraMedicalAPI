create table patients(
    id bigint not null auto_increment,
    name varchar(100) not null,
    email varchar(100) not null unique,
    identity_card varchar(14) not null unique,
    cellphone_number varchar(20) not null,
    street varchar(100) not null,
    district varchar(100) not null,
    complement varchar(100),
    number varchar(20),
    city varchar(100) not null,

    primary key(id)

    );