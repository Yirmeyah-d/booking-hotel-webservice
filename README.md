BD sqlite :
1) Recharger la bd
Option 1:
sqlite3 bookingHotel.db
sqlite> .read bookingHotel.db.sql
Option 2:
cat bookingHotel.db.sql.sql | sqlite3 bookingHotel.db.db

