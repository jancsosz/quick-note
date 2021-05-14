create sequence hibernate_sequence start with 1 increment by 1

create table s_user (
                        id NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY,
                        username VARCHAR2(30) not null,
                        password VARCHAR2(30) not null,
                        constraint user_pk primary key (id)
);