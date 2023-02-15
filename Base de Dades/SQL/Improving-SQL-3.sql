CREATE TABLE Pieces(
    Code int,
    Name varchar(255),
    
    PRIMARY KEY (Code)
);

CREATE TABLE Provides(
    Piece int,
    Provider varchar(255),
    Price int,
    
    CONSTRAINT Pk_Provides PRIMARY KEY (Piece, Provider),
    FOREIGN KEY (Piece) REFERENCES Pieces(Code),
    FOREIGN KEY (Provider) REFERENCES Providers(Code)
);

CREATE TABLE Providers(
    Code varchar(255),
    Name varchar(255),
    
    PRIMARY KEY (Code)
);

INSERT INTO Providers(Code, Name) VALUES('HAL','Clarke Enterprises');
INSERT INTO Providers(Code, Name) VALUES('RBT','Susan Calvin Corp.');
INSERT INTO Providers(Code, Name) VALUES('TNBC','Skellington Supplies');
INSERT INTO Pieces(Code, Name) VALUES(1,'Sprocket');
INSERT INTO Pieces(Code, Name) VALUES(2,'Screw');
INSERT INTO Pieces(Code, Name) VALUES(3,'Nut');
INSERT INTO Pieces(Code, Name) VALUES(4,'Bolt');
INSERT INTO Provides(Piece, Provider, Price) VALUES(1,'HAL',10);
INSERT INTO Provides(Piece, Provider, Price) VALUES(1,'RBT',15);
INSERT INTO Provides(Piece, Provider, Price) VALUES(2,'HAL',20);
INSERT INTO Provides(Piece, Provider, Price) VALUES(2,'RBT',15);
INSERT INTO Provides(Piece, Provider, Price) VALUES(2,'TNBC',14);
INSERT INTO Provides(Piece, Provider, Price) VALUES(3,'RBT',50);
INSERT INTO Provides(Piece, Provider, Price) VALUES(3,'TNBC',45);
INSERT INTO Provides(Piece, Provider, Price) VALUES(4,'HAL',5);
INSERT INTO Provides(Piece, Provider, Price) VALUES(4,'RBT',7);

--EXERCICES--
--1
SELECT Name
FROM Pieces;

--2
SELECT *
FROM Providers;

--3
SELECT Piece, AVG(Price)
FROM PROVIDES
GROUP BY Piece;

--4
    --a
SELECT NAME
FROM Providers
WHERE CODE IN (SELECT Provider
                FROM Provides
                WHERE Piece = 1);
                
    --b
SELECT Providers.Name
FROM Providers
INNER JOIN Provides
ON Providers.Code = Provides.Provider
WHERE Provides.Piece = 1;

--5
    --a
SELECT Pieces.NAME
FROM Pieces
INNER JOIN Provides
ON Pieces.Code = Provides.Piece
WHERE Provides.Provider = 'HAL';

    --b
SELECT Name
FROM Pieces
WHERE Code IN (SELECT Piece
                FROM Provides
                WHERE Provider = 'HAL');

    --c
SELECT Name
FROM Pieces
WHERE EXISTS (SELECT Piece
                FROM Provides
                WHERE Provider = 'HAL');
                
--6
SELECT PIECES.Name, Providers.Name, Provides.PRICE
FROM PROVIDES
INNER JOIN PIECES
ON PROVIDES.PIECE = PIECES.CODE
INNER JOIN PROVIDERS
ON Provides.PROVIDER = PROVIDERS.CODE
WHERE PROVIDES.PIECE = (SELECT PIECE
                        FROM PROVIDES
                        WHERE PRICE = Max(Price));

--7
INSERT INTO Provides(Piece, Provider, Price) 
VALUES(1,'TNBC',7);

--8
UPDATE PROVIDES
SET Price = Price + 1;

--9
DELETE FROM Provides
WHERE Provider = 'RBT' AND PIECE = 4;

--10
UPDATE PROVIDES
SET Price = NULL, PIECE = NULL
WHERE Provider = 'RBT';