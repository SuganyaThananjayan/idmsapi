package com.dealersocket.idmsapi.repository;

import com.dealersocket.idmsapi.model.IdmsAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface IdmsAccountRepository extends JpaRepository<IdmsAccount, Long> {
     Optional<IdmsAccount> findById(Long Id);
}