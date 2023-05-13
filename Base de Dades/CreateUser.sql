-- create new user
CREATE USER youruser IDENTIFIED BY yourpassword;

-- grant priviledges
GRANT CONNECT, RESOURCE, DBA TO youruser;