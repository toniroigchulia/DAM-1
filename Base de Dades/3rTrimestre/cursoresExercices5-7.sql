--Exercice 5
    --Part A
CREATE OR REPLACE PROCEDURE UpdateCustomerPurchases(CustomerCode IN NUMBER) AS
  TotalPurchases NUMBER;

BEGIN

  SELECT SUM(Amount) INTO TotalPurchases
  FROM Purchase
  WHERE CustomerCode = CustomerCode;

  UPDATE Customers
  SET Purchases = TotalPurchases
  WHERE CustomerCode = CustomerCode;

  DBMS_OUTPUT.PUT_LINE('Customer Code: ' || CustomerCode || ' - Purchases: ' || TotalPurchases);

END;

    --Part B
CREATE OR REPLACE PROCEDURE UpdateAllCustomerPurchases AS
  CustomerCode NUMBER;
  TotalPurchases NUMBER;

  CURSOR customerCursor IS
    SELECT CustomerCode
    FROM Customers;
BEGIN

  OPEN customerCursor;
  FETCH customerCursor INTO CustomerCode;

  WHILE customerCursor%FOUND LOOP

    SELECT SUM(Amount) INTO TotalPurchases
    FROM Purchase
    WHERE CustomerCode = CustomerCode;

    UPDATE Customers
    SET Purchases = TotalPurchases
    WHERE CustomerCode = CustomerCode;

    DBMS_OUTPUT.PUT_LINE('Customer Code: ' || CustomerCode || ' - Purchases: ' || TotalPurchases);

    FETCH customerCursor INTO CustomerCode;
  END LOOP;

  CLOSE customerCursor;

END;

--Exercice 6
    --Part A
CREATE OR REPLACE PROCEDURE UpdateTotalAmountForProvince(ProvinceCode IN NUMBER) AS
BEGIN

  UPDATE Purchases
  SET Total_Amount = (
      SELECT SUM(Purchase.Amount)
      FROM Customers
      JOIN Purchase ON Customers.CustomerCode = Purchase.CustomerCode
      WHERE Customers.ProvinceCode = ProvinceCode
  )
  WHERE EXISTS (
      SELECT 1
      FROM Customers
      JOIN Purchase ON Customers.CustomerCode = Purchase.CustomerCode
      WHERE Customers.ProvinceCode = ProvinceCode
        AND Purchases.CustomerCode = Purchase.CustomerCode
  );

  SELECT ProvinceCode, Name, Total_Amount
  FROM Purchases
  WHERE ProvinceCode = ProvinceCode;

END;
    --Part B
        --i
CREATE OR REPLACE PROCEDURE UpdateAllTotalAmount AS
BEGIN

  UPDATE Purchases
  SET Total_Amount = (
      SELECT SUM(Purchase.Amount)
      FROM Customers
      JOIN Purchase ON Customers.CustomerCode = Purchase.CustomerCode
      WHERE Customers.ProvinceCode = Purchases.ProvinceCode
  );

  SELECT ProvinceCode, Name, Total_Amount
  FROM Purchases;

END;

        --ii
CREATE OR REPLACE PROCEDURE UpdateAllTotalAmountUsingProcedure AS
  ProvinceCode NUMBER;

  CURSOR provinceCursor IS
    SELECT DISTINCT ProvinceCode
    FROM Customers;
BEGIN
  OPEN provinceCursor;
  FETCH provinceCursor INTO ProvinceCode;

  WHILE provinceCursor%FOUND LOOP

    UpdateTotalAmountForProvince(ProvinceCode);

    FETCH provinceCursor INTO ProvinceCode;
  END LOOP;

  CLOSE provinceCursor;

END;

--Exercice 7
CREATE OR REPLACE PROCEDURE InsertVendorsByProvince(ProvinceCode IN NUMBER) AS
BEGIN
  -- Create the VendorsProvince table if it doesn't exist
  EXECUTE IMMEDIATE 'CREATE TABLE VendorsProvince (
    Code NUMBER,
    Name VARCHAR2(100),
    Degree VARCHAR2(100),
    ProvinceName VARCHAR2(100)
  )';

  INSERT INTO VendorsProvince (Code, Name, Degree, ProvinceName)
  SELECT Vendors.Code, Vendors.Name, Vendors.Degree, Provinces.Name
  FROM Vendors
  JOIN VendorsProvince ON Vendors.Code = VendorsProvince.Code
  JOIN Provinces ON Provinces.ProvinceCode = VendorsProvince.ProvinceCode
  WHERE Provinces.ProvinceCode = ProvinceCode;

  FOR vendor IN (SELECT * FROM VendorsProvince) LOOP
    DBMS_OUTPUT.PUT_LINE('Vendor Code: ' || vendor.Code || ', Name: ' || vendor.Name ||
                         ', Degree: ' || vendor.Degree || ', Province: ' || vendor.ProvinceName);
  END LOOP;
END;