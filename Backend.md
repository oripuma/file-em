# Introduction #

The project uses Google's Tesseract OCR engine to perform the actual OCR'ing of receipts.  It also uses a SQLite database for storage.

# OCR #

File-'em makes use of [Tess4J](http://tess4j.sourceforge.net/), a library/interface for Tesseract and Java.

# SQLite #

File-'em also makes use of a JDBC for SQLite implemented in Java.   The schema is represented by `"CREATE TABLE receipts (id INTEGER PRIMARY KEY name TEXT NOT NULL, price REAL NOT NULL, category TEXT NOT NULL, datetime INTEGER NOT NULL, salestax REAL NOT NULL paymentmethod TEXT NOT NULL, notes TEXT"`