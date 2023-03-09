/* EXAMEN SQL SEGON TRIMESTRE // ANTONI ROIG CHULIA */
INSERT INTO MEMBERS VALUES ('11111111Z', 'MMM', 'MMM', 'STREET 1', TO_DATE('12/02/22','DD/MM/YY'));
INSERT INTO MEMBERS VALUES ('22222222Z', 'CCC', 'CCC', 'STREET 2', TO_DATE('22/08/20','DD/MM/YY'));
INSERT INTO MEMBERS VALUES ('33333333Z', 'DDD', 'DDD', 'STREET 3', TO_DATE('01/12/19','DD/MM/YY'));
INSERT INTO MEMBERS VALUES ('44444444Z', 'AAA', 'AAA', 'STREET 4', TO_DATE('09/04/18','DD/MM/YY'));
INSERT INTO BOOKS VALUES ('1111', 'QAZ', 'Title 1', 'Author 1', 'Y');
INSERT INTO BOOKS VALUES ('2222', 'WSX', 'Title 2', 'Author 2', 'N');
INSERT INTO BOOKS VALUES ('3333', 'QAZ', 'Title 3', 'Author 3', 'Y');
INSERT INTO BOOKS VALUES ('4444', 'EDC', 'Title 4', 'Author 4', 'N');
INSERT INTO BOOKS VALUES ('5555', 'QAZ', 'Title 5', 'Author 5', 'N');
INSERT INTO BOOKS VALUES ('6666', 'QAZ', 'Title 6', 'Author 6', 'Y');
INSERT INTO BOOKS VALUES ('7777', 'WSX', 'Title 7', 'Author 7', 'Y');
INSERT INTO BOOKS VALUES ('8888', 'EDC', 'Title 8', 'Author 8', 'Y');
INSERT INTO BOOKS VALUES ('9999', 'QAZ', 'Title 9', 'Author 9', 'Y');
INSERT INTO BOOKS VALUES ('10101010', 'WSX', 'Title 10', 'Author 10', 'Y');
INSERT INTO BOOKS VALUES ('11111111', 'QAZ', 'Title 11', 'Author 11', 'Y');
INSERT INTO BOOKS VALUES ('12121212', 'EDC', 'Title 12', 'Author 12', 'Y');
INSERT INTO LOANS VALUES (1, '11111111Z', '2222', TO_DATE('13/02/22','DD/MM/YY'));
INSERT INTO LOANS VALUES (2, '22222222Z', '2222', TO_DATE('10/04/21','DD/MM/YY'));
INSERT INTO LOANS VALUES (3, '22222222Z', '3333', TO_DATE('01/12/20','DD/MM/YY'));
INSERT INTO LOANS VALUES (4, '33333333Z', '5555', TO_DATE('13/02/22','DD/MM/YY'));
INSERT INTO LOANS VALUES (5, '33333333Z', '6666', TO_DATE('08/01/21','DD/MM/YY'));
INSERT INTO LOANS VALUES (6, '33333333Z', '9999', TO_DATE('16/11/19','DD/MM/YY'));
INSERT INTO LOANS VALUES (7, '44444444Z', '4444', TO_DATE('13/02/19','DD/MM/YY'));
INSERT INTO LOANS VALUES (8, '44444444Z', '8888', TO_DATE('13/02/20','DD/MM/YY'));
INSERT INTO LOANS VALUES (9, '44444444Z', '4444', TO_DATE('13/02/20','DD/MM/YY'));
INSERT INTO LOANS VALUES (10, '44444444Z', '10101010', TO_DATE('13/02/19','DD/MM/YY'));


--EXERCICI 1
CREATE TABLE Members (

    id CHAR(9) NOT NULL,
    name varchar2(15),
    lastname VARCHAR2(20),
    addres VARCHAR2(30),
    admision DATE DEFAULT SYSDATE,
    
    PRIMARY KEY (id)
);

CREATE TABLE Books (

    code NUMBER(11) NOT NULL,
    signature CHAR(10),
    title VARCHAR2(40) UNIQUE,
    author VARCHAR2(40),
    available CHAR(1),
    
    PRIMARY KEY (code)
);

CREATE TABLE Loans (

    loan_id NUMBER(11) NOT NULL,
    member_id CHAR(9) NOT NULL,
    book NUMBER(11) NOT NULL,
    loan_date DATE NOT NULL,
    
    PRIMARY KEY (loan_id),
    FOREIGN KEY (member_id) REFERENCES Members(id),
    FOREIGN KEY (book) REFERENCES Books(code)

);

    --1.A
ALTER TABLE Books
ALTER COLUMN available char(1) CHECK (available = 'Y' OR available = 'N');

    --1.B
Alter Table Loans
Alter Colum member_id char(9) NOT Null REFERENCES Members ON DELETE CASCADE;

    --1.C
Alter Table Members
Alter Column name varchar2(15) UNIQUE;

--EXERCICI 2
SELECT count(id), YEAR(ADMISION)
FROM MEMBERS
GROUP BY YEAR(admision);

--EXERCICI 3
SELECT books.title, books.signature, MEMBERS.name, MEMBERS.LASTNAME, loans.loan_date
FROM Loans
INNER JOIN Books
ON loans.book = books.code
INNER JOIN MEMBERS
ON loans.member_id = MEMBERS.id
ORDER BY books.title;

--EXERCICI 4
SELECT MEMBERS.name, MEMBERS.LASTNAME
FROM Loans
INNER JOIN MEMBERS
ON loans.member_id = MEMBERS.ID
WHERE MONTHS_BETWEEN(loans.loan_date, SYSDATE) > 14;

--EXERCICI 5
SELECT title
FROM books
WHERE code IN (SELECT MAX(book)
                FROM LOANS
                GROUP BY book);

--EXERCICI 6
SELECT MEMBERS.NAME
FROM MEMBERS
WHERE MEMBERS.id = (SELECT member_id
                    FROM Loans
                    WHERE count(book) > 2);

--EXERCICI 7
UPDATE books
SET avaliable = 'Y'
WHERE EXISTS
(SELECT loans.book FROM Loans WHERE loan_date IS NUll);


--EXERCICI 8
SELECT books.code, books.title
FROM books
LEFT JOIN loans ON books.name = loans.book
WHERE loans.book IS NULL;