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
);


SELECT id, name, surname, MARK
FROM STUDENTS
WHERE mark > 3.6;