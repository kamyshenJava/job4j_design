create table bodies(
    id serial primary key,
    name text
);

create table engines(
    id serial primary key,
    name text
);

create table transmissions(
    id serial primary key,
    name text
);

create table cars(
    id serial primary key,
    name text,
    body_id int references bodies(id),
    engine_id int references engines(id),
    transmission_id int references transmissions(id)
);

insert into bodies(name)
values ('седан'), ('хэтчбэк'), ('универсал'), ('минивэн'), ('кабриолет');
insert into engines(name)
values ('75 л.с.'), ('100 л.с.'), ('150 л.с.'), ('200 л.с.');
insert into transmissions(name)
values ('ручная 5'), ('ручная 6'), ('автомат 5'), ('автомат 6'), ('автомат 4');
insert into cars(name, body_id, engine_id, transmission_id)
values ('Лада', 1, 1, 2), ('Москвич', 2, 1, 1), ('Вольво', 3, 3, 4), ('Мерседес', 5, 4, 3);

select c.name, b.name, e.name, t.name from cars c
left join bodies b
on b.id = c.body_id
left join engines e
on e.id = c.engine_id
left join transmissions t
on t.id = c.transmission_id;

select b.name from bodies b
left join cars c
on b.id = c.body_id
where c.name is null;

select e.name from engines e
left join cars c
on e.id = c.engine_id
where c.name is null;

select t.name from transmissions t
left join cars c
on t.id = c.transmission_id
where c.name is null;

