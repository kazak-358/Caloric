insert into manufacturer  (
select rownum id, t1.s||t2.s||t3.s||t4.s||t5.s||t6.s name from 
(select column_value s from table(ku$_vcnt('A', 'B', 'C', 'D', 'E', 'F' ))) t1,
(select column_value s from table(ku$_vcnt('A', 'B', 'C', 'D', 'E', 'F'))) t2,
(select column_value s from table(ku$_vcnt('A', 'B', 'C', 'D', 'E', 'F'))) t3,
(select column_value s from table(ku$_vcnt('A', 'B', 'C', 'D', 'E', 'F'))) t4,
(select column_value s from table(ku$_vcnt('A', 'B', 'C', 'D', 'E', 'F'))) t5,
(select column_value s from table(ku$_vcnt('A', 'B', 'C', 'D', 'E', 'F'))) t6
where t1.s <> t2.s and t1.s <> t3.s and t1.s <> t4.s and t1.s <> t5.s and t1.s <> t6.s and
t2.s <> t3.s and t2.s <> t4.s and t2.s <> t5.s and t2.s <> t6.s and
t3.s <> t4.s and t3.s <> t5.s and t3.s <> t6.s and 
t4.s <> t5.s and t4.s <> t6.s and 
t5.s <> t6.s);

commit;

insert into product (
select rownum id, t1.s||t2.s||t3.s||t4.s||t5.s||t6.s name, 
trunc(dbms_random.VALUE(1, 99999)) caloric , 
trunc(dbms_random.VALUE(0, 30),1) protein,
trunc(dbms_random.VALUE(0, 30),1) fat,
trunc(dbms_random.VALUE(0, 30),1) carbohydrates 
from 
(select column_value s from table(ku$_vcnt('A', 'B', 'C', 'D', 'E', 'F' ))) t1,
(select column_value s from table(ku$_vcnt('A', 'B', 'C', 'D', 'E', 'F'))) t2,
(select column_value s from table(ku$_vcnt('A', 'B', 'C', 'D', 'E', 'F'))) t3,
(select column_value s from table(ku$_vcnt('A', 'B', 'C', 'D', 'E', 'F'))) t4,
(select column_value s from table(ku$_vcnt('A', 'B', 'C', 'D', 'E', 'F'))) t5,
(select column_value s from table(ku$_vcnt('A', 'B', 'C', 'D', 'E', 'F'))) t6
where t1.s <> t2.s and t1.s <> t3.s and t1.s <> t4.s and t1.s <> t5.s and t1.s <> t6.s and
t2.s <> t3.s and t2.s <> t4.s and t2.s <> t5.s and t2.s <> t6.s and
t3.s <> t4.s and t3.s <> t5.s and t3.s <> t6.s and 
t4.s <> t5.s and t4.s <> t6.s and 
t5.s <> t6.s
);

commit;

insert into manufacturer_product (select m.id, p.id from manufacturer m, product p)

commit;