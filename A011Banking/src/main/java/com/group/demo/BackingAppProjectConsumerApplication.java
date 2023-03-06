package com.group.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.group.entity.Account;
import com.group.entity.User;
import com.group.model.persistence.AccountDao;
import com.group.model.persistence.UserDao;

@SpringBootApplication(scanBasePackages = "com.group")
@EntityScan(basePackages = "com.group.entity")
@EnableJpaRepositories(basePackages = "com.group.model.persistence")
public class BackingAppProjectConsumerApplication implements CommandLineRunner{
	
	@Autowired
	AccountDao accountDao;
	@Autowired
	UserDao userDao;
	
	public static void main(String[] args) {
		SpringApplication.run(BackingAppProjectConsumerApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		User user1 = new User("Alex", "a");
		userDao.save(user1);
		
		Account account1 = new Account(1, "Savings", 2500, "Alex");
		accountDao.save(account1);
		Account account2 = new Account(2, "Checking", 1000, "Alex");
		accountDao.save(account2);
		
	}
}
