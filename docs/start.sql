INSERT INTO users (email,password,name,last_name)
       VALUES ('erick@admin.com','8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918','erick','giancarlo');

INSERT INTO role_user (role_id,user_id)
       VALUES ('admin','erick@admin.com');

select * from users;
select * from role_user;