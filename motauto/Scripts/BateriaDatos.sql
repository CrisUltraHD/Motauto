INSERT INTO articulos (codigo, nombre, precio, iva) VALUES ("M001","Mano de obra", 30, 0.21);
INSERT INTO articulos (codigo, nombre, precio, iva) VALUES ("A001","Filtro aceite Bosch", 15, 0.21);
INSERT INTO articulos (codigo, nombre, precio, iva) VALUES ("A002","Filtro aire K&n", 70.29, 0.21);
INSERT INTO articulos (codigo, nombre, precio, iva) VALUES ("A003","Aceite Motul 4100 Performance 10W-40, 5L", 23.97, 0.21);
INSERT INTO articulos (codigo, nombre, precio, iva) VALUES ("A004","Escobilla Bosch Aerotwin A8635 650mm/450mm", 19.68, 0.21);
INSERT INTO articulos (codigo, nombre, precio, iva) VALUES ("S001","Calibració rodes", 45, 0.21);


INSERT INTO clientes (dni, nombre, apellidos , correo, telefono, direccion) VALUES ("41528630Z","Pedro","Ponce Sanchez", "pereponce7@gmail.com", 622072104, "pozo alcon 62");
INSERT INTO clientes (dni, nombre, apellidos , correo, telefono, direccion) VALUES ("41508030A","Jordi","Poch", "info@instalaciónspoch.com", 663 72 82 31, "Instal·lacions Poch");
INSERT INTO clientes (dni, nombre, apellidos , correo, telefono, direccion) VALUES ("42222222f","Cristian","Caride Rodriz", "cariderodriguez@gmail.com", 662471231, "Napoles 17255");
INSERT INTO clientes (dni, nombre, apellidos , correo, telefono, direccion) VALUES ("42285782Q","Derar","El Koumssi", "delkoumssi@gmail.com", 622092104, "Villademany 5");
INSERT INTO clientes (dni, nombre, apellidos , correo, telefono, direccion) VALUES ("41509630Z","Sergio","Deulofeu Blinder", "deulowtf@gmail.com", 611072104, "Birmingham 69");



INSERT INTO vehiculo (matricula, dni, color , tipo) VALUES ("4040MKR", "41528630Z", "gris", "coche");
INSERT INTO vehiculo (matricula, dni, color , tipo) VALUES ("666DKR", "42222222f", "blau", "furgoneta");
INSERT INTO vehiculo (matricula, dni, color , tipo) VALUES ("777AAA", "42222211f", "groc", "camieno");
INSERT INTO vehiculo (matricula, dni, color , tipo) VALUES ("888BBB", "42222233f", "verd", "moto");
INSERT INTO vehiculo (matricula, dni, color , tipo) VALUES ("999CCC", "42222244f", "vermell", "coche");


INSERT INTO facturas_header (cif_empresa, dni_cliente, fecha, forma_pago, matricula, estado, total_base_imponible, descuento, total_iva, total_factura, observaciones) 
VALUES ('123', '41528630Z', '2020-06-07', 'efectivo', '4040MKR', '1', 200, 0, 42, 242, 'xd');

INSERT INTO facturas_header (cif_empresa, dni_cliente, fecha, forma_pago, matricula, estado, total_base_imponible, descuento, total_iva, total_factura, observaciones) 
VALUES ('123', '41528630Z', '2020-06-07', 'efectivo', '4040MKR', '1', 200, 0, 42, 242, 'xd');

INSERT INTO facturas_header (cif_empresa, dni_cliente, fecha, forma_pago, matricula, estado, total_base_imponible, descuento, total_iva, total_factura, observaciones) 
VALUES ('123', '41528630Z', '2020-06-07', 'efectivo', '4040MKR', '1', 200, 0, 42, 242, 'xd');

INSERT INTO facturas_filas (num_factura, codigo_articulo, nombre, cantidad, num_fila, descuento, precio_total) VALUES (1,"M001","Mano de obra", 4, 0, 0, 145.2);
