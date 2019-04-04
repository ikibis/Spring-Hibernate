create table if not exists car_engine
(
    id    serial primary key not null,
    type  varchar(200),
    value numeric(1)
);
create table if not exists car_catalog
(
    id        serial primary key not null,
    car_name  varchar(200),
    body      varchar(200),
    engine_id int references car_engine (id),
    gearbox   varchar(200)
);
