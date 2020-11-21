#Hotel Booking Webservice  
##Requirement :  
* Apache Tomcat (v7)  
* Axis 2 SOAP (axis2-1.6.2)  
* Restlet 2.0.6  
* Sqlite3  

##Procédure de lancement :  
Initialiser la BD sqlite  
1. Option 1:
* sqlite3 bookingHotel.db. 
* sqlite> .read bookingHotel.db.sql  
2. Option 2:
* cat bookingHotel.db.sql.sql | sqlite3 bookingHotel.db.db
* Lancer le serveur Tomcat (HotelReservationWS)
* Lancer le serveur Rest (RESTDistributor)
* Lancer le Main