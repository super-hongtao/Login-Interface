package com.ftg.loginInterface.customer.repository;

import com.ftg.loginInterface.customer.pojo.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Integer> {
}
