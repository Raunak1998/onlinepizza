package com.cg.onlinepizza.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="coupon_details")
public class Coupon implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="coupon_name")
	private String couponName;

	@Column(name="coupon_type")
	private String couponType;

	@Column(name="coupon_description")
	private String couponDescription;

	public Coupon()
	{
		super();
	}

	public Coupon(String couponName, String couponType, String couponDescription) {
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
		Coupon other = (Coupon) obj;
		if (couponName == null) {
			if (other.couponName != null)
				return false;
		} else if (!couponName.equals(other.couponName))
			return false;
		return true;
	}

	

}
