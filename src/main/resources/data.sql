insert into notice (id, title, writer, content, date)
values (nextval('notice_sequence'), 'Notice Test', 'Me', 'Content...', current_timestamp);
insert into notice (id, title, writer, content, date)
values (nextval('notice_sequence'), 'Important Notice', 'Admin', 'Text...', current_timestamp);

insert into opinion(id, title, writer, content, date)
values (nextval('opinion_sequence'), 'Opinion Test', 'Kim', 'Opinion...', current_timestamp);
insert into opinion(id, title, writer, content, date)
values (nextval('opinion_sequence'), 'Book Request', 'Lee', 'Gof...', current_timestamp);
