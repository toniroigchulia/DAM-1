--Exercises SELECT-2

--1
SELECT CUSTOMERTABLE.name, CUSTOMERTABLE.code, VENDOR.name 
FROM CUSTOMERTABLE
INNER JOIN VENDOR
ON CUSTOMERTABLE.code = VENDOR.code
WHERE CUSTOMERTABLE.type = 'AA';

--2
SELECT CUSTOMERTABLE.name, CUSTOMERTABLE.PROVINCE, VENDOR.name
FROM CUSTOMERTABLE
INNER JOIN VENDOR
ON CUSTOMERTABLE.code = VENDOR.code;

--3
CREATE TABLE CATEGORY(
    cat_PK_category int,
    cat_begining int,
    cat_end int,
    
    PRIMARY KEY(cat_PK_category)
);

CREATE SEQUENCE Cat_PK_categoria_squence
START WITH 1
Increment By 1
MINVALUE 1
MaxValue 13
cycle
CACHE 2;

CREATE SEQUENCE Cat_inici_squence
START WITH 1
Increment By 100000
MINVALUE 1
MaxValue 1200001
CYCLE
CACHE 2;

CREATE SEQUENCE Cat_fi_squence
START WITH 100000
Increment By 100000
MINVALUE 1
MaxValue 1300000
cycle
CACHE 2;


SELECT *
FROM CATEGORY;

INSERT INTO CATEGORY (cat_PK_category, cat_begining, cat_end)
VALUES(Cat_PK_categoria_squence.nextVal, Cat_inici_squence.nextval, Cat_fi_squence.nextVal);

--4
SELECT customers