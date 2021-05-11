create sequence hibernate_sequence start with 1 increment by 1

create table if not exists s_users (
                            id bigint not null,
                            username varchar not null,
                            password varchar not null
                            primary key (id)
)
