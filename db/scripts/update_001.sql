create table if not exists car_body
(
    id        serial primary key not null,
    body_type int
);

create table if not exists car_engine
(
    id          serial primary key not null,
    engine_type int
);

create table if not exists car_gearbox
(
    id           serial primary key not null,
    gearbox_type int
);

create table if not exists car_catalog
(
    id         serial primary key not null,
    car_name   varchar(200),
    body_id    int references car_body (id),
    engine_id  int references car_engine (id),
    gearbox_id int references car_gearbox (id)
);

insert into car_body(body_type) values('Sedan');
insert into car_body(body_type) values('Liftback');
insert into car_body(body_type) values('SUV');

insert into car_engine (engine_name) values('TSI');
insert into car_engine (engine_name) values('TDI');
insert into car_engine (engine_name) values('TFSI');

insert into car_gearbox (gearbox_name) values('S-Tronic');
insert into car_gearbox (gearbox_name) values('MultiTronic');
insert into car_gearbox (gearbox_name) values('Aisin');