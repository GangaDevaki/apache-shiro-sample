CREATE DATABASE `shiro_users`;

use `shiro_users`;

CREATE TABLE "user" (
    id bigserial primary key,
    username varchar(50) NOT NULL,
	email varchar(50) NOT NULL,
    password varchar(50) NOT NULL
);

CREATE TABLE "org" (
    id bigserial primary key,
	org_code varchar(50) NOT NULL,
    org_name varchar(50) NOT NULL,
	email varchar(50) NOT NULL
);

CREATE TABLE "role" (
    id bigserial primary key,
	role_code varchar(50) NOT NULL,
    role_name varchar(50) NOT NULL,
	role_json json NOT NULL
);