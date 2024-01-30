drop table if exists plant;
create table plant (
                       id LONG primary key ,
                       name varchar,
                       address varchar,
                       create_date date,
                       date_add_in_base date
);

drop table if exists device;
create table device (
                        id LONG primary key ,
                        ui varchar,
                        date_create date,
                        device_name varchar,
                        name_director_change varchar,
                        plant_id long,
                        foreign key (plant_id) references plant (id)
);
