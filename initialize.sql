create database melendar;

use melendar;

drop table if exists `User`, `Group`, `UserGroup`, `Memo`, `CalendarEvent`;

CREATE TABLE `User` (
   `user_id`   bigint   NOT NULL AUTO_INCREMENT PRIMARY KEY,
   `nickname`   varchar(20)   NULL,
   `profile_image`  varchar(30) NULL
);

CREATE TABLE `Group` (
   `group_id`   bigint   NOT NULL AUTO_INCREMENT PRIMARY KEY ,
   `group_name`   varchar(30)   NULL
);

CREATE TABLE `CalendarEvent` (
   `event_id`   bigint   NOT NULL AUTO_INCREMENT PRIMARY KEY,
   `group_id`   bigint   NOT null,
   `date`   date   NOT NULL,
   `task`   text   NULL,
   foreign key (group_id) references `Group`(group_id) on delete cascade
);

CREATE TABLE `Memo` (
   `memo_id`   bigint   NOT NULL AUTO_INCREMENT PRIMARY KEY,
   `user_id`   bigint   NOT NULL  ,
   `date`   date   NULL   ,
   `content`   text   NULL,
   `title`   text   NULL   ,
   foreign key (user_id) references `User` (user_id) on delete cascade
);

CREATE TABLE `UserGroup` (
   `user_group_id`   bigint   NOT NULL ,
   `user_id`   bigint   NOT NULL AUTO_INCREMENT PRIMARY KEY,
   `group_id`   bigint   NOT NULL,
   `group_description`   text   NULL,
   `group_color`   varchar(10)   NULL,
   FOREIGN KEY (user_id) REFERENCES User(user_id) ON DELETE CASCADE,
   foreign key(group_id) references `Group`(group_id) on delete cascade
);
