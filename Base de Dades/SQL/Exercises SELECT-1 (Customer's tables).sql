CREATE TABLE CustomerTable (
    DNI VARCHAR(255) not null,
    Name VARCHAR(255) not null,
    Province VARCHAR(255),
    Type VARCHAR(255),
    Register_Date DATE,
    Vendor  int,
    Purchases int,
    
    PRIMARY KEY (DNI)
);

SELECT * FROM CustomerTable;

--1
INSERT INTO CustomerTable VALUES ('&DNI', '&Name', '&Province', '&Type',date '&Register_Date', &Vendor, &Purchases);

--2
SELECT Name, Round(Purchases, -3)
FROM CustomerTable;

--3
SELECT Name, TYPE
FROM CustomerTable
ORDER BY TYPE DESC;

--4
SELECT *
FROM CustomerTable
WHERE Type = 'AA' AND (Province = 'Zaragoza' OR Province = 'Barcelona');

--5
SELECT Province
FROM CustomerTable
WHERE Name IS NOT NULL;

--6
SELECT Count(Province)
FROM CustomerTable
WHERE Province = 'Zaragoza';

--7
SELECT MAX(Purchases) AS a, MIN(Purchases) AS CompraMinima, ROUND(AVG(Purchases), 0) AS CompraMedia
FROM CustomerTable;

--8
SELECT DISTINCT Count(NAME), Count(Type)
FROM CustomerTable
Where Province = 'Zaragoza'
GROUP BY TYPE;

--9
SELECT DNI, NAME, ROUND(SYSDATE - REGISTER_DATE, 0) AS DiasEnLaTablas
FROM CustomerTable;

--10
SELECT SUM(Purchases)
FROM CustomerTable
GROUP BY Province;

--11
SELECT Type, Count(Province)
FROM CustomerTable
GROUP BY TYPE;

--12
SELECT ROUND(SYSDATE - REGISTER_DATE, 0)
FROM CustomerTable
GROUP BY Province
WHERE MIN(REGISTER_DATE) = REGISTER_DATE;

--13
SELECT COUNT(NAME), COUNT(Purchases)
FROM CustomerTable
GROUP BY TYPE
WHERE Purchase > 150000;

--14
DELETE FROM CustomerTable
WHERE Province = "MA";

--15
UPDATE CustomerTable
SET Purchase = Purchase*0,1
WHERE Province = "AA";

--16
CREATE SEQUENCE SEQ1
[INCREMENT BY 10]
[START WITH 1000]
[MaxValue 1050]
[MinValue 1000];

INSERT INTO DUAL VALUES (SEQ1.NEXTVAL);

--Exercises CREATE / SYNONYM / DATE

--1
CREATE TABLE STAFF(
    Cod_emp varchar(255),
    Name varchar(255) UNIQUE,
    JOB varchar(255),
    SALARY int,
    REG_DATE date DEFAULT(SYSDATE),
    Cod_dept int not null,
    Cod_supp DEFAULT(0),
    
    PRIMARY KEY (Cod_emp)
);


--2
INSERT INTO STAFF
VALUES (0998, "Martinez", "Analyst", 5700, 05, 044)

INSERT INTO STAFF
VALUES (1004, "Mulet", "Driver", 7500, 10)

INSERT INTO STAFF
VALUES (1200, "Muñoz", "Project manage", 2500, 10, 0413)

INSERT INTO STAFF
VALUES (1110, "Ortiz", "Programmer", 4000, 06, 1008)


--3


--4


--5
Select * from staff;
--Display all the information from te table staff
Create synonym for personal p;
--Creates another name for te table persnal
Select * from p;
--Display all the informatioc of the table personal using the synonym "P"
Drop SYNONYM p;
--Delete the synonym "P" from the table personal

--6
Select sysdate from dual;
--Display the current date from the table dual
Select to_char (sysdate, 'DD-MM-YYYY') from dual;
--Transform to the format "DD-MM_YYYY" the current date of the dual table
Select to_char (sysdate, 'YYDDD') from dual;
--Transform to the format "YYDDD" the current date of the dual table
Select to_char (sysdate, 'Q') from dual;

Select to_char (date, "Joined in " Ddsp " of " fmMonth " of " YYYY,
" at " HH: MI "am ") joining from staff;

--7