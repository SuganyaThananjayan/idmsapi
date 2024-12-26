package com.dealersocket.idmsapi.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//import javax.persistence.Entity;
//import javax.persistence.Id;
@Entity
@JsonIgnoreProperties({"contractSalesPrice","AcctType","SalesGroupPerson1ID”,”SalesGroupPerson1Commission","SalesGroupPerson2ID","SalesGroupPerson2Commission","SalesManagerID","ContractCashDown","ContractTotalDeferredDown","CurDownPayBal","AcctCurEstimatedPayoff","ContractTotalFees","ContractTotalTaxes","ContractTotalTradeIn","ContractTotalTradeInPayoff","Borrower1State","ContractTotalProductCost","ContractTotalProductSalesPrice","ContractAmountFinanced","WarrantyPrice","WarrantyContractCost","OptionalVSIPrice","OptionalVSICost","TotalAcctCollected","PrimaryLoanTotalMiscFeeAdjusted","ContractDate","CollateralStockNumber","AcctCurTotalBalance","Borrower1FullName","LenderDesc","CollateralBuyerID","CollateralDaysOnLot","CollateralAcquiredDate","AcctStatus","CollateralVIN","Borrower1SSN","Borrower2SSN","Borrower1Address1","Borrower2FullName","BookedDate","DealWorksheetID","Borrower1City","Borrower1Zipcode","Borrower2City","Borrower2AddressZipcode","AcctID","CurDueAmt","NumDaysSinceContractDate","ContractLoanRate","PrimaryLoanOrigTermInMonths","PrimaryLoanCSRegPaymentAmt","CollateralYearModel","CollateralMake","CollateralModel","PrimaryLoanCSPaymentFrequency","Borrower1FirstName","Borrower1LastName","Borrower2FirstName","Borrower2LastName","AcctLastPaidDate","CollateralAcquiredFromTypeID","CollateralTotalCost","DebtCancellationCost","DebtCancellation","SalesLocationName","SalesLocationDesc","PrimaryLoanCSPaymentsRemaininginMonths","ServiceContractCompanyName","GAPCompanyName","DebtCancellationCompanyName","Borrower1CellPhone"})
public class IdmsAccount {


    @Override
    public String toString() {
        return "IdmsAccount{" +
                "dealId =" + dealId +
                ", name='" + name + '\'' +
                '}';
    }



    public Long getDealId() {
        return dealId;
    }

    public void setDealId(Long dealId) {

        this.dealId = dealId;
    }

    @Id
    @JsonProperty("DealID")
    private Long dealId;
    @JsonProperty("LenderName")
    private String name; // Ensure this field exists for the query to work

    // Other fields, getters, and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

    }


}
