create table departments(
    id serial primary key,
    name varchar(255)
);

create table employees(
    id serial primary key,
    name varchar(255),
    department_id int references departments(id)
);

insert into departments(name) values ('marketing'), ('wholesale'), ('retail'), ('board of directors');
insert into employees(name, department_id) values ('John', 1), ('Richard', 2), ('Sean', 3),
('Liz', 2), ('Mark', 1), ('Paul', 2), ('Ann', 3), ('Joan', 1);

select * from employees e
left join departments d
on d.id = e.department_id;

select * from employees e
right join departments d
on d.id = e.department_id;

select * from employees e
full join departments d
on d.id = e.department_id;

select * from employees e
cross join departments d;

select * from departments d
left join employees e
on d.id = e.department_id where
e.department_id is null;

select e.name, d.name from employees e
left join departments d
on d.id = e.department_id;

select e.name, d.name from departments d
right join employees e
on d.id = e.department_id;

create table teens(
    id serial primary key,
    name text,
    gender varchar(16)
);

insert into teens(name, gender)
values ('John', 'M'), ('Richard', 'M'), ('Liz', 'F'), ('Ann', 'F');

select * from (select * from teens
where gender = 'M') as M
cross join (select * from teens
where gender = 'F') as F;