package com.group.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.entity.Transaction;
import com.group.model.persistence.TransactionDao;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	TransactionDao transactionDao;

	@Override
	public Transaction addTransaction(Transaction transaction) {
		return transactionDao.save(transaction);
	}
	
	@Override
	public Transaction getTransactionById(int transactionId) {
		return transactionDao.findById(transactionId).orElse(null);
	}
	
	@Override
	public List<Transaction> getAllTransactionsByUserName(String userName) {
		return transactionDao.getAllTransactionByUserName(userName);
	}

	@Override
	public void removeAllTransactionsByUserName(String userName) {
		transactionDao.deleteAllTransactionByUserName(userName);
	}

	@Override
	public int getNumberOfTransactions() {
		return (int)transactionDao.count();
	}

}
