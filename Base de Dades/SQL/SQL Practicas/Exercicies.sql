DECLARE
    pais_name varchar2(10) := 'Spain';
    pais_ID varchar2(10) := 'Sp';
    region_pais_ID number;
BEGIN
    SELECT REGION_ID INTO region_pais_ID from REGIONS WHERE REGION_NAME = 'Europe';
    INSERT INTO COUNTRIES VALUES(pais_ID, pais_name, region_pais_ID); 

    DBMS_OUTPUT.PUT_LINE(pais_name);
    DBMS_OUTPUT.PUT_LINE(pais_ID);
    DBMS_OUTPUT.PUT_LINE(region_pais_ID);
END;


