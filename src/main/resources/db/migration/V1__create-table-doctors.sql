create table doctors (

    id bigint not null auto_increment,
    name varchar(100) not null,
    email varchar(100) not null unique,
    identity_card varchar(100) not null unique,
    specialty varchar(100) not null,
    street varchar(100) not null,
    district varchar(100) not null,
    city varchar(100) not null,
    number varchar(20),
    complement varchar(100),

    primary key(id)
);