create table product(
id number primary key,
name varchar2(100) unique not null,
caloric number(5) not null check (caloric >0),
protein number(3,1) default 0 not null check (protein >=0 and protein <100),
fat number(3,1) default 0 not null check (fat >=0 and fat < 100),
carbohydrates number(3,1) default 0 not null check (carbohydrates >=0 and carbohydrates < 100),
check (protein + fat + carbohydrates <= 100) 
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
