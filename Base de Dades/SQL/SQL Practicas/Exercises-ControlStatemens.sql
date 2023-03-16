DECLARE
    type t_array_type is table of ORDER_ITEMS%ROWTYPE;
    t_array t_array_type;
    l_value number(20,2);
BEGIN
    SELECT * BULK collect into t_array from ORDER_ITEMS
    where PRODUCT_ID = 1 or PRODUCT_ID = 7 or PRODUCT_ID = 34;

    FOR row in t_array.first..t_array.last LOOP
       l_value := t_array(row).PRODUCT_ID;
       DBMS_OUTPUT.PUT_LINE('PRODUCT_ID: ' || l_value);
    END LOOP;

END;