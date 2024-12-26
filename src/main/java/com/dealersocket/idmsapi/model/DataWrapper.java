package com.dealersocket.idmsapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DataWrapper {


    @Override
    public String toString() {
        return "DataWrapper{" +
                "row=" + row +
                '}';
    }

    @JsonProperty("Row")
        private AccountData row;  // The "row" field contains the actual account details

        // Getter and setter for row
        public AccountData getRow() {
            return row;
        }

        public void setRow(AccountData row) {
            this.row = row;
        }
    }
