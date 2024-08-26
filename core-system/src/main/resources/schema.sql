create SCHEMA core_system;

USE core_system;

show tables;

SELECT * FROM users;
SELECT * FROM accounts;
SELECT * FROM user_activity;
SELECT * FROM transactions;


delete from users
    where user_id='nerd012456'
;

delete from accounts
    where user_id ='nerd012456'
;


