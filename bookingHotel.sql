DROP TABLE IF EXISTS Hotel;
DROP TABLE IF EXISTS Room;
DROP TABLE IF EXISTS Reservation;
DROP TABLE IF EXISTS Client;


CREATE TABLE Hotel (idHotel integer PRIMARY KEY, hotelName varchar (50) NOT NULL, hotelAdress varchar (100) NOT NULL);

CREATE TABLE Room (idRoom integer PRIMARY KEY , hotelId integer NOT NULL, nightPrice varchar (20) NOT NULL, FOREIGN KEY (hotelId) REFERENCES Hotel (idHotel));

CREATE TABLE Client (idClient  integer PRIMARY KEY AUTOINCREMENT, firstName varchar (50) NOT NULL, lastName varchar (50) NOT NULL, email varchar(254) UNIQUE NOT NULL, password varchar(255) NOT NULL, phone integer (10) UNIQUE);

CREATE TABLE Reservation (idRervation integer PRIMARY KEY AUTOINCREMENT, hotelId integer NOT NULL, roomId integer NOT NULL, customerId integer NOT NULL, rentalDate varchar (20) NOT NULL, endDate varchar (20) NOT NULL, FOREIGN KEY (hotelId) REFERENCES Hotel (idHotel), FOREIGN KEY (roomId) REFERENCES Room (idRoom), FOREIGN KEY (customerId) REFERENCES Client (idClient));

INSERT INTO Hotel VALUES(1,"Dadou Hotel","1 rue Square de la résistance");
INSERT INTO Hotel VALUES(2,"Vivi Hotel","2 place de la Triomphe");
INSERT INTO Hotel VALUES(3,"Lala Hotel","3 place de la République");

INSERT INTO Room VALUES(1,1,"100€");
INSERT INTO Room VALUES(2,1,"120€");
INSERT INTO Room VALUES(3,1,"150€");
INSERT INTO Room VALUES(4,2,"250€");
INSERT INTO Room VALUES(5,3,"350€");


INSERT INTO Client VALUES(1,"Jeremy","DORMEVIL","jeremydormevil@gmail.com","123456", 0625487521);
INSERT INTO Client VALUES(2,"Aida","AZI","aidaazi@gmail.com","123456", 0625487524);


INSERT INTO Reservation VALUES(1,1,1,1,"2020-06-15","2020-06-20");
INSERT INTO Reservation VALUES(2,1,3,2,"2020-12-18","2020-12-28");
INSERT INTO Reservation VALUES(3,3,5,1,"2020-12-18","2020-12-22");
INSERT INTO Reservation VALUES(4,1,1,2,"2020-12-05","2020-12-08");
INSERT INTO Reservation VALUES(5,1,3,2,"2020-05-20","2020-05-27");
INSERT INTO Reservation VALUES(6,1,1,2,"2020-06-21","2020-06-27");
INSERT INTO Reservation VALUES(7,1,1,2,"2020-11-10","2020-11-20");


Select  distinct h.idHotel,h.hotelName,h.hotelAdress,idRoom from Room,Hotel as h where idRoom NOT IN(SELECT roomId FROM Reservation WHERE ('2020-11-09' BETWEEN rentalDate  AND endDate) OR ('2020-11-11' BETWEEN rentalDate  AND endDate)) and h.idHotel ==Room.hotelId ;

Select  distinct h.idHotel from Room,Hotel as h where idRoom NOT IN(SELECT roomId FROM Reservation WHERE ('2020-11-09' BETWEEN rentalDate  AND endDate) OR ('2020-11-11' BETWEEN rentalDate  AND endDate)) and h.idHotel ==Room.hotelId GROUP BY  h.idHotel HAVING COUNT(h.idHotel) >= 2 ;

Select  distinct h.idHotel,h.hotelName,h.hotelAdress,idRoom from Room,Hotel as h where idRoom NOT IN(SELECT roomId FROM Reservation WHERE ('2020-11-09' BETWEEN rentalDate  AND endDate) OR ('2020-11-11' BETWEEN rentalDate  AND endDate)) AND h.idHotel ==Room.hotelId AND h.idHotel IN (Select  distinct h.idHotel from Room,Hotel as h where idRoom NOT IN(SELECT roomId FROM Reservation WHERE ('2020-11-09' BETWEEN rentalDate  AND endDate) OR ('2020-11-11' BETWEEN rentalDate  AND endDate)) and h.idHotel ==Room.hotelId GROUP BY  h.idHotel HAVING COUNT(h.idHotel) >= 2 ) ;



