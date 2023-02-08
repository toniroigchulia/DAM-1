CREATE TABLE NewDepartments(
    CODE int not null,
    NAME varchar(255),
    BUDGET int,
    
    PRIMARY KEY (CODE)
);
SELECT * FROM NewDepartments;

CREATE TABLE NewEmployees(
    SSN int,
    Name VARCHAR(255),
    LastName VARCHAR(255),
    Department int,
    
    PRIMARY KEY(SSN),
    FOREIGN KEY(Department) REFERENCES NewDepartments(CODE)
);
SELECT * FROM NewEmployees;

INSERT INTO NewDepartments(Code,Name,Budget) VALUES(14,'IT',65000);
INSERT INTO NewDepartments(Code,Name,Budget) VALUES(37,'Accounting',15000);
INSERT INTO NewDepartments(Code,Name,Budget) VALUES(59,'Human Resources',240000);
INSERT INTO NewDepartments(Code,Name,Budget) VALUES(77,'Research',55000);

INSERT INTO NewEmployees(SSN,Name,LastName,Department)
VALUES('123234877','Michael','Rogers',14);
INSERT INTO NewEmployees(SSN,Name,LastName,Department)
VALUES('152934485','Anand','Manikutty',14);
INSERT INTO NewEmployees(SSN,Name,LastName,Department)
VALUES('222364883','Carol','Smith',37);
INSERT INTO NewEmployees(SSN,Name,LastName,Department)
VALUES('326587417','Joe','Stevens',37);
INSERT INTO NewEmployees(SSN,Name,LastName,Department) VALUES('332154719','MaryAnne','Foster',14);
INSERT INTO NewEmployees(SSN,Name,LastName,Department)
VALUES('332569843','George','O''Donnell',77);
INSERT INTO NewEmployees(SSN,Name,LastName,Department)
VALUES('546523478','John','Doe',59);
INSERT INTO NewEmployees(SSN,Name,LastName,Department)
VALUES('631231482','David','Smith',77);
INSERT INTO NewEmployees(SSN,Name,LastName,Department)
VALUES('654873219','Zacary','Efron',59);
INSERT INTO NewEmployees(SSN,Name,LastName,Department)
VALUES('745685214','Eric','Goldsmith',59);
INSERT INTO NewEmployees(SSN,Name,LastName,Department)
VALUES('845657245','Elizabeth','Doe',14);
INSERT INTO NewEmployees(SSN,Name,LastName,Department)
VALUES('845657246','Kumar','Swamy',14);

--Exercices--

--1
SELECT LastName
FROM NewEmployees;

--2
SELECT DISTINCT LastName
FROM NewEmployees;

--3
SELECT *
FROM NewEmployees
WHERE LastName='Smith';

--4
SELECT *
FROM NewEmployees
Where LastNAme='Smith' OR LastName='Doe';

--5
SELECT *
FROM NewEmployees
WHERE Department=14;

--6
SELECT *
FROM NewEmployees
WHERE Department=37 OR Department=77;

--7
SELECT *
FROM NewEmployees
WHERE LastName LIKE 'S%';

--8
SELECT sum(Budget), Code
FROM NewDepartments
GROUP By Code;

--9
SELECT Count(NewEmployees.SSN), NewDepartments.Code
FROM NewEmployees
INNER JOIN NewDepartments ON NewEmployees.Department=NewDepartments.Code
Group BY NewDepartments.Code;

--10
SELECT *
FROM NewEmployees
INNER JOIN NewDepartments ON NewEmployees.Department=NewDepartments.Code;

--11
SELECT NewEmployees.Name, NewEmployees.LastName, NewDepartments.Name, NewDepartments.Code
FROM NewEmployees
INNER JOIN NewDepartments ON NewEmployees.Department=NewDepartments.Code;

--12
SELECT NewEmployees.Name, NewEmployees.LastName, NewDepartments.Name, NewDepartments.Budget
FROM NewEmployees
INNER JOIN NewDepartments ON NewEmployees.Department=NewDepartments.Code
WHERE NewDepartments.Budget>=60000;

--13
SELECT NAME, Budget
FROM NewDepartments
WHERE Budget>=(SELECT avg(Budget) FROM NewDepartments);

--14
SELECT Count(NewEmployees.SSN), NewDepartments.Code
FROM NewEmployees
INNER JOIN NewDepartments ON NewEmployees.Department=NewDepartments.Code
Group BY NewDepartments.Code
HAVING Count(NewEmployees.SSN) >= 2;

--15
SELECT NewEmployees.Name, NewEmployees.LastName
FROM NewEmployees
INNER JOIN NewDepartments ON NewEmployees.Department=NewDepartments.Code
WHERE NewDepartments.Budget = (SELECT BUDGET 
                                FROM (SELECT RowNum AS Rnum, Budget 
                                        FROM NewDepartments 
                                        ORDER BY Budget DESC)
                                WHERE Rnum = 2);

--16
INSERT INTO NewDepartments(Code,Name,Budget) 
VALUES(11,'Quality Assurance',40000);

INSERT INTO NewEmployees(SSN,Name,LastName,Department)
VALUES('847219811','Mary','Moore',11);

--17
UPDATE NewDepartments
SET Budget = Budget*0.9;

--18
UPDATE NewEmployees
SET Department = 14
WHERE Department = 77;

--19
DELETE FROM NewEmployees
WHERE Department = 14;

--20
DELETE FROM NewEmployees
WHERE Department = (SELECT Code
                    FROM NewDepartments
                    WHERE Budget>=60000);
                                       
--21
Delete FROM NewEmployees;