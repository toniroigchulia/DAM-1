/*Tabla de clubs de futbol*/
CREATE TABLE ClubTable(

    TeamName varchar(255),
    StadiumName varchar(255),
    Capacity int,
    Community int,
    Foundation int,
    
    PRIMARY KEY (TeamName)
);

SELECT * FROM ClubTable;

DROP TABLE ClubTable;
/**/



/*Tabla de jugadores de futbol*/
CREATE TABLE PlayersTable(

    Name varchar(255),
    Club varchar(255),
    DateofBirth Date,
    Weight varchar(255),
    PlayerNumber int,
    Position varchar(255),
    
    PRIMARY KEY (Name)
);

SELECT * FROM PlayersTable;

DROP TABLE PlayersTable;
/**/



/*Tabla de empleados de la empresa de ordenadores*/
CREATE TABLE StaffComputerCompany(

    EmployeeCode int,
    Name varchar(255),
    Job varchar(255),
    Salary int,
    DepartmentCode int,
    StartDate DATE,
    SuperiorOfficer varchar(255),
    
    PRIMARY KEY (EmployeeCode)
);



ALTER TABLE StaffComputerCompany
ADD FOREIGN KEY (DepartmentCode) REFERENCES DepartmentComputerCompany(DepartmentCode);



INSERT INTO StaffComputerCompany (EmployeeCode, Name, Job, Salary, DepartmentCode, StartDate, SuperiorOfficer)
VALUES (0368, 'Almoacid', 'Head', 9700, 12, Date '2012-12-20', Null);

INSERT INTO StaffComputerCompany (EmployeeCode, Name, Job, Salary, DepartmentCode, StartDate, SuperiorOfficer)
VALUES (0413, 'Aneas', 'Analyst', 6000, 05, Date '2013-06-01', 1008);

INSERT INTO StaffComputerCompany (EmployeeCode, Name, Job, Salary, DepartmentCode, StartDate, SuperiorOfficer)
VALUES (0545, 'Barcelo', 'Analyst', 5600, 05, Date '2012-11-01', 1008);

INSERT INTO StaffComputerCompany (EmployeeCode, Name, Job, Salary, DepartmentCode, StartDate, SuperiorOfficer)
VALUES (0552, 'Batle', 'Analyst', 5500, 05, Date '2013-10-15', 1190);

INSERT INTO StaffComputerCompany (EmployeeCode, Name, Job, Salary, DepartmentCode, StartDate, SuperiorOfficer)
VALUES (0663, 'Borras', 'Analyst', 6700, 05, Date '2013-01-02', 1190);

INSERT INTO StaffComputerCompany (EmployeeCode, Name, Job, Salary, DepartmentCode, StartDate, SuperiorOfficer)
VALUES (0765, 'Cedeño', 'Programmer', 3800, 08, Date '2013-06-01', 0413);

INSERT INTO StaffComputerCompany (EmployeeCode, Name, Job, Salary, DepartmentCode, StartDate, SuperiorOfficer)
VALUES (0998, 'Fernandez', 'Programmer', 4300, 08, Date '2013-01-01', 0413);

INSERT INTO StaffComputerCompany (EmployeeCode, Name, Job, Salary, DepartmentCode, StartDate, SuperiorOfficer)
VALUES (1003, 'Flores', 'Analyst', 6600, 05, Date '2013-12-01', 1190);

INSERT INTO StaffComputerCompany (EmployeeCode, Name, Job, Salary, DepartmentCode, StartDate, SuperiorOfficer)
VALUES (1008, 'Fresneda', 'Project Manager', 7800, 10, Date '2013-05-12', 0368);

INSERT INTO StaffComputerCompany (EmployeeCode, Name, Job, Salary, DepartmentCode, StartDate, SuperiorOfficer)
VALUES (1087, 'Galiano', 'Programmer', 4000, 08, Date '2013-02-01', 1003);

INSERT INTO StaffComputerCompany (EmployeeCode, Name, Job, Salary, DepartmentCode, StartDate, SuperiorOfficer)
VALUES (1190, 'Garcia', 'Project Manager', 8000, 10, Date '2012-09-01', 0368);



SELECT * FROM StaffComputerCompany;



DROP TABLE StaffComputerCompany;
/**/



/*Tabla de Departamentos de la companyia de ordenadores*/
CREATE TABLE DepartmentComputerCompany(

    DepartmentCode int,
    Name varchar(255),
    City varchar(255),
    
    PRIMARY KEY (DepartmentCode)

);

INSERT INTO DepartmentComputerCompany (DepartmentCode, Name, City)
VALUES (12, 'Direction', 'Palma');

INSERT INTO DepartmentComputerCompany (DepartmentCode, Name, City)
VALUES (05, 'Analysis', 'Barcelona');

INSERT INTO DepartmentComputerCompany (DepartmentCode, Name, City)
VALUES (08, 'Programming', 'Maó');

INSERT INTO DepartmentComputerCompany (DepartmentCode, Name, City)
VALUES (10, 'Control', 'Eivissa');



SELECT * FROM DepartmentComputerCompany;



DROP TABLE DepartmentComputerCompany;
/**/



/*Exercices Insert/Update/Delete*/

--Staff and Department Table--

--1
INSERT INTO DepartmentComputerCompany (DEPARTMENTCODE, Name, CITY)
VALUES (11,'Consulting', 'Ciutadella');

--2
INSERT INTO StaffComputerCompany (EmployeeCode, Name, Job, Salary, DepartmentCode, StartDate, SuperiorOfficer)
VALUES (1200, 'Juan', 'Project Analist', 3000, 05, Date '2023-01-19', null);

--3
INSERT INTO STAFFCOMPUTERCOMPANY (EMPLOYEECODE,NAME,JOB,SALARY,DEPARTMENTCODE,STARTDATE,SUPERIOROFFICER)
VALUES (1333,'Maroto',null,5000,11,date '2023-01-19',null);

--4
UPDATE STAFFCOMPUTERCOMPANY
SET STARTDATE = date '2023-01-19'
WHERE name = 'Borras';

UPDATE STAFFCOMPUTERCOMPANY
SET NAME = 'Almonacid'
WHERE EMPLOYEECODE = 0368;

--5
UPDATE STAFFCOMPUTERCOMPANY
SET JOB = 'Programer', SALARY = SALARY*1.2, SUPERIOROFFICER = 1003
WHERE NAME = 'Batle';

--6
UPDATE STAFFCOMPUTERCOMPANY
SET SALARY = SALARY*1.06;

--7
DELETE FROM STAFFCOMPUTERCOMPANY
WHERE NAME = 'Galiano';

--8
/*
With these comand we change the department code and the salary of the 
empleyoees from the table Staff A wich his department code is from the 
city name Ibiza. The department code change at the same as the department
code of the city of palma and the salary is an increase of the 10% of the
salary of the Staff B table for all the employees that shares department code
*/

--9
UPDATE STAFFCOMPUTERCOMPANY
SET SALARY = SALARY*1.1
WHERE DEPARTMENTCODE IN (SELECT DEPARTMENTCODE
                        FROM STAFFCOMPUTERCOMPANY
                        GROUP BY DEPARTMENTCODE
                        HAVING COUNT(*) = (SELECT MIN(count(*))
                                            FROM STAFFCOMPUTERCOMPANY
                                            GROUP BY DEPARTMENTCODE));
                                            
--10
INSERT INTO StaffComputerCompany (EmployeeCode, Name, Job, Salary, DepartmentCode, StartDate, SuperiorOfficer)
VALUES (1265, 'Toni', 'Programer', 4000, 08, Date '2023-01-19', null);

UPDATE StaffComputerCompany
SET SALARY = SALARY*1.5
WHERE NAME = 'Toni';


--Football	Players	Tables--

--1


--2
SELECT TeamName, Foundation, ISNULLL(Foundation, '1890')
FROM CLUBTABLE;

--3
SELECT STADIUMNAME, ROUND(CAPACITY, -2)
FROM CLUBTABLE;

--4
SELECT TEAMNAME, STADIUMNAME, DIFFERENCE(CAPACITY, AVG(CAPACITY))
FROm CLUBTABLE;


--5
SELECT TEAMNAME, STADIUMNAME
FROM CLUBTABLE
WHERE MAX(COMMUNITY) = COMMUNITY;

--6
SELECT COUNT(TEAMNAME)
FROM CLUBTABLE
WHERE FOUNDATION = Null; --Witohut these WHERE we get the count of all the clubs

--7


--8
SELECT TEAMNAME
FROM CLUBTABLE
INNER JOIN PLAYERSTABLE
ON CLUBTABLE.TEAMNAME = PLAYERSTABLE.CLUB
WHERE COUNT(Name) = 5;