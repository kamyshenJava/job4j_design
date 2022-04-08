CREATE TABLE company
(
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
    id integer NOT NULL,
    name character varying,
    company_id integer references company(id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

insert into company(id, name) values(1, 'apple'), (2, 'google'), (3, 'microsoft');
insert into person(id, name, company_id) values(1, 'John', 3), (2, 'Paul', 1),
 (3, 'Ann', 1), (4, 'Jeff', 1), (5, 'Liz', 3), (6, 'Brian', 3), (7, 'Nancy', 2);

select p.name, c.name from person p
join company c
on p.company_id != 5 and
p.company_id = c.id

select c.name, count(p) from company c
join person p
on p.company_id = c.id
group by c.name
having count(p) =
(select max(count) from
(select c.name, count(p) from company c
join person p
on p.company_id = c.id
group by c.name
) as m)


