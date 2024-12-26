package com.dealersocket.idmsapi.repository;

import com.dealersocket.idmsapi.model.AccountData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountDataRepository extends JpaRepository<AccountData, String> {
    boolean existsByAcctID(String acctID);
    // Method to check for duplicates based on AcctID
}