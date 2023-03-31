DECLARE
    num Number;
BEGIN

    num := 2/0;
    
    EXCEPTION
        WHEN Zero_Divide THEN
            DBMS_output.Put_line('Error al dividir entre 0');

END;