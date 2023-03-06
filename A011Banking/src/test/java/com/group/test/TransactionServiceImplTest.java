package com.group.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.group.entity.Transaction;
import com.group.model.persistence.TransactionDao;
import com.group.model.service.TransactionServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceImplTest {

	@InjectMocks
	private TransactionServiceImpl transactionService;
	
	@Mock
	private TransactionDao transactionDao;
	
	private Transaction testTransaction;
	
	@Before
	public void setUp() {
		testTransaction = new Transaction(1, 1, 2, 500, "Test User");
	}
	
	@Test
	public void testAddTransaction() {
		when(transactionDao.save(testTransaction)).thenReturn(testTransaction);
		
		Transaction addedTransaction = transactionService.addTransaction(testTransaction);
		
		assertNotNull(addedTransaction);
		assertEquals(testTransaction.getTransactionId(), addedTransaction.getTransactionId());
		assertEquals(testTransaction.getDebitAccountId(), addedTransaction.getDebitAccountId());
		assertEquals(testTransaction.getCreditAccountId(), addedTransaction.getCreditAccountId());
		assertEquals(testTransaction.getAmount(), addedTransaction.getAmount(), 0);
		assertEquals(testTransaction.getUserName(), addedTransaction.getUserName());
	}
	
	@Test
	public void testGetTransactionById() {
		when(transactionDao.findById(testTransaction.getTransactionId())).thenReturn(Optional.of(testTransaction));
		
		Transaction foundTransaction = transactionService.getTransactionById(testTransaction.getTransactionId());
		
		assertNotNull(foundTransaction);
		assertEquals(testTransaction.getTransactionId(), foundTransaction.getTransactionId());
		assertEquals(testTransaction.getDebitAccountId(), foundTransaction.getDebitAccountId());
		assertEquals(testTransaction.getCreditAccountId(), foundTransaction.getCreditAccountId());
		assertEquals(testTransaction.getAmount(), foundTransaction.getAmount(), 0);
		assertEquals(testTransaction.getUserName(), foundTransaction.getUserName());
	}
	
	@Test
	public void testGetAllTransactionsByUserName() {
		List<Transaction> testTransactions = new ArrayList<Transaction>();
		testTransactions.add(testTransaction);
		
		when(transactionDao.getAllTransactionByUserName(testTransaction.getUserName())).thenReturn(testTransactions);
		
		List<Transaction> foundTransactions = transactionService.getAllTransactionsByUserName(testTransaction.getUserName());
		
		assertNotNull(foundTransactions);
		assertEquals(testTransactions.size(), foundTransactions.size());
		
		Transaction foundTransaction = foundTransactions.get(0);
		assertEquals(testTransaction.getTransactionId(), foundTransaction.getTransactionId());
		assertEquals(testTransaction.getDebitAccountId(), foundTransaction.getDebitAccountId());
		assertEquals(testTransaction.getCreditAccountId(), foundTransaction.getCreditAccountId());
		assertEquals(testTransaction.getAmount(), foundTransaction.getAmount(), 0);
		assertEquals(testTransaction.getUserName(), foundTransaction.getUserName());
	}
	
	@Test
	public void testRemoveAllTransactionsByUserName() {
		transactionService.removeAllTransactionsByUserName(testTransaction.getUserName());
	}
	
	@Test
	public void testGetNumberOfTransactions() {
		when(transactionDao.count()).thenReturn(1L);
		int numTransactions = transactionService.getNumberOfTransactions();
		assertEquals(1, numTransactions);
	}

}
