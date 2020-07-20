drop table if exists notice cascade;
drop table if exists opinion cascade;
drop sequence if exists notice_sequence;
drop sequence if exists opinion_sequence;
create sequence notice_sequence start 1 increment 1;
create sequence opinion_sequence start 1 increment 1;
create table notice
(
    id      int8 not null,
    content varchar(255),
    date    timestamp,
    title   varchar(255),
    writer  varchar(255),
    primary key (id)
);
create table opinion
(
    id      int8 not null,
    content varchar(255),
    date    timestamp,
    title   varchar(255),
    writer  varchar(255),
    primary key (id)
);