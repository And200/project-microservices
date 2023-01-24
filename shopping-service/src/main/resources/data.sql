INSERT INTO invoice ( invoice_number, description, customer_id, create_date)
VALUES('0001', 'invoice office items', 1, NOW());
INSERT INTO invoice ( invoice_number, description, customer_id, create_date)
VALUES( '0002', 'invoice office items', 2, NOW());
INSERT INTO invoice ( invoice_number, description, customer_id, create_date)
VALUES( '0003', 'invoice office items', 3, NOW());

INSERT INTO invoice_item ( invoice_id, product_id, quantity, price ) VALUES(1, 1 , 20, 178.89);
INSERT INTO invoice_item ( invoice_id, product_id, quantity, price)  VALUES(1, 2 , 12, 12.5);
INSERT INTO invoice_item ( invoice_id, product_id, quantity, price)  VALUES(1, 3 , 11, 40.06);