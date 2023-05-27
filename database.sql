CREATE SCHEMA `ecommerce`;
CREATE TABLE `ecommerce`.`customer`(
`id` INT NOT NULL AUTO_INCREMENT,
`name` VARCHAR(60) NOT NULL,
`mobile` CHAR(12) NOT NULL,
`email` VARCHAR(100) NOT NULL,
`password` VARCHAR(100) NOT NULL,
`address` VARCHAR(200) NULL,
PRIMARY KEY(`id`));

INSERT INTO customer(name,email,mobile,password) VALUES('Khan','er.moiz99@gmail.com','8328177702','abc123');

SELECT * FROM customer;

DELETE FROM customer WHERE id=2;

SELECT * FROM customer WHERE email='er.moiz2102@gmail.com' AND password ='abc123';

INSERT INTO product(name,price,quantity) VALUES('Lenovo X1 ThinkPad',120000,50);

INSERT INTO product(name,price,quantity) VALUES('Samsung Galaxy Note 10',110000,40);

INSERT INTO product(name,price,quantity) VALUES('Iphone 14 pro max',140000,70);

SELECT id,name,price FROM product;
SELECT * FROM orders;

-- INSERT INTO orders(group_order_id,customer_id,product_id) VALUES(1,1,1);

SELECT max(group_order_id)+1 id FROM orders;