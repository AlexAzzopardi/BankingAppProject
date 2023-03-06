package com.group.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.group.entity.Account;
import com.group.model.persistence.AccountDao;
import com.group.model.service.AccountServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceImplTest {

	@InjectMocks
	private AccountServiceImpl accountService;

	@Mock
	private AccountDao accountDao;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testAddAccount() {
		Account account = new Account(1, "Test Type", 1000, "Test User");

		Mockito.when(accountDao.save(Mockito.any(Account.class))).thenReturn(account);

		Account savedAccount = accountService.addAccount(account);
		assertEquals(account, savedAccount);
	}

	@Test
	public void testGetAllAccountsbyUserName() {
		Account account1 = new Account(1, "Test Type", 1000, "Test User");
		Account account2 = new Account(2, "Test Type", 2000, "Test User");

		List<Account> accountList = List.of(account1, account2);

		Mockito.when(accountDao.getAllAccountByUserName(Mockito.anyString())).thenReturn(accountList);

		List<Account> returnedAccountList = accountService.getAllAccountsbyUserName("Test User");
		assertEquals(accountList, returnedAccountList);
	}
		
	@Test
	public void testTransferFunds() {
		Account account1 = new Account(1, "Test Type", 1000, "Test User");
		Account account2 = new Account(2, "Test Type", 2000, "Test User");

		Mockito.when(accountDao.getAccountByAccountId(1)).thenReturn(account1);
		Mockito.when(accountDao.getAccountByAccountId(2)).thenReturn(account2);
		
		accountService.transferFunds(2, 1, 500);

		assertEquals(1500.0, account1.getBalance(), 0.0);
		assertEquals(1500.0, account2.getBalance(), 0.0);
	}
}
	