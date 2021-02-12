drop table if exists tasks_domain CASCADE;
drop table if exists to_do_list_domain CASCADE;
create table tasks_domain (task_id bigint generated by default as identity, summary varchar(240), priority integer not null,deadline varchar(240), to_do__list_id bigint, status varchar(240), primary key (task_id));
create table to_do_list_domain (list_id bigint generated by default as identity, name varchar(255), primary key (list_id));
alter table tasks_domain add constraint FKdp6hsg4fffbm3xq4p4t2153ft foreign key (to_do_list_id) references to_do_list_domain on delete cascade;