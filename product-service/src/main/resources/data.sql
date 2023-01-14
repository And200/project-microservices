INSERT INTO product_categories ( category) VALUES ( 'Panes');
INSERT INTO product_categories ( category) VALUES ( 'Postres');
INSERT INTO product_categories ( category) VALUES ( 'Pasteles');
INSERT INTO product_categories ( category) VALUES ( 'Bocadillos');


INSERT INTO products ( name, description, stock,price,status, create_date,category_id)
VALUES ( 'Pan Rollo','Pan de Rollo',5,223.2,'CREATED','2022-12-05',1);

INSERT INTO products ( name, description, stock,price,status, create_date,category_id)
VALUES ( 'Pan Hojaldre','Pan de Hojaldre Preparado con hogaza madre',4,122.5,'CREATED','2022-09-05',1);


INSERT INTO products ( name, description, stock,price,status, create_date,category_id)
VALUES ( 'Pan Relleno','Pan Relleno de jamon y queso',12,400.06,'CREATED','2022-11-05',2);