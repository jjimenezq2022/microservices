

CREATE TABLE cliente (
    cliente_id  SERIAL PRIMARY KEY,
    contrasena VARCHAR(100),
    estado BOOLEAN,
   nombre VARCHAR(100),
    genero VARCHAR(10),
    edad INT,
    identificacion VARCHAR(20),
    direccion VARCHAR(200),
    telefono VARCHAR(15)
);

CREATE TABLE cuenta (
    cuenta_id SERIAL PRIMARY KEY,
    numero_cuenta VARCHAR(50),
    cliente_id INT,
    tipo_cuenta VARCHAR(50),
    saldo DECIMAL(12, 2),
    saldo_inicial  DECIMAL(12, 2),
    estado BOOLEAN,
    FOREIGN KEY (cliente_id) REFERENCES cliente(cliente_id)
);

CREATE TABLE movimiento (
    movimiento_id SERIAL PRIMARY KEY,
    cuenta_id INT,
    tipo_movimiento VARCHAR(50),
    monto DECIMAL(12, 2),
    fecha_movimiento DATE,
    FOREIGN KEY (cuenta_id) REFERENCES cuenta(cuenta_id)
);

 --DROP FUNCTION obtener_movimientos_por_fecha_y_nombre(VARCHAR,VARCHAR,VARCHAR);
-- En el ejercicio entendi que el filtro seria CASE SENSITIVE por el nombre del cliente, para
CREATE OR REPLACE FUNCTION public.obtener_movimientos_por_fecha_y_nombre(IN fecha_inicio VARCHAR, IN fecha_fin VARCHAR, IN cliente VARCHAR)
RETURNS TABLE (
	id BIGINT,
    fecha_movimiento DATE,
    nombre VARCHAR,
    numero_cuenta VARCHAR,
    tipo_cuenta VARCHAR,
    saldo_inicial DECIMAL,
    estado BOOLEAN,
    monto DECIMAL,
    saldo DECIMAL
)
  language sql
as
$$
    select ROW_NUMBER() OVER () AS id, movimiento.fecha_movimiento, cliente.nombre, cuenta.numero_cuenta, cuenta.tipo_cuenta, cuenta.saldo_inicial, cuenta.estado, movimiento.monto, cuenta.saldo
    FROM movimiento
    INNER JOIN cuenta ON cuenta.cuenta_id = movimiento.cuenta_id
    INNER JOIN cliente ON cuenta.cliente_id = cliente.cliente_id
    WHERE cliente.nombre = $3
    AND movimiento.fecha_movimiento BETWEEN $1::DATE AND $2::DATE;
$$;
/*
select * from cliente;
select * from cuenta;
select * from movimiento;
*/
