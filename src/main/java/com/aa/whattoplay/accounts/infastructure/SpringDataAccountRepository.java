package com.aa.whattoplay.accounts.infastructure;

import com.aa.whattoplay.accounts.domain.AccountRepository;
import com.aa.whattoplay.accounts.infastructure.entities.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataAccountRepository extends AccountRepository, JpaRepository<AccountEntity, Long> {
}
