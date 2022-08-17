package com.ominext.auth.repository;

import com.ominext.auth.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findFirstByMailAddress(String emailAddress);
}
