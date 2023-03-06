package com.group.model.service;

import java.util.List;

import com.group.entity.Account;
import com.group.entity.Transaction;

public interface TransactionService {

	public Transaction addTransaction(Transaction transaction);
	public Transaction getTransactionById(int transactionId);
	public List<Transaction> getAllTransactionsByUserName(String userName);
	public int getNumberOfTransactions();
	public void removeAllTransactionsByUserName(String userName);

}
