CREATE TABLE IF NOT EXISTS account (
    acctId BIGINT PRIMARY KEY,
    contractSalesPrice DOUBLE,
    acctType VARCHAR(255),
    salesGroupPerson1ID BIGINT,
    contractDate DATE,
    collateralStockNumber VARCHAR(255),
    collateralYearModel INT,
    collateralMake VARCHAR(255),
    collateralModel VARCHAR(255),
    borrower1FirstName VARCHAR(255),
    borrower1LastName VARCHAR(255)
);

