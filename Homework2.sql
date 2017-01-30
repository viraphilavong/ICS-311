CREATE TABLE customer(
cus_code INTEGER PRIMARY KEY,
cus_lname VARCHAR(20) NOT NULL,
cus_fname VARCHAR(20) NOT NULL,
cus_initial CHAR,
cus_areacode INTEGER NOT NULL,
cus_phone INTEGER NOT NULL);

CREATE TABLE invoice(
inv_number INTEGER PRIMARY KEY,
cus_code INTEGER NOT NULL,
inv_date DATE NOT NULL,
FOREIGN KEY (cus_code) references customer (cus_code));

CREATE TABLE product(
prod_code INTEGER PRIMARY KEY,
prod_desc VARCHAR(50) NOT NULL,
prod_price INTEGER NOT NULL,
prod_quant INTEGER NOT NULL,
vend_code INTEGER NOT NULL,
FOREIGN KEY (vend_code) references vendor(vend_code));

CREATE TABLE line(
inv_number INTEGER NOT NULL,
prod_code INTEGER NOT NULL,
line_units INTEGER NOT NULL,
FOREIGN KEY (inv_number) references invoice (inv_number),
FOREIGN KEY (prod_code) references product (prod_code));

CREATE TABLE vendor(
vend_code INTEGER PRIMARY KEY,
vend_name VARCHAR(30) NOT NULL,
vend_contact VARCHAR(30) NOT NULL,
vend_areacode INTEGER NOT NULL, 
vend_phone INTEGER NOT NULL);

INSERT INTO customer VALUES (10010, "Ramas", "Alfred", "A", 615, 8442573);
INSERT INTO customer VALUES (10011, "Dunne", "Leona", "K", 713, 8941238);
INSERT INTO customer VALUES (10012, "Smith", "Kathy", "W", 615, 8442285);
INSERT INTO customer VALUES (10013, "Olowski", "Paul", "F", 615, 2221672);
INSERT INTO customer VALUES (10014, "Orlando", "Myron", NULL, 615, 2971228);

INSERT INTO invoice VALUES (1001, 10011, '2008-08-03');
INSERT INTO invoice VALUES (1002, 10014, '2008-08-04');
INSERT INTO invoice VALUES (1003, 10012, '2008-03-20');
INSERT INTO invoice VALUES (1004, 10011, '2008-09-23');

INSERT INTO line VALUES (1001, 12321, 1);
INSERT INTO line VALUES (1001, 65781, 3);
INSERT INTO line VALUES (1002, 34256, 6);
INSERT INTO line VALUES (1003, 12321, 5);
INSERT INTO line VALUES (1002, 12333, 6);

INSERT INTO product VALUES (12321, "hammer", 189, 20 , 232);
INSERT INTO product VALUES (65781, "chain", 12, 45, 235);
INSERT INTO product VALUES (34256, "tape", 35, 60, 235);
INSERT INTO product VALUES (12333, "hanger", 200, 10, 232);

INSERT INTO vendor VALUES (232, "Bryson", "Smith", 615, 2233234);
INSERT INTO vendor VALUES (235, "SuperLoo", "Anderson", 615, 2158995);

SELECT inv_number, inv_date FROM invoice;

SELECT prod_code, prod_quant FROM product, invoice WHERE inv_number = 1001;

SELECT prod_desc, prod_price FROM product, vendor WHERE vend_name = "Smith";

SELECT prod_desc, vend_name, vend_phone FROM product, vendor WHERE prod_price < 50;

SELECT prod_desc, cus_lname, cus_fname FROM customer, product;

SELECT vend_code FROM product WHERE prod_price < 50;

SELECT MIN(prod_price) AS prod_code, vend_code, prod_price FROM product;

SELECT inv_number, SUM(prod_price) FROM invoice, product;

SELECT SUM(line_units) FROM line, invoice WHERE (SELECT invoice.inv_number = line.inv_number);

SELECT inv_number FROM invoice GROUP BY cus_code;
