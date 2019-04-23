create table if not exists car_engine
(
    id    serial primary key not null,
    type  varchar(200),
    value int
);
create table if not exists car_catalog
(
    id        serial primary key not null,
    brand     varchar(200),
    model     varchar(200),
    body      varchar(200),
    engine_id int references car_engine (id),
    gearbox   varchar(200)
);
create table if not exists users
(
    id         serial primary key not null,
    login      varchar(200),
    password   varchar(200),
    createDate timestamp,
    name       varchar(200),
    email      varchar(200),
    city       varchar(200)
);
create table if not exists ads
(
    id          serial primary key not null,
    car_id      int references car_catalog (id),
    user_id     int references users (id),
    createDate  date,
    status      varchar(200),
    year        int,
    mileage     int,
    description varchar(1950)
);

create table if not exists photo
(
    id    serial primary key not null,
    photo bytea,
    name  varchar(200),
    ad_id int references ads (id)
);