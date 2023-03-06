package com.group.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.entity.Account;
import com.group.model.persistence.AccountDao;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountDao accountDao;

	@Override
	public Account addAccount(Account account) {
		return accountDao.save(account);
	}

	@Override
	public List<Account> getAllAccountsbyUserName(String userName) {
		return accountDao.getAllAccountByUserName(userName);
	}

	@Override
	public String transferFunds(int debitAccountId, int creditAccountId, double amount) {
		if (amount <= 0)
			return "Can't Transfer That Amount!";
		if(debitAccountId != 0 && creditAccountId != 0 && debitAccountId != creditAccountId) {
			double currentDebitBalance = accountDao.getAccountByAccountId(debitAccountId).getBalance();
			double currentCreditBalance = accountDao.getAccountByAccountId(creditAccountId).getBalance();
			if(currentCreditBalance - amount >= 0) {
				accountDao.updateBalance(creditAccountId, currentCreditBalance - amount);
				accountDao.updateBalance(debitAccountId, currentDebitBalance + amount);
				return "Amount Transfered!";
			}
			else { return "Insufficient Funds"; }
		}
		else if(debitAccountId != 0 && debitAccountId != creditAccountId) {
			System.out.println("Test2");
			double currentDebitBalance = accountDao.getAccountByAccountId(debitAccountId).getBalance();
			accountDao.updateBalance(debitAccountId, currentDebitBalance + amount);
			return "Amount Transfered!";
		}
		else if(creditAccountId != 0 && debitAccountId != creditAccountId){
			System.out.println("Test3");
			double currentCreditBalance = accountDao.getAccountByAccountId(creditAccountId).getBalance();
			if(currentCreditBalance - amount >= 0) {
				accountDao.updateBalance(creditAccountId, currentCreditBalance - amount);
				return "Amount Transfered!";
			}
			else { return "Insufficient Funds!"; }
		}
		else { return "Can't Transfer Between These Two Accounts!"; }
	}
	
	@Override
	public double getBalanceById(int accountId) {
		return accountDao.getAccountByAccountId(accountId).getBalance();
	}

	@Override
	public int getNumberOfAccounts() {
		return (int)accountDao.count();
	}

	public void setAccountDao(AccountDao accountDao2) {
		accountDao = accountDao2;
		
	}

}
