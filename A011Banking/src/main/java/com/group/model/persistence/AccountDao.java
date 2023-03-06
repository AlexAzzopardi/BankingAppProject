package com.group.model.persistence;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.group.entity.Account;

@Repository
public interface AccountDao extends JpaRepository<Account, Integer>{
	
	@Transactional
	@Modifying
	@Query("Update Account set balance=:balance where accountId=:accountId")
	public void updateBalance(@Param("accountId") int accountId, @Param("balance") double balance);
	public Account getAccountByAccountId(int accountId);
	public List<Account> getAllAccountByUserName(String userName);
	
}
