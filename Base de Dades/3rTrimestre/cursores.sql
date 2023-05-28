/*Ex2*/
CREATE OR REPLACE PROCEDURE CHANGE_TAX_RATE(p_property_id IN NUMBER, p_new_rate IN NUMBER) IS
BEGIN
  
  CURSOR property_cursor IS
    SELECT property_id, tax_rate
    FROM properties
    WHERE property_id = p_property_id;

  v_property_exists NUMBER := 0;
  v_property_id properties.property_id%TYPE;
  v_tax_rate properties.tax_rate%TYPE;
  
BEGIN

  SELECT COUNT(*)
  INTO v_property_exists
  FROM properties
  WHERE property_id = p_property_id;

  IF v_property_exists = 0 THEN
    
    DBMS_OUTPUT.PUT_LINE('La propiedad no existe');
    
  ELSE
    
    OPEN property_cursor;
    
    LOOP
    
      FETCH property_cursor INTO v_property_id, v_tax_rate;
      
      EXIT WHEN property_cursor%NOTFOUND;
      
      IF v_tax_rate = p_new_rate THEN
        
        DBMS_OUTPUT.PUT_LINE('La taxa ya es igual a la nueva taxa');
        
      ELSE
    
        UPDATE properties
        SET tax_rate = p_new_rate
        WHERE CURRENT OF property_cursor;

        DBMS_OUTPUT.PUT_LINE('Se ha aplicado la nueva taxa');
      END IF;
      
    END LOOP;
    CLOSE property_cursor;
  END IF;    
END;

/*Ex3*/
CREATE TABLE EmployeeTable (
  department_code VARCHAR(10),
  employee_name VARCHAR(50)
);

CREATE OR REPLACE PROCEDURE InsertEmployeesByDepartment(p_dept_code IN VARCHAR2)
IS
  
  emp_name VARCHAR2(50);
BEGIN

  IF NOT EXISTS (SELECT 1 FROM YourDepartmentTable WHERE department_code = p_dept_code) THEN
    DBMS_OUTPUT.PUT_LINE('El codigo ed departamento no existe');
  ELSE

    CURSOR emp_cursor IS
      SELECT employee_name FROM YourEmployeeTable WHERE department_code = p_dept_code;
  
    
    FOR emp_rec IN emp_cursor LOOP
      emp_name := emp_rec.employee_name;
      

      INSERT INTO EmployeeTable (department_code, employee_name) VALUES (p_dept_code, emp_name);
    END LOOP;
  
    DBMS_OUTPUT.PUT_LINE('Se ha insertado el nombre del empleado');
  END IF;
END;

BEGIN
    InsertEmployeesByDepartment('D001');
END;


/*Ex4*/
CREATE TABLE NEW_REGISTRY (
  registry_reference VARCHAR2(50),
  street_name VARCHAR2(50),
  street_number VARCHAR2(10)
);


CREATE OR REPLACE PROCEDURE UPDATE_REGISTRY(p_citizen_code NUMBER) AS

  CURSOR property_cursor IS
    SELECT owner_code, street_name, street_number
    FROM LANDREGISTRY
    WHERE owner_code = p_citizen_code;

  v_owner_code NUMBER;
  v_street_name VARCHAR2(50);
  v_street_number VARCHAR2(10);
  
BEGIN
  
  OPEN property_cursor;

  LOOP
    FETCH property_cursor INTO v_owner_code, v_street_name, v_street_number;
    EXIT WHEN property_cursor%NOTFOUND;
    
    INSERT INTO NEW_REGISTRY(registry_reference, street_name, street_number)
    VALUES (v_owner_code, v_street_name, v_street_number);
    
    DBMS_OUTPUT.PUT_LINE('Actualizar ' || v_owner_code || ', ' || v_street_name || ', ' || v_street_number);
  END LOOP;
  
  CLOSE property_cursor;

  DBMS_OUTPUT.PUT_LINE('Actualizacion del reporte: ');
  FOR update_row IN (SELECT * FROM NEW_REGISTRY) LOOP
    DBMS_OUTPUT.PUT_LINE(update_row.registry_reference || ', ' || update_row.street_name || ', ' || update_row.street_number);
  END LOOP;
END;

BEGIN
  UPDATE_REGISTRY(12345); 
END;