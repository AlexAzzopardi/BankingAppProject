package com.group.model.service;

import java.util.List;

import com.group.entity.Account;


public interface AccountService {

	public Account addAccount(Account account);
	public List<Account> getAllAccountsbyUserName(String userName);
	public String transferFunds(int debitAccountId, int creditAccountId, double amount);
	public double getBalanceById(int accountId);
	public int getNumberOfAccounts();

}
