# microservices
El microservice cliente-persona  se ejecuta en el puerto 8089
El microservice cuenta-movimiento  se ejecuta en el puerto 8090

Se implemento una comunicación sincrona entre ambos micros, por lo que para ejecutar el micro cuenta-movimiento, el micro cliente-persona debe estar ejecutandose.

Se adjunta script SQL BaseDatos.sql, la implementación la hice apuntando a un RDS Amazon gratuita

Se adjunta archivo Devsu.postman_collection.json de Aplicacioon Postman 

Se implemento 1 prueba unitaria para la entidad de dominio Cliente

Se implemento 1 prueba de integracion para CuentaController