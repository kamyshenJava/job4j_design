insert into rules(name) values('New rule');
insert into status(name) values('Processing');
insert into categories(name) values('Urgent');
insert into roles(name) values('User');
insert into users(name, role_id) values('Petr', 1);
insert into items(name, user_id, category_id, status_id) values('New item', 1, 1, 1);
insert into comments_(name, item_id) values('New comment', 1);
insert into attachs(name, item_id) values('New attachment', 1);
insert into rules_roles(rule_id, role_id) values(1, 1);