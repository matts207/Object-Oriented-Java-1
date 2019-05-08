DROP DATABASE IF EXISTS projectdb;
create database projectdb;
use projectDB;
create user 'projectuser'@'localhost' identified by 'projectuser';
grant select, insert, update, delete, create, create view, drop, execute, references on projectdb.* to 'projectuser'@'localhost';
