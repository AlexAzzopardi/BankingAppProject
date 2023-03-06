package com.group.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Transactions")
public class Transaction {

	@Id
	private int transactionId;
	private int debitAccountId;
	private int creditAccountId;
	private double amount;
	private String userName;
	
}
