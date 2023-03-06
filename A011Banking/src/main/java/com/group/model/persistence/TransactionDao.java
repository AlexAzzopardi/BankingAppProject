package com.group.model.persistence;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.group.entity.Transaction;

@Repository
public interface TransactionDao extends JpaRepository<Transaction, Integer>{
	
	@Transactional
	@Modifying
	@Query("Delete Transaction where userName=:userName")
	public void deleteAllTransactionByUserName(@Param("userName") String userName);
	public List<Transaction> getAllTransactionByUserName(String userName);
	
}
