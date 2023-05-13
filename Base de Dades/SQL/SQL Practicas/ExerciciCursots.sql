DECLARE
    CURSOR C_COUNT(SALESMAN_ID NUMBER;
    N_RECORDS NUMBER) IS (
        SELECT
            SALESMAN_ID
        FROM
            ORDERS
        WHERE
            STATUS = "Shiped";
    );
BEGIN
    OPEN C_COUNT();
    DBMS_OUTPUT.PUT_LINE(C_COUNT);
END;