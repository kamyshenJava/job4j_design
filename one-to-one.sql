create table ITIN(
    id serial primary key, 
    name varchar(255),
);
create table people(
    id serial primary key, 
    name varchar(255),
	ITIN_id int references ITIN(id) unique
);