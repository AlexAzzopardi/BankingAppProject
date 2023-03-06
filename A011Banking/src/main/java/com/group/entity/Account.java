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
@Table(name = "Accounts")
public class Account {

	@Id
	private int accountId;
	private String type;
	private double balance;
	private String userName;
	
}
