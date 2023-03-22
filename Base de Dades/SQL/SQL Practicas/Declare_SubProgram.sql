
DECLARE
    email VARCHAR2(100);

    FUNCTION get_email (int_employee_ID  NUMBER) RETURN VARCHAR2 IS
    l_email VARCHAR2(100);
    
    BEGIN
        SELECT Email INTO l_email
        FROM Employees
        WHERE Employee_ID = int_employee_ID;
        
        RETURN l_email;
    END;
    
BEGIN
    
    email := get_email(107);
    DBMS_Output.Put_Line(email);
    
End ;