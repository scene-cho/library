insert into member(user_id, password, email)
values ('Kim', '123', 'a@b.c');
insert into member(user_id, password, email)
values ('Lee', '4567', 'b@c.d');

insert into admin(user_id, password, email)
values ('me', '123', 'c@d.e');
insert into admin(user_id, password, email)
values ('admin', '4567', 'd@e.f');

insert into notice (id, title, writer_user_id, content, date)
values (nextval('notice_sequence'), 'Notice Test', 'me', 'Content...', current_timestamp);
insert into notice (id, title, writer_user_id, content, date)
values (nextval('notice_sequence'), 'Important Notice', 'admin', 'Text...', current_timestamp);

insert into opinion(id, title, writer_user_id, content, date)
values (nextval('opinion_sequence'), 'Opinion Test', 'Kim', 'Opinion...', current_timestamp);
insert into opinion(id, title, writer_user_id, content, date)
values (nextval('opinion_sequence'), 'Book Request', 'Lee', 'Gof...', current_timestamp);
