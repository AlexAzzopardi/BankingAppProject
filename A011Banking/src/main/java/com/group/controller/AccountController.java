package com.group.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.group.entity.Account;
import com.group.entity.Transaction;
import com.group.model.service.AccountService;
import com.group.model.service.TransactionService;

@Controller
public class AccountController {
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	UserController userController;
	
	@Autowired
	TransactionService tranactionService;
	
	@RequestMapping("/mainMenu")
	public ModelAndView mainMenuController() {
		return new ModelAndView("MainMenu");
	}
	
	@RequestMapping("/viewAllAccounts")
	public ModelAndView viewAllAccountsController() {
		return new ModelAndView("ViewAllAccounts", "accounts", accountService.getAllAccountsbyUserName(userController.currentUser));
	}
	
	@RequestMapping("/addAccountPage")
	public ModelAndView addAccountPageController() {
		return new ModelAndView("AddAccount", "account", new Account());
	}
	
	@RequestMapping("/addAccount")
	public ModelAndView addAccountController(@ModelAttribute("account") Account account) {
		if (account.getBalance() >= 0 ) 
			accountService.addAccount(new Account(accountService.getNumberOfAccounts() + 1, account.getType(), account.getBalance(), userController.currentUser));
		return addAccountPageController();
	}
	
	@RequestMapping("/transfer")
	public ModelAndView transferController() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("transaction", new Transaction());
		modelAndView.addObject("accounts", accountService.getAllAccountsbyUserName(userController.currentUser));
		modelAndView.setViewName("Transfer");
		return modelAndView;
	}
	
	@RequestMapping("/transfered")
	public ModelAndView transferedController(@ModelAttribute("transaction") Transaction transaction) {
		String transferRequest = accountService.transferFunds(transaction.getDebitAccountId(), transaction.getCreditAccountId(), transaction.getAmount());
		ModelAndView modelAndView = transferController();
		if (transferRequest == "Amount Transfered!") {
			modelAndView.addObject("Transfered", transferRequest);
			tranactionService.addTransaction(new Transaction(tranactionService.getNumberOfTransactions() + 1, transaction.getDebitAccountId(), transaction.getCreditAccountId(), transaction.getAmount(), userController.currentUser));
		}
		else 
			modelAndView.addObject("NotTransfered", transferRequest);
		return modelAndView;
	}
	
	@RequestMapping("/transactionHistory")
	public ModelAndView viewTransactionHistoryController() {
		List<Transaction> transactions = tranactionService.getAllTransactionsByUserName(userController.currentUser);
		if (transactions != null) 
			return new ModelAndView("ViewAllTransactions", "transactions", transactions);
		else
			return new ModelAndView("MainMenu", "Message", "No Transaction Records Avaliable");
	}
	
	@RequestMapping("/deleteTransactions")
	public ModelAndView deleteTransactionsController() {
		tranactionService.removeAllTransactionsByUserName(userController.currentUser);
		return viewTransactionHistoryController();
	}
	
}
