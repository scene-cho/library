drop table if exists admin cascade;
drop table if exists member cascade;
drop table if exists notice cascade;
drop table if exists opinion cascade;
drop sequence if exists notice_sequence;
drop sequence if exists opinion_sequence;
create sequence notice_sequence start 1 increment 1;
create sequence opinion_sequence start 1 increment 1;
create table admin
(
    user_id  varchar(255) not null,
    email    varchar(255),
    password varchar(255),
    primary key (user_id)
);
create table member
(
    user_id  varchar(255) not null,
    email    varchar(255),
    password varchar(255),
    primary key (user_id)
);
create table notice
(
    id             int8 not null,
    content        varchar(255),
    date           varchar(255),
    title          varchar(255),
    writer_user_id varchar(255),
    primary key (id)
);
create table opinion
(
    id             int8 not null,
    content        varchar(255),
    date           varchar(255),
    title          varchar(255),
    writer_user_id varchar(255),
    primary key (id)
);
alter table if exists notice
    add constraint fk_notice_writer foreign key (writer_user_id) references admin;
alter table if exists opinion
    add constraint fk_opinion_writer foreign key (writer_user_id) references member;