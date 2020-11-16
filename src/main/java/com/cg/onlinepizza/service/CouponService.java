package com.cg.onlinepizza.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.onlinepizza.model.Coupon;

@Service
public interface CouponService {
	
	public List<Coupon> getAllCoupon();

	public Coupon findCoupon(String couponName);

	public List<Coupon> deleteCoupon(String couponName);

	public List<Coupon> saveCoupon(Coupon coupon);

	public List<Coupon> updateCoupon(Coupon coupon);

}
