//Dao layer interface

package com.capgemini.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.capgemini.model.Transaction;
import com.capgemini.model.User;

public class BankApplicationDaoImpl implements BankApplicationDao {

	double bal;

	// Declaration of ArrayList
	static ArrayList<User> list = new ArrayList<User>();
	ArrayList<Transaction> trans = new ArrayList<Transaction>();

	User u = new User();

	// Storing values in List
	static {

		User u1 = new User("shreya25", "2512@shreya", "8853088862", "shr.2512@gmailcom", "Shreya", 5000);
		list.add(u1);
		User u2 = new User("mansi_", "mansi1111", "9825371123", "mansi12@gmailcom", "Mansi", 1000);
		list.add(u2);
		User u3 = new User("manali2512", "25manali@", "8624186352", "manali97@gmailcom", "Manali", 1500);
		list.add(u3);
		User u4 = new User("shivani25", "shi56vani", "7624980376", "shivani@gmailcom", "Shivani", 10000);
		list.add(u4);
		User u5 = new User("rohit96", "rohit0101", "9162840923", "rohit1221@gmailcom", "Rohit", 2000);
		list.add(u5);
	}

	// Implementing methods of DAO interface by overriding
	
	//Method to retrieve balance 
	@Override
	public double get_Balance(String username, String password) {

		boolean flag = false;
		Iterator<User> itr = list.iterator();

		while (itr.hasNext()) {

			User user = itr.next();
			if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
				flag = true;

				bal = user.getBalance();
			}
			if (flag)
				break;

		}

		return bal;

	}
	
	//Method to deposit money in account

	@Override
	public double amt_deposit(String username, String password, double amount) {

		Iterator<User> itr = list.iterator();
		while (itr.hasNext()) {
			User user = itr.next();
			if (user.getUsername().equals(username) && user.getPassword().equals(password))
				bal = user.getBalance();

		}
		itr = list.iterator();
		while (itr.hasNext()) {
			User user = itr.next();
			if (user.getUsername().equals(username) && user.getPassword().equals(password)) {

				Transaction obj = new Transaction("deposit", amount, (bal));
				trans.add(obj);
			}
		}
		return bal + amount;
	}
	
	//Method to withdraw money

	@Override
	public double amt_withdraw(String username, String password, double amount) {

		Double am = 0d;
		Iterator<User> itr = list.iterator();
		while (itr.hasNext()) {
			User user = itr.next();
			if (user.getUsername().equals(username) && user.getPassword().equals(password))
				bal = user.getBalance();
			if (bal < amount) {
				am = 0d;
			} else
				am = bal - amount;

		}
		itr = list.iterator();
		while (itr.hasNext()) {
			User user = itr.next();
			if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
				Transaction obj = new Transaction("withdraw", amount, am);

				trans.add(obj);
			}
		}
		return am;

	}
	
	//Method to transfer fund to another account

	@Override
	public boolean fund_Transfer(String username, String password, double amount, String username1) {

		boolean flag = true;
		boolean flag1=true;
		Iterator<User> itr = list.iterator();
		while (itr.hasNext()) {
			User user = itr.next();
			if (user.getUsername().equals(username) && user.getPassword().equals(password))
				bal = user.getBalance();
			if (bal < amount) {
				flag = false;
			} else {

				itr = list.iterator();
				while (itr.hasNext()) {
					User user1 = itr.next();
					if (user1.getUsername().equals(username1)) {
						flag1 = true;
						bal = user1.getBalance();
						bal = bal + amount;
						if (user1.getUsername().equals(username)) {
							bal = user1.getBalance();
							bal = bal - amount;
						} else
							flag1 = false;

						if (flag1 ==false)
							System.out.println("Username not found");
					}
				}
			}
		}
		return flag;

	}
	
	//Method to create new user account

	@Override
	public boolean createUserAccount(User user) {

		boolean status = true;
		if (list.contains(user.getUsername()))
			status = false;
		else
			list.add(user);

		return true;
	}
	
	//To print transaction details

	@Override
	public List<Transaction> get_Transaction(String username, String password) {
		List<Transaction> ll = trans;
		return ll;
	}
	
	//to validate login details

	@Override
	public boolean loginValidation(String username, String password) {
		boolean flag = false;
		Iterator<User> itr = list.iterator();
		while (itr.hasNext()) {
			User u = (User) itr.next();
			if ((u.getUsername().equals(username)))
				flag = true;

		}
		return flag;

	}
}
