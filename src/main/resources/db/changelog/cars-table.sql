create table cars(
    id serial primary key not null ,
    number varchar unique not null ,
    model varchar not null ,
    producer varchar not null ,
    color varchar not null ,
    user_id int,
    foreign key (user_id) references users(id)
)