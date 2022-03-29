create table cars(
    id serial primary key,
    name text
);

create table bodies(
    id serial primary key,
    name text,
    cars_id int references cars(id)
);

create table engines(
    id serial primary key,
    name text,
    cars_id int references cars(id)
);

create table transmissions(
    id serial primary key,
    name text,
    cars_id int references cars(id)
)

insert into cars(name)
values ('Лада'), ('Москвич'), ('Вольво'), ('Мерседес');
insert into bodies(name, cars_id)
values ('седан', 1), ('хэтчбэк', 2), ('универсал', 3), ('минивэн', 3), ('кабриолет', null);
insert into engines(name, cars_id)
values ('75 л.с.', 1), ('100 л.с.', 1), ('150 л.с.', 4), ('200 л.с.', null);
insert into transmissions(name, cars_id)
values ('ручная 5', 1), ('ручная 6', 2), ('автомат 5', 3), ('автомат 6', 4), ('автомат 4', null);

select c.name, b.name, e.name, t.name from cars c
left join bodies b
on b.cars_id = c.id
left join engines e
on e.cars_id = c.id
left join transmissions t
on t.cars_id = c.id;

select b.name from bodies b
left join cars c
on b.cars_id = c.id
where c.name is null;

select e.name from engines e
left join cars c
on e.cars_id = c.id
where c.name is null;

select t.name from transmissions t
left join cars c
on t.cars_id = c.id
where c.name is null;

