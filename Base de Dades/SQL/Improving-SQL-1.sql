--CREATE TABLES
CREATE TABLE Manufacturers(
    Code int,
    Name varchar(255),
    
    PRIMARY KEY (Code)
);

CREATE TABLE Products(
    Code int,
    Name varchar(255),
    Price int,
    Manufacturer,
    
    PRIMARY Key(Code),
    FOREIGN Key(Manufacturer) REFERENCES Manufacturers(Code)
);

SELECT * FROM Manufacturers;

SELECT * FROM Products;


--SAMPLE DATASET
INSERT INTO Manufacturers(Code,Name) VALUES(1,'Sony');
INSERT INTO Manufacturers(Code,Name) VALUES(2,'Creative Labs');
INSERT INTO Manufacturers(Code,Name) VALUES(3,'Hewlett-Packard');
INSERT INTO Manufacturers(Code,Name) VALUES(4,'Iomega');
INSERT INTO Manufacturers(Code,Name) VALUES(5,'Fujitsu');
INSERT INTO Manufacturers(Code,Name) VALUES(6,'Winchester');

INSERT INTO Products(Code,Name,Price,Manufacturer) VALUES(1,'Hard drive',240,5);
INSERT INTO Products(Code,Name,Price,Manufacturer) VALUES(2,'Memory',120,6);
INSERT INTO Products(Code,Name,Price,Manufacturer) VALUES(3,'ZIP drive',150,4);
INSERT INTO Products(Code,Name,Price,Manufacturer) VALUES(4,'Floppy disk',5,6);
INSERT INTO Products(Code,Name,Price,Manufacturer) VALUES(5,'Monitor',240,1);
INSERT INTO Products(Code,Name,Price,Manufacturer) VALUES(6,'DVD drive',180,2);
INSERT INTO Products(Code,Name,Price,Manufacturer) VALUES(7,'CD drive',90,2);
INSERT INTO Products(Code,Name,Price,Manufacturer) VALUES(8,'Printer',270,3);
INSERT INTO Products(Code,Name,Price,Manufacturer) VALUES(9,'Toner cartridge',66,3);
INSERT INTO Products(Code,Name,Price,Manufacturer) VALUES(10,'DVD burner',180,2);

--EXERCICES

--1
SELECT NAME
FROM Products;

--2
SELECT Name, Price 
FROM Products;

--3
SELECT Name, Price     
FROM Products
WHERE Price <= 200;

--4
SELECT Name, Price
FROM Products
WHERE Price BETWEEN 60 AND 120;

--5
SELECT Name, Price*100 AS Price_In_Cents
FROM Products;

--6
SELECT AVG(Price) AS Average_Price
FROM Products;

--7
SELECT AVG(Price) AS Average_Price
FROM Products
WHERE Manufacturer = 2;

--8
SELECT COUNT(*) AS Total_Products
FROM Products
WHERE Price >= 180;

--9
SELECT Name, Price
FROM Products
WHERE Price >= 180
ORDER BY Price DESC;
ORDER BY Name ASC;

--10
SELECT *
FROM Products, Manufacturers;

--11
SELECT Products.Name, Price, Manufacturers.Name
FROM Products, Manufacturers;

--12
SELECT AVG(Price), Manufacturers.Code
FROM Products
INNER Join Manufacturers
ON products.Manufacturer = Manufacturers.Code
Group by manufacturers.code;

--13
SELECT AVG(Price), Manufacturers.name
FROM Products
INNER Join Manufacturers
ON products.Manufacturer = Manufacturers.Code
Group by manufacturers.code;

--14
SELECT Manufacturers.name, avg(price)
FROM Manufacturers
INNER Join Products
ON manufacturers.code = products.Manufacturer
Group by manufacturers.name
HAVING AVG(Price) >= 150;

--15
SELECT NAME, Price
FROM PRODUCTS
WHERE price = (SELECT min(PRICE) From PRODUCTS);

--16
SELECT MANUFACTURERS.NAME, PRODUCTS.PRICE
FROM PRODUCTS
INNER JOIN MANUFACTURERS
ON PRODUCTS.MANUFACTURER = MANUFACTURERS.CODE
WHERE PRICE = (SELECT max(PRICE) FROM PRODUCTS);

--17
INSERT INTO Products(Code,Name,Price,Manufacturer) VALUES(null,'Loudspeaker',70,2);

--18
UPDATE PRODUCTS
SET name = "Laser Prinet"
WHERE code = 8;

--19
UPDATE PRODUCTS
SET PRICE = PRICE*0,1;

--20
UPDATE PRODUCTS
SET PRICE = PRICE*0,1
WHERE PRICE >= 120;