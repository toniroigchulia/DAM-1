create Table Students (
    ID int,
    name VARCHAR(255),
    surname VARCHAR(255),
    mark float,
    size_high_school int,
    
    PRIMARY KEY (ID)
);

create Table College (
    name VARCHAR(255),
    state VARCHAR2(2),
    enrollment int,
    
    PRIMARY KEY (name)
);

create Table Applies (
    sid int,
    college VARCHAR2(255),
    major varchar(255),
    decision int,
    
    PRIMARY KEY (sid)
);

INSERT INTO Students VALUES ('123','Amy','Smith',3.9,1000);
INSERT INTO Students VALUES ('234','Bob','Taylor',3.6,1500);
INSERT INTO Students VALUES ('345','Craig','Davis',3.5,500);
INSERT INTO Students VALUES ('456','Doris','Roberts',3.9,1000);
INSERT INTO Students VALUES ('543','Craig','Wilder',3.4,2000);
INSERT INTO Students VALUES ('567','Edward','Norton',2.9,2000);
INSERT INTO Students VALUES ('654','Amy','Cooper',3.9,1000);
INSERT INTO Students VALUES ('678','Fay','Laurence',3.8,200);
INSERT INTO Students VALUES ('765','Jay','Farlong',2.9,1500);
INSERT INTO Students VALUES ('789','Gary','Oldman',3.4,800);
INSERT INTO Students VALUES ('876','Irene','Lopez',3.9,400);
INSERT INTO Students VALUES ('987','Helen','Karlson',3.7,800);

INSERT INTO College VALUES ('Berkeley','CA',36000);
INSERT INTO College VALUES ('Cornell','NY',21000);
INSERT INTO College VALUES ('MIT','MA',10000);
INSERT INTO College VALUES ('Stanford','CA',15000);

INSERT INTO Applies VALUES ('123','Berkeley','CS','1');
INSERT INTO Applies VALUES ('123','Cornell','EE','1');
INSERT INTO Applies (sid, college, major, desicion) VALUES ('123','Stanford','CS','1');
INSERT INTO Applies VALUES ('123','Stanford','EE','0');
INSERT INTO Applies VALUES ('234','Berkeley','biology','0');
INSERT INTO Applies VALUES ('345','Cornell','bioengineering','0');
INSERT INTO Applies VALUES ('345','Cornell','CS','1');
INSERT INTO Applies VALUES ('345','Cornell','EE','0');
INSERT INTO Applies VALUES ('345','MIT','bioengineering','1');
INSERT INTO Applies VALUES ('543','MIT','CS','0');
INSERT INTO Applies VALUES ('678','Stanford','history','1');
INSERT INTO Applies VALUES ('765','Cornell','history','0');
INSERT INTO Applies VALUES ('765','Cornell','psychology','1');
INSERT INTO Applies VALUES ('765','Stanford','history','1');
INSERT INTO Applies VALUES ('876','MIT','biology','1');
INSERT INTO Applies VALUES ('876','MIT','marine biology','0');
INSERT INTO Applies VALUES ('876','Stanford','CS','0');
INSERT INTO Applies VALUES ('987','Berkeley','CS','1');
INSERT INTO Applies VALUES ('987','Stanford','CS','1');

-- Exercise 40
INSERT INTO College Values ('UIB', 'IB', 11500);

-- Exercise 41
INSERT INTO APPLIES (sid, COLLEGE, MAJOR, desicion)
SELECT s.id, 'UIB', 'IB', NULL
FROM STUDENTS s
WHERE s.id NOT IN (SELECT sid FROM APPLIES);

-- Exercise 42
INSERT INTO APPLIES (sid, COLLEGE, MAJOR, desicion)
SELECT s.id, 'UIB', 'EE', NULL
FROM Students.s
WHERE s.id IN (
    SELECT a.sid
    FROM APPLIES a
    WHERE a.MAJOR = 'EE' AND a.DECISION = 0
) AND s.id NOT IN (
    SELECT SID
    FROM APPLIES
    WHERE MAJOR = 'EE'
);

-- Exercise 43
DELETE STUDENTS
WHERE 

-- Exercise 44
DELETE COLLEGE
WHERE 