--
-- Sample Data
--

-- Roles
insert into role(name) values ('ADMIN');
insert into role(name) values ('PRODUCER');
insert into role(name) values ('CONSUMER');
insert into role(name) values ('TRANSPORTER');

-- Users
insert into user(login, password, first_name, last_name, email, mobile) values ('user.admin','p2m1S1YJIUrqsuC+nqhH1Q==','User','ADMIN','user.admin@c2p.fr','06.63.66.66.66');
insert into user(login, password, first_name, last_name, email, mobile) values ('user.producer','p2m1S1YJIUrqsuC+nqhH1Q==','User','PRODUCER','user.producer@c2p.fr','06.63.66.66.66');
insert into user(login, password, first_name, last_name, email, mobile) values ('user.consumer','p2m1S1YJIUrqsuC+nqhH1Q==','User','CONSUMER','user.consumer@c2p.fr','06.63.66.66.66');
insert into user(login, password, first_name, last_name, email, mobile) values ('user.transporter','p2m1S1YJIUrqsuC+nqhH1Q==','User','TRANSPORTER','user.transporter@c2p.fr','06.63.66.66.66');

-- users roles
insert into user_role (user_id,role_id) values (1,1);
insert into user_role (user_id,role_id) values (2,2);
insert into user_role (user_id,role_id) values (3,3);
insert into user_role (user_id,role_id) values (4,4);







