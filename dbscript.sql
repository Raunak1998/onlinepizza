CREATE TABLE customer_details(customer_id NUMBER(10) primary key,first_name VARCHAR2(10),last_name VARCHAR2(10), mobile_no NUMBER(10),email_id VARCHAR2(20),address VARCHAR2(50),username VARCHAR2(20),password VARCHAR2(10));

CREATE TABLE pizza_details(pizza_id NUMBER(10) primary key,pizza_name VARCHAR2(30),type VARCHAR2(10),description VARCHAR2(100),cost NUMBER(5),pizza_size VARCHAR2(10));

CREATE TABLE order_details(order_id NUMBER(10) primary key, order_date date,customer_id NUMBER(10) REFERENCES customer_details(customer_id),coupon_name VARCHAR2(30) REFERENCES coupon_details(coupon_name),total_cost NUMBER(10));

CREATE TABLE coupon_details(coupon_name VARCHAR2(30) primary key,coupon_type VARCHAR2(20),coupon_description VARCHAR2(200));

CREATE TABLE pizza_order_details(order_id NUMBER(10) REFERENCES order_details(order_id),pizza_id NUMBER(10) REFERENCES pizza_details(pizza_id));

drop table pizza_order_details;
drop table order_details;
drop table pizza_details;
drop table customer_details;
drop table coupon_details;