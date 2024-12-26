package com.dealersocket.idmsapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GetResponse {
    @JsonProperty("Status")
   private String Status;
    @JsonProperty("Message")
    private String Message;
    @JsonProperty("TotalRecords")
    private String TotalRecords;
    @JsonProperty("TotalPages")
    private String TotalPages;
    @JsonProperty("PageNumber")
    private String PageNumber;
    @JsonProperty("BeginningPage")
    private String BeginningPage;

    @Override
    public String toString() {
        return "GetResponse{" +
                "Status='" + Status + '\'' +
                ", Message='" + Message + '\'' +
                ", TotalRecords='" + TotalRecords + '\'' +
                ", TotalPages='" + TotalPages + '\'' +
                ", PageNumber='" + PageNumber + '\'' +
                ", BeginningPage='" + BeginningPage + '\'' +
                ", EndingPage='" + EndingPage + '\'' +
                ", data=" + data +
                '}';
    }

    private String EndingPage;
    @JsonProperty("Data")  // Map the "data" field in the response to this property
    private List<DataWrapper> data;


    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getTotalRecords() {
        return TotalRecords;
    }

    public void setTotalRecords(String totalRecords) {
        TotalRecords = totalRecords;
    }

    public String getTotalPages() {
        return TotalPages;
    }

    public void setTotalPages(String totalPages) {
        TotalPages = totalPages;
    }

    public String getPageNumber() {
        return PageNumber;
    }

    public void setPageNumber(String pageNumber) {
        PageNumber = pageNumber;
    }

    public String getBeginningPage() {
        return BeginningPage;
    }

    public void setBeginningPage(String beginningPage) {
        BeginningPage = beginningPage;
    }

    public String getEndingPage() {
        return EndingPage;
    }

    public void setEndingPage(String endingPage) {
        EndingPage = endingPage;
    }

    public List<DataWrapper> getData() {
        return data;
    }

    public void setData(List<DataWrapper> data) {
        this.data = data;
    }
}
