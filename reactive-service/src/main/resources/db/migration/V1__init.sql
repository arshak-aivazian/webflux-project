create table products (
    id bigserial primary key,
    name varchar(255)
);

insert into products (id, name) values (1, 'test1');
insert into products (id, name) values (2, 'test2');
insert into products (id, name) values (3, 'test3');