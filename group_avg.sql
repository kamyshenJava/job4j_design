insert into devices(name, price)
values ('smartphone', 800.5), ('tea-kettle', 20.2), ('razor', 10.5);
insert into people(name) values ('Ivan'), ('John'), ('Liz');
insert into devices_people(people_id, device_id) values (1, 3), (2, 1), (2, 2), (2, 3), (3,1), (3, 2);

select avg(price) from devices;

select p.name, avg(d.price)
from people as p
join devices_people as dp
on dp.people_id = p.id
join devices as d
on dp.device_id = d.id
group by p.name
having avg(d.price) > 5000;
