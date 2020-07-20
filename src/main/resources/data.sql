insert into member(user_id, password, email)
values ('Kim', '123', 'a@b.c');
insert into member(user_id, password, email)
values ('Lee', '4567', 'b@c.d');

insert into admin(user_id, password, email)
values ('me', '123', 'c@d.e');
insert into admin(user_id, password, email)
values ('admin', '4567', 'd@e.f');

insert into notice (id, title, writer_user_id, content, date)
values (nextval('notice_sequence'), 'Notice Test', 'me', 'Content...', '2020.1.1 01:00:00');
insert into notice (id, title, writer_user_id, content, date)
values (nextval('notice_sequence'), 'Important Notice', 'admin', 'Text...', '2020.12.31 23:59:59');

insert into opinion(id, title, writer_user_id, content, date)
values (nextval('opinion_sequence'), 'Opinion Test', 'Kim', 'Opinion...', '2020.1.1 01:00:00');
insert into opinion(id, title, writer_user_id, content, date)
values (nextval('opinion_sequence'), 'Book Request', 'Lee', 'Gof...', '2020.12.31 23:59:59');
