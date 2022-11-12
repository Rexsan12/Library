package net.codejava.javaee.bank;

public class Customer {
	protected int id;
	protected String name;
	protected String accountno;
	protected float balance;

	public Customer() {
	}
	
	public Customer(int id) {
		this.id = id;
	}
	public Customer(int id, String name, String accountno, float balance) {
		this(name, accountno, balance);
		this.id = id;
	}
	public Customer(String name, String accountno, float balance) {
		this.name = name;
		this.accountno = accountno;
		this.balance = balance;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccountno() {
		return accountno;
	}

	public void setAccountno(String accountno) {
		this.accountno = accountno;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}


}
