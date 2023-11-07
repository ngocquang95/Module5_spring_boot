create database student_manager;

use student_manager;

create table clazz
(
    id   int primary key auto_increment,
    name varchar(50) unique
);

create table student
(
    id    int primary key auto_increment,
    name  varchar(50),
    score double
);

alter table student add clazz_id int references clazz(id);

create table roles
(
    id   int auto_increment
        primary key,
    name varchar(255) not null
);

create table users
(
    id       int auto_increment
        primary key,
    name     varchar(255) not null,
    username varchar(255) not null unique,
    email    varchar(255) not null unique,
    password varchar(255) not null
);

create table users_roles
(
    user_id int not null,
    role_id int not null,
    constraint FK859n2jvi8ivhui0rl0esws6o
        foreign key (user_id) references users (id),
    constraint FKa68196081fvovjhkek5m97n3y
        foreign key (role_id) references users (id)
);

insert into roles (id, name)
values (1, 'ROLE_USER');
insert into roles (id, name)
values (2, 'ROLE_ADMIN');

insert into users (id, name, username, email, password)
values (1, 'Nguyễn Văn An', 'AnNV' , 'AnNV@gmail.com' , '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu');

insert into  users (id, name, username, email, password)
values (2, 'Nguyễn Văn Toàn', 'ToanNV' , 'ToanNV@gmail.com' , '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu');

insert into users_roles (user_id, role_id)
values (1, 1);
insert into users_roles (user_id, role_id)
values (2, 1);

insert into users_roles (user_id, role_id)
values (1, 1);
insert into users_roles (user_id, role_id)
values (2, 1);
insert into users_roles (user_id, role_id)
values (2, 2);



insert into clazz (name) values ('N0623C1');
insert into clazz (name) values ('N0723C1');
insert into clazz (name) values ('N0823C1');

insert into student (name, score) values ('Nguyễn Văn A', 9.6);
insert into student (name, score) values ('Nguyễn Văn B', 9.0);
insert into student (name, score) values ('Nguyễn Văn C', 5.6);



select id, name, score from student where id = ?;
insert into student (name, score) VALUES (?, ?);


select s.id, s.name as name, s.score, c.name as 'clazz_name' from student s inner join clazz c on s.clazz_id = c.id
where s.name like concat('%', ? , '%') and s.clazz_id = ?;


select s.id, s.name, s.score, s.clazz_id FROM Student s where (:name = '' or :name is null or s.name like concat('%', :name, '%'))
             and (:fromScore = '' or :fromScore is null or s.score >= :fromScore)
             and (:toScore = '' or :toScore is null or s.score <= :toScore)
             and (:clazzId = '' or :clazzId is null or s.clazz_id = :clazzId)