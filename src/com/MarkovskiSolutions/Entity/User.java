package com.MarkovskiSolutions.Entity;

import java.sql.Date;

public class User {
	
	private int id;
	private String FirstName;
	private String LastName;
	private Date DateBirth;
	private String PhoneNumber;
	private String EMail;
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return FirstName;
	}
	
	/**
	 * @param firstName the firstName to set
	 * @throws EmptyFieldException 
	 */
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return LastName;
	}
	
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	
	/**
	 * @return the dateBirth
	 */
	public Date getDateBirth() {
		return DateBirth;
	}
	
	/**
	 * @param dateBirth the dateBirth to set
	 */
	public void setDateBirth(Date dateBirth) {
		DateBirth = dateBirth;
	}
	
	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return PhoneNumber;
	}
	
	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}
	
	/**
	 * @return the eMail
	 */
	public String getEMail() {
		return EMail;
	}
	
	/**
	 * @param eMail the eMail to set
	 */
	public void setEMail(String eMail) {
		EMail = eMail;
	}	
}
