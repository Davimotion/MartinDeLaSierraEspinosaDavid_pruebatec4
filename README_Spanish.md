# TravelAgencyApi.

Se trata del backend de una API Rest que administra la base de datos de una agencia de viajes.

## Tecnologias.
- Java 17 (open JDK 17)
- Springboot
- Spring Security
- Hibernate
- MySQL
- Swagger-ui

## Funcionalidad.

La API tiene que controlar la creación, solicitud, borrado y editado (CRUD) de múltiples entidades. Estas sirven para 
llevar la cuenta de personas, billetes de avion, vuelos, reservas hoteleras, hoteles, habitaciones y ocupaciones.
Así mismo debe de:
- Controlar cuales están disponibles para usuarios que hagan login, y cuales son de uso público para clientes.
- Validar que los datos introducidos y de consulta son válidos y que no causan ciclos infinitos de recursividad
  a la hora de escribir los JSONs.
- Garantizar que los cambios se reflejan entre clases mediante relaciones.

   
##Supuestos.

-Se añaden las clases entidades Person, Ticket, Reservation, Room y Ocupation, para asemejar la API a lo que en mi
 opinion se refleja en las ajencias de viajes reales.
-Se opta por un método de borrado auténtico. A pesar de que las empresas suelen obtar por el borrado lógico, la 
rápidamente cambiante legislación de la UE sobre tratamiento de datos sugiere el borrado auténtico.
-No se pueden editar ni Tickets ni Reservations, se deben borrar y crear nuevos.
-Puede haber múltiples personas asociadas a un Ticket.
-Las reservas (Reservetions) vienen a nombre de una sola persona, y solo se puede reservar una habitación (Room)
 por persona.

##Comentario

Todo el proyecto está en inglés, incluído el comentario. Dicho comentario explica la funcionalidad del código y,
al mismo tiempo, expresa mi aprendizaje y mi frustración durante el proyecto, así como señala posibles errores para
hipotéticas iteraciones del proyecto, el cual consideraría en beta.


## Diagrama de relaciones de entidades. VER EN RAW PARA QUE SEA LEGIBLE!!!

       n      n        n        1
Person -------- Ticket ----------Flight
 1  |
    |
 n  |
Reservation------- n
 n  |             |
    |             |
 1  |             |
  Hotel           |
 1  |             |
    |             |
 n  |             |
   Room ---------- 1
 1  |
    |
 n  |
 Ocupation*

   *Sé que hay una falta de ortografía aquí, ver comentario de clase.





