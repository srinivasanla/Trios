package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


import play.db.jpa.Model;

@Entity
public class Accounts extends Model {

	public String name;

	public String abouthim;

	public String password;

	public long registerNo;

	@Column(columnDefinition = "DATE")
	public Date dob;

	public boolean isDeleted;
	
	public String mailId;
	//public AccountType accountType;
	public boolean superPrivelages;
	
	public String accountType;
	
	@Column(columnDefinition = "TIMESTAMP")
	public Date createdAt;
	
	@Column(columnDefinition = "SMALLINT default  '0'")	
	public boolean isEnabled;
	
	public String fb;
	public String blogger;
	public String twitter;

	/* Default Constructor */
	

	/**
	 * Return the Account type for the ID value that is being passed from the UI
	 * 
	 * @param idValue
	 * @return
	 /
	public static Accounts getAccountType(Integer idValue) {
		switch (idValue) {
		case 1:
			return Accounts.STUDENT;
		case 2:
			return Accounts.ADMIN;
		case 3:
			return Accounts.MODERATOR;
		case 4:
			return Accounts.STAFF;
		}
		
		return AccountType.STUDENT;
	}
	*/
	
}
