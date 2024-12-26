package com.dealersocket.idmsapi.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "account_data")
public class AccountData {
    @Id
    @JsonProperty("AcctID")
    @Column(name = "AcctID")
    private String acctID; // Assuming AcctID is unique

    @JsonProperty("ContractSalesPrice")
    @Column(name = "ContractSalesPrice")
    private Double contractSalesPrice;

    @JsonProperty("AcctType")
    @Column(name = "AcctType")
    private String acctType;

    @JsonProperty("SalesGroupPerson1ID")
    @Column(name = "SalesGroupPerson1ID")
    private String salesGroupPerson1ID;

    @JsonProperty("ContractDate")
    @Column(name = "ContractDate")
    private String contractDate;

    @JsonProperty("CollateralStockNumber")
    @Column(name = "CollateralStockNumber")
    private String collateralStockNumber;

    @JsonProperty("CollateralYearModel")
    @Column(name = "CollateralYearModel")
    private String collateralYearModel;

    @JsonProperty("CollateralMake")
    @Column(name = "CollateralMake")
    private String collateralMake;

    @JsonProperty("CollateralModel")
    @Column(name = "CollateralModel")
    private String collateralModel;

    @JsonProperty("Borrower1FirstName")
    @Column(name = "Borrower1FirstName")
    private String borrower1FirstName;

    @JsonProperty("Borrower1LastName")
    @Column(name = "Borrower1LastName")
    private String borrower1LastName;

    public String getAcctID() {
        return acctID;
    }

    public void setAcctID(String acctID) {
        this.acctID = acctID;
    }

    public Double getContractSalesPrice() {
        return contractSalesPrice;
    }

    public void setContractSalesPrice(Double contractSalesPrice) {
        this.contractSalesPrice = contractSalesPrice;
    }

    public String getAcctType() {
        return acctType;
    }

    public void setAcctType(String acctType) {
        this.acctType = acctType;
    }

    public String getSalesGroupPerson1ID() {
        return salesGroupPerson1ID;
    }

    public void setSalesGroupPerson1ID(String salesGroupPerson1ID) {
        this.salesGroupPerson1ID = salesGroupPerson1ID;
    }

    public String getContractDate() {
        return contractDate;
    }

    public void setContractDate(String contractDate) {
        this.contractDate = contractDate;
    }

    public String getCollateralStockNumber() {
        return collateralStockNumber;
    }

    public void setCollateralStockNumber(String collateralStockNumber) {
        this.collateralStockNumber = collateralStockNumber;
    }

    public String getCollateralYearModel() {
        return collateralYearModel;
    }

    public void setCollateralYearModel(String collateralYearModel) {
        this.collateralYearModel = collateralYearModel;
    }

    public String getCollateralMake() {
        return collateralMake;
    }

    public void setCollateralMake(String collateralMake) {
        this.collateralMake = collateralMake;
    }

    public String getCollateralModel() {
        return collateralModel;
    }

    public void setCollateralModel(String collateralModel) {
        this.collateralModel = collateralModel;
    }

    public String getBorrower1FirstName() {
        return borrower1FirstName;
    }

    public void setBorrower1FirstName(String borrower1FirstName) {
        this.borrower1FirstName = borrower1FirstName;
    }

    public String getBorrower1LastName() {
        return borrower1LastName;
    }

    public void setBorrower1LastName(String borrower1LastName) {
        this.borrower1LastName = borrower1LastName;
    }

    @Override
    public String toString() {
        return "AccountData{" +
                "acctID='" + acctID + '\'' +
                ", contractSalesPrice=" + contractSalesPrice +
                ", acctType='" + acctType + '\'' +
                ", salesGroupPerson1ID='" + salesGroupPerson1ID + '\'' +
                ", contractDate='" + contractDate + '\'' +
                ", collateralStockNumber='" + collateralStockNumber + '\'' +
                ", collateralYearModel='" + collateralYearModel + '\'' +
                ", collateralMake='" + collateralMake + '\'' +
                ", collateralModel='" + collateralModel + '\'' +
                ", borrower1FirstName='" + borrower1FirstName + '\'' +
                ", borrower1LastName='" + borrower1LastName + '\'' +
                '}';
    }

    // Getters and setters omitted for brevity
}

