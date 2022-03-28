create table type(
    id serial primary key,
    name varchar(255)
    );
create table product(
    id serial primary key,
    name varchar(255),
    type_id int references type(id),
    expiration_date date,
    price float
    );

insert into type(name) values ('СЫР'), ('МОЛОКО'), ('КРУПА');
insert into product(name, type_id, expiration_date, price)
values ('Сыр плавленный', 1, '01-07-2022', 500),
('Сыр моцарелла', 1, '01-02-2022', 4000),
('Молоко 1.5%', 2, '20-03-2022', 200),
('Молоко 3%', 2, '20-05-2022', 250),
('Крупа пшеничная', 3, '15-01-2023', 150),
('Крупа манная', 3, '15-02-2023', 120.5),
('Мороженое Пломбир', 2, '07-03-2022', 180),
('Крупа гречневая', 3, '07-02-2023', 130);

select p.name from product p
join type t
on p.type_id = t.id and
t.name = 'СЫР';

select p.name from product p
join type t
on p.type_id = t.id and
p.name like '%ороженое%';

select p.name from product p
join type t
on p.type_id = t.id and
p.expiration_date < current_date;

select name, price from product
where price = (
select max(price) from product
);

select t.name, count(*) from type t
join product p
on p.type_id = t.id
group by t.name;

select count(*) from product p
join type t
on p.type_id = t.id and
(t.name = 'СЫР' or t.name = 'МОЛОКО');

select t.name from type t
join product p
on p.type_id = t.id
group by t.name
having count(*) < 10;

select p.name, t.name from product p
join type t
on p.type_id = t.id;




