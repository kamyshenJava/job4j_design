create table authors(
    id serial primary key,
    name varchar(255),
    birth date,
    death date
);
create table books(
    id serial primary key,
    name varchar(255),
    date_of_writting int,
	author_id int references authors(id)
);

insert into authors(name, birth, death)
values('Pushkin', '1799-01-01', '1837-01-29');
insert into authors(name, birth, death)
values('Lermontov', '1814-10-15', '1841-07-27');
insert into books(name, date_of_writting, author_id)
values('Eugene Onegin', 1832, 1);
insert into books(name, date_of_writting, author_id)
values('Ruslan and Lyudmila', 1820, 1);
insert into books(name, date_of_writting, author_id)
values('A Hero of Our Time', 1840, 2);
insert into books(name, date_of_writting, author_id)
values('Borodino', 1837, 2);

select a.name as Имя, b.name as Книга
from authors as a join books as b on a.id = b.author_id;
select a.name, b.name, b.date_of_writting as дата_написания
from authors as a
join books as b
on a.id = b.author_id;
select a.name, a.birth, a.death, b.name, b.date_of_writting
from authors as a
join books as b
on a.id = b.author_id;