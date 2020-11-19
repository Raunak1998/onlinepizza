package com.cg.onlinepizza.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class CouponDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "CouponName is Mandatory")
	private String couponName;

	@NotBlank(message = "CouponType is Mandatory")
	@Pattern(regexp = "^\\d{2}+%+\\s+(OFF)+$")
	private String couponType;

	@Size(max=200)
    @NotBlank(message = "CouponDescription is Mandatory")
	private String couponDescription;
	

	public CouponDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CouponDTO(String couponName, String couponType, String couponDescription) {
		super();
		this.couponName = couponName;
		this.couponType = couponType;
		this.couponDescription = couponDescription;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public String getCouponType() {
		return couponType;
	}

	public void setCouponType(String couponType) {
		this.couponType = couponType;
	}

	public String getCouponDescription() {
		return couponDescription;
	}

	public void setCouponDescription(String couponDescription) {
		this.couponDescription = couponDescription;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((couponName == null) ? 0 : couponName.hashCode());
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
		CouponDTO other = (CouponDTO) obj;
		if (couponName == null) {
			if (other.couponName != null)
				return false;
		} else if (!couponName.equals(other.couponName))
			return false;
		return true;
	}





}
