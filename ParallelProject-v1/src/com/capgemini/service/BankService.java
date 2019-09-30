//Service layer Interface

package com.capgemini.service;

import java.util.List;

import com.capgemini.model.Transaction;
import com.capgemini.model.User;
import com.capgemini.validation.PasswordValidation;
import com.capgemini.validation.UsernameException;

public interface BankService {

	public double showBalance(String username, String password); // To show balance

	public double deposit(String username, String password, double amount); // To deposit amount

	public double withdraw(String username, String password, double amount); // To withdraw money

	public boolean fundTransfer(String username, String password, double amount, String username1); // To transfer fund

	public List<Transaction> printTransaction(String username, String password); // For printing transaction details

	// Validation details

	public boolean validatePhone(String contact_no);

	public boolean validateBalance(double balance);

	public boolean validateUsername(String username) throws UsernameException;

	public boolean validatePassword(String password) throws PasswordValidation;

	public boolean createAccount(User user);

	public boolean loginCheck(String username, String password);

}
