create table rules(
    id serial primary key, 
    name text
);

create table status(
    id serial primary key, 
    name text
);

create table categories(
    id serial primary key, 
    name text
);

create table roles(
    id serial primary key, 
    name text
);

create table users(
    id serial primary key, 
    name text,
	role_id int references roles(id)
);

create table items(
    id serial primary key, 
    name text,
	user_id int references users(id),
	category_id int references categories(id),
	status_id int references status(id)
);

create table comments_(
    id serial primary key, 
    name text,
	item_id int references items(id)
);

create table attachs(
    id serial primary key, 
    name text,
	item_id int references items(id)
);

create table rules_roles(
    id serial primary key, 
    rule_id int references rules(id),
	role_id int references roles(id)
);