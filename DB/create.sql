create table product(
id number primary key,
name varchar2(100) unique not null,
caloric number(6) not null check (caloric >0),
protein number(2) not null check (protein >=0),
fat number(2) not null check (fat >=0),
carbohydrates number(2) not null check (carbohydrates  >=0)
);

create table manufacturer(
id number primary key,
name varchar2(100) unique not null
);

create table manufacturer_product(
id_manufacturer number,
id_product number,
primary key (id_manufacturer, id_product)
);

alter table manufacturer_product add constraint FK_MANUFACTURER foreign key (id_manufacturer) references manufacturer;
alter table manufacturer_product add constraint FK_PRODUCT foreign key (id_product) references product;
