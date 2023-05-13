-- create new user
CREATE USER potatomaster IDENTIFIED BY 1234;

-- grant priviledges
GRANT CONNECT, RESOURCE, DBA TO potatomaster;