create table computers(
    id serial primary key,
    name varchar(255),
    manufacturer text,
    isLaptop boolean
);

insert into computers(name, manufacturer, isLaptop) values('Lenovo IdeaPad', 'Asus', true);
select * from computers;

update computers set isLaptop = false;
select * from computers;

delete from computers;
