create database bestdeal;
use bestdeal;

create table Registration(
userID varchar(11),
username varchar(40),
password varchar(40),
usertype varchar(40));

create table CustomerOrder(
OrderId integer,
userID varchar(11),
userName varchar(40),
orderName varchar(40),
productID varchar(11),
quantity int,
discount double,
shippingCost double,
netTotal double,
orderPrice double,
userAddress varchar(60),
creditCardNo varchar(40),
mode varchar(10),
storeID varchar(11),
location varchar(40),
orderDate varchar(16),
shipDate varchar(16),
Primary key (orderid, userName, orderName));

Create table Productdetails
(
productType varchar(20),
ID varchar(20),
productName varchar(40),
productPrice double,
productImage varchar(50),
productManufacturer varchar(40),
productCondition varchar(40),
productDiscount double,
description varchar(60),
Primary key(Id)
);

Create table locations
(
storeID varchar(20),
street varchar(40),
city varchar(20),
state varchar(50),
zip varchar(40),
);
