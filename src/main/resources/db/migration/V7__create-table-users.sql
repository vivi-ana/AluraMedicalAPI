create table users (

    id bigint not null auto_increment,
    user_name varchar(100) not null,
    pass varchar(300) not null unique,

    primary key(id)
);