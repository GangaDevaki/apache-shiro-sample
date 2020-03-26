CREATE DATABASE `shiro_users`;

use `shiro_users`;

CREATE TABLE "users" (
    id bigserial primary key,
    user_name varchar(50) NOT NULL,
	email varchar(50) NOT NULL,
    password varchar(50) NOT NULL
);

CREATE TABLE "orgs" (
    id bigserial primary key,
	org_code varchar(50) NOT NULL,
    org_name varchar(50) NOT NULL,
	email varchar(50) NOT NULL
);

CREATE TABLE "roles" (
    id bigserial primary key,
	role_code varchar(50) NOT NULL,
    role_name varchar(50) NOT NULL,
	role_json json NOT NULL
);

CREATE TABLE `user_org_role` (
  `id` bigint(20) NOT NULL,
  `org_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) InnoDB DEFAULT CHARSET=utf8;

