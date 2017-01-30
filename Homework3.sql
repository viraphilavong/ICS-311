ALTER TABLE customer
ADD 
num_invoices INT;

ALTER TABLE customer
ADD 
num_products INT;

UPDATE customer
SET num_invoices = 1
WHERE cus_code IS NOT NULL;

UPDATE customer
SET num_products = 1
WHERE cus_code IS NOT NULL;

ALTER TABLE invoice
ADD
invoice_total INT;

UPDATE invoice
SET invoice_total = 
	(
		SELECT SUM(line_units) 
        FROM line 
        WHERE (SELECT invoice.inv_number = line.inv_number)
	)
WHERE invoice_total IS NULL;

CREATE Trigger trg_new_line
after insert on line
for each row
begin

update customer
set num_products = num_products + 1;

update invoice	
set invoice_total = invoice_total + new.invoice_total;

end;

create procedure prc_customer_discount(in cus_code int, in discount_amount int)
begin
	update invoice
    set invoice_total = invoice total - discount_amount
    where cus_code = cus_code.customer;
end;



