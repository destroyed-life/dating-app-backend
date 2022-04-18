create table ideal_type (
    id bigint not null auto_increment,
    description varchar(255),
    type varchar(255) not null,
    primary key (id)
);
create table profile_image (
   id bigint not null auto_increment,
   created_at datetime(6) not null,
   image varchar(255) not null,
   primary key (id)
);
create table users (
    id bigint not null auto_increment,
    alcohol_mention varchar(255),
    base_address varchar(255),
    birth_day date,
    blood varchar(255),
    created_at datetime(6) not null,
    deleted_at datetime(6),
    detail_address varchar(255),
    email varchar(50),
    gender varchar(255),
    height double precision not null,
    hobby longtext,
    human_body varchar(255),
    if_love_todo longtext,
    interest longtext,
    introduce longtext,
    job varchar(255),
    mbti varchar(255),
    name varchar(30) not null,
    nickname varchar(50) not null,
    password varchar(50) not null,
    religion varchar(255),
    role varchar(255) not null,
    smoke_mention varchar(255),
    primary key (id)
);
create table users_ideal_type (
    id bigint not null auto_increment,
    ideal_type_id bigint,
    user_id bigint,
    primary key (id)
);
create table users_profile_image (
    id bigint not null auto_increment,
    profile_image_id bigint,
    user_id bigint,
    primary key (id)
);
alter table users
    add constraint UK_6dotkott2kjsp8vw4d0m25fb7 unique (email);
alter table users_ideal_type
    add constraint FK437b2jpuu3fdpl09nju7lht8b
        foreign key (ideal_type_id)
            references ideal_type (id);
alter table users_ideal_type
    add constraint FKk6h6l79b2oio5xtqejqp0euxo
        foreign key (user_id)
            references users (id);
alter table users_profile_image
    add constraint FKgq7p50279ercuu49pqe3cpa74
        foreign key (profile_image_id)
            references profile_image (id);
alter table users_profile_image
    add constraint FKdimjfe0g4mkfj7nmamraxq48p
        foreign key (user_id)
            references users (id);