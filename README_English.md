# TravelAgencyApi.
 It's the backend portion of a Rest API that administers the database for a travel agency.

## Technologies.
- Java 17 (open JDK 17)
- Springboot
- Spring Security
- Hibernate
- MySQL
- Swagger-ui


##Functionality.

The API has to control the creation, request, updates and edits (CRUD) of multiple entities. This serve to take accounting
of people, plane tickets, flights, hotels, hotel reservations, rooms and it's occupations.
As such, it must:
-Check which endpoints are publicly available and which are reserved for authorized users.
-Validate that the data introduced an requested are valid and don't cause infinite recursion loops when writing JSONs.
-Guarantee that the changes are reflected in the database through mapped relationships.

##Supositions

-The entity classes Person, Ticket, Reservation, Room and Ocupation have been added to resemble what in my criteria would be 
a more realistic travel agency API.
-A true deletion method has been chosen. Even though real companies would opt for "logic deletion", the quickly changing EU
 regulations about data treatment suggest the use of a true deletion.
-Tickets and Reservations can't be edited, they must be deleted and new ones must be created.
-Multiple people can be associated with a ticket.
-Reservations are associated with one person, and only one room can be reserved for reservation.

##Commentary

The code comments describe both the code functionality as well as both my learning process and frustration with the project.
I would consider this project to be in beta.

##Entity relationship diagram. SEE IN RAW!!!

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
