insert into notice (id, title, writer, content, date)
values (nextval('notice_sequence'), 'Notice Test', 'Me', 'Content...', current_timestamp);
insert into notice (id, title, writer, content, date)
values (nextval('notice_sequence'), 'Important Notice', 'Admin', 'Text...', current_timestamp);

insert into opinion(id, title, writer, content, date)
values (nextval('opinion_sequence'), 'Opinion Test', 'Kim', 'Opinion...', current_timestamp);
insert into opinion(id, title, writer, content, date)
values (nextval('opinion_sequence'), 'Book Request', 'Lee', 'Gof...', current_timestamp);

insert into member(user_id, password)
values ('me', '123');
insert into member(user_id, password)
values ('you', '4567');

insert into admin(user_id, password)
values ('ceo', '123');
insert into admin(user_id, password)
values ('cto', '4567');
