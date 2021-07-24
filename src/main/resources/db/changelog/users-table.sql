create table users(
    id serial not null primary key,
    username varchar unique not null,
    password varchar not null,
    name varchar not null,
    dob date not null,
    email varchar
)