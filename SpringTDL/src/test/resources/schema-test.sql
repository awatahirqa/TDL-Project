 drop table if exists tasks_domain CASCADE ;
 drop table if exists to_do_list_domain CASCADE; 
 create table tasks_domain (task_id bigint auto_increment, deadline varchar(255), priority integer not null, status varchar(255), summary varchar(255), my_list_list_id bigint, primary key (task_id));
 create table to_do_list_domain (list_id bigint auto_increment, name varchar(255), primary key (list_id));
 alter table tasks_domain add constraint FKnlqn8pbkeafjn4ufpqfl86anx foreign key (my_list_list_id) references to_do_list_domain on delete cascade;
