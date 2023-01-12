INSERT INTO invoice ( invoice_number, description, customer_id, create_date, state) 
VALUES('0001', 'invoice office items', 1, NOW(),'CREATED');
INSERT INTO invoice ( invoice_number, description, customer_id, create_date, state) 
VALUES( '0002', 'invoice office items', 2, NOW(),'CREATED');
INSERT INTO invoice ( invoice_number, description, customer_id, create_date, state) 
VALUES( '0003', 'invoice office items', 3, NOW(),'CREATED');

INSERT INTO invoice_item ( invoiceitem_id, product_id, quantity, price ) VALUES(1, 1 , 1, 178.89);
INSERT INTO invoice_item ( invoiceitem_id, product_id, quantity, price)  VALUES(1, 2 , 2, 12.5);
INSERT INTO invoice_item ( invoiceitem_id, product_id, quantity, price)  VALUES(1, 3 , 1, 40.06);