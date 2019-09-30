package com.capgemini.service;

import java.util.List;

import com.capgemini.dao.BankApplicationDao;
import com.capgemini.dao.BankApplicationDaoImpl;
import com.capgemini.model.Transaction;
import com.capgemini.model.User;
import com.capgemini.validation.PasswordValidation;
import com.capgemini.validation.UsernameException;

public class BankServiceImpl implements BankService {

	BankApplicationDao dao = new BankApplicationDaoImpl();

	@Override
	public double deposit(String username, String password, double amount) {

		double dep = dao.amt_deposit(username, password, amount);
		return dep;
	}

	@Override
	public double withdraw(String username, String password, double amount) {

		double with = dao.amt_withdraw(username, password, amount);
		return with;
	}

	@Override
	public boolean fundTransfer(String username, String password, double amount, String username1) {

		boolean result = dao.fund_Transfer(username, password, amount, username1);
		return result;
	}

	@Override
	public boolean createAccount(User user) {

		boolean result = dao.createUserAccount(user);
		return result;
	}

	@Override
	public double showBalance(String username, String password) {

		double balance = dao.get_Balance(username, password);
		return balance;
	}

	@Override
	public List<Transaction> printTransaction(String username, String password) {
		List<Transaction> transaction = dao.get_Transaction(username, password);
		return transaction;
	}

	public boolean validatePhone(String contact_no ) {
		
		boolean flag = false;
		char ch;
		if(contact_no.length()==10) {
		for (int i = 0; i < contact_no.length(); i++) {
			ch = contact_no.charAt(i);
			if (ch >= '1' && ch <= '9')
				flag = true;
		
		}
		
		
	}
		return flag;

}
	public boolean validateBalance(double balance) {
		
		boolean flag = true;
		if(balance<0)
			flag = false;
		return flag;
	}
	
	
	public  boolean validateUsername(String username) throws UsernameException {
		
		boolean flag = false;
		if (username.length() >= 6 && username.length() <= 12 && !username.contains(" "))

		{
			System.out.println("valid username");
		} else {
			flag = true;
			UsernameException u = new UsernameException(username);
			throw u;
		}
		return flag;
	}

	public  boolean validatePassword(String password) throws PasswordValidation {
		boolean flag = false;
		if (password.length() >= 5 && password.length() <= 10) {
			int flag1 = 0;
			int flag2 = 0;
			char ch;
			for (int i = 0; i < password.length(); i++) {
				ch = password.charAt(i);
				if (ch >= '1' && ch <= '9')
					flag1 = 1;
				if (ch >= 33 && ch <= 47 || ch >= 56 && ch <= 64 || ch >= 91 && ch <= 96 || ch >= 123 && ch <= 126)
					flag2 = 1;
			}
			if (flag1 == 1 && flag2 == 1)
				System.out.println("valid password");

		} else {
			

			PasswordValidation v = new PasswordValidation(password);
			flag = true;
			throw v;
		}
		return flag;

	}

	@Override
	public boolean loginCheck(String username, String password) {
		boolean res=dao.loginValidation(username, password);
		return res;
	}

}
