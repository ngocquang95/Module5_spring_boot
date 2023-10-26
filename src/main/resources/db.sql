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