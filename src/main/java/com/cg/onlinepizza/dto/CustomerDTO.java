package com.cg.onlinepizza.dto;

import java.io.Serializable;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CustomerDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int customerId;
    
	@NotNull
	@Size(min = 2, message = "First Name should have atleast 2 characters")
	private String firstName;
	
	@NotNull(message = "Last name should not be null")
	private String lastName;
    
	@NotNull(message = "Phone number should be 10 characters long")
	private Long customerMobile;
    
	@NotBlank(message = "Email cannot be blank")
	@Email
	private String customerEmail;
    
	@NotBlank(message = "Address cannot be blank")
	private String customerAddress;
    
	@NotNull(message = "Username cannot be blank")
	private String userName;
    
	@NotNull(message = "Passord should contain min of 3 chars and max of 10 chars")
	@Size(min=3, max=10)
	private String password;
	
	private Set<OrderDTO> order;


	public CustomerDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomerDTO(String firstName, String lastName, Long customerMobile, String customerEmail,
			String customerAddress, String userName, String password, Set<OrderDTO> order) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.customerMobile = customerMobile;
		this.customerEmail = customerEmail;
		this.customerAddress = customerAddress;
		this.userName = userName;
		this.password = password;
		this.order = order;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Long getCustomerMobile() {
		return customerMobile;
	}

	public void setCustomerMobile(Long customerMobile) {
		this.customerMobile = customerMobile;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<OrderDTO> getOrder() {
		return order;
	}

	public void setOrder(Set<OrderDTO> order) {
		this.order = order;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + customerId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerDTO other = (CustomerDTO) obj;
		if (customerId != other.customerId)
			return false;
		return true;
	}

	
}
