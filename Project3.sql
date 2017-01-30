CREATE TABLE customer (
cus_id INTEGER AUTO_INCREMENT PRIMARY KEY,
cus_name VARCHAR(50),
address VARCHAR(50),
cc_num VARCHAR(16),
phone VARCHAR(12));

CREATE TABLE category (
cat_id INTEGER AUTO_INCREMENT PRIMARY KEY,
cat_name VARCHAR(20));

CREATE TABLE product (
prod_id INTEGER AUTO_INCREMENT PRIMARY KEY,
cat_id INTEGER,
title VARCHAR(50),
price DOUBLE,
prod_desc VARCHAR(50),
stock INT,
FOREIGN KEY (cat_id) REFERENCES category (cat_id));

CREATE TABLE orders (
order_id INTEGER AUTO_INCREMENT PRIMARY KEY,
order_date DATE,
cus_id INTEGER,
prod_id INTEGER,
prod_quant INTEGER,
total_price DOUBLE,
FOREIGN KEY (cus_id) REFERENCES customer (cus_id),
FOREIGN KEY (prod_id) REFERENCES product(prod_id));

INSERT INTO customer VALUES (1, 'John Hancock', '123 Memory Lane', '1234987465431258', '763-654-1594');
INSERT INTO customer VALUES (2, 'James Beach', '321 Memory Lane', '4567987612346587', '763-654-1593');
INSERT INTO customer VALUES (3, 'Jane Doe', '453 Memory Lane', '9876543214567891', '763-654-1592');
INSERT INTO customer VALUES (4, 'Jake Heimer', '354 Memory Lane', '7896541234567', '763-654-1591');
INSERT INTO customer VALUES (5, 'Jax Donger', '829 Memory Lane', '1234987465431258', '763-654-1590');

INSERT INTO category (cat_id, cat_name) VALUES (1, 'Action');
INSERT INTO category (cat_id, cat_name) VALUES (2, 'Anime');
INSERT INTO category (cat_id, cat_name) VALUES (3, 'Family');
INSERT INTO category (cat_id, cat_name) VALUES (4, 'Fiction');
INSERT INTO category (cat_id, cat_name) VALUES (5, 'Science');

INSERT INTO product VALUES (1, 1, 'Action Movie 101', 10.99, 'A movie about action.', 5); 
INSERT INTO product VALUES (2, 2, 'Anime Movie 101', 10.99, 'A movie about anime.', 5); 
INSERT INTO product VALUES (3, 3, 'Family Movie 101', 10.99, 'A movie about family.', 5); 
INSERT INTO product VALUES (4, 4, 'Fiction Movie 101', 10.99, 'A movie about fiction.', 5); 
INSERT INTO product VALUES (5, 5, 'Science Movie 101', 10.99, 'A movie about science.', 5); 

INSERT INTO orders VALUES (1, 20160410, 1, 1, 3, 32.97);
INSERT INTO orders VALUES (2, 20150410, 2, 2, 1, 10.99);
INSERT INTO orders VALUES (3, 20140410, 3, 3, 3, 32.97);
INSERT INTO orders VALUES (4, 20130410, 4, 4, 1, 10.99);
INSERT INTO orders VALUES (5, 20120410, 4, 5, 3, 32.97);

/* This trigger will decrease the amount of product in stock
if an order has been placed. The stock of the product will
be equal to the current stock minus the amount of product
in the order. */
CREATE TRIGGER trg_decrease_stock
AFTER INSERT ON orders
FOR EACH ROW
	UPDATE product
    SET stock = stock - (SELECT prod_quant FROM orders)
    WHERE prod_id = new.prod_id;

/* This trigger will increase the amount of stock the product
has if an order has been deleted. The stock will equal the
current stock plus the product quantity in the order deleted */
CREATE TRIGGER trg_increase_stock
AFTER DELETE ON orders
FOR EACH ROW
	UPDATE product
    SET stock = stock + (SELECT prod_quant FROM orders)
    where prod_id = old.prod_id;
    
/* How many customers there are, this could be important for 
a store to know just how much stock they should have */

SELECT count(*)
FROM customer;

/* How many orders there are for a customer, this could be
important to keep track of frequent buyers so a discount
can be given in a loyalty program */

SELECT count(*)
FROM orders NATURAL JOIN customer
GROUP BY cus_id, cus_name;

/* How much product was sold, to determine how much the store
made */

SELECT SUM(total_price)
FROM orders;

/* Find the cheapest product and it's title, this would be
useful for someone who is looking to buy the cheapest item */

SELECT title, min(price)
FROM product;

/* Find what customer ordered in their order, this would be
useful to see what item a customer has ordered and how many
was ordered. */

SELECT cus_id, title, prod_quant
FROM customer NATURAL JOIN  product NATURAL JOIN orders
WHERE cus_id = 1
GROUP BY title, prod_quant;

