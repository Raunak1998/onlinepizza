package com.cg.onlinepizza.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.onlinepizza.model.Coupon;
import com.cg.onlinepizza.repository.CouponRepository;
import com.cg.onlinepizza.service.CouponService;

@Service
public class CouponServiceImpl implements CouponService{

	@Autowired
	private CouponRepository couponRepo;

	
	
	@Override
	public List<Coupon> getAllCoupon() {
		return couponRepo.findAll();
	}

	@Override
	public List<Coupon> deleteCoupon(String couponName) {
		couponRepo.deleteById(couponName);
		return couponRepo.findAll();
	}

	@Override
	public List<Coupon> saveCoupon(Coupon coupon) {
		couponRepo.saveAndFlush(coupon);

		return  couponRepo.findAll();
	}

	@Override
	public List<Coupon> updateCoupon(Coupon coupon) {
		couponRepo.save(coupon);

		return  couponRepo.findAll();
	}

	@Override
	public Coupon findCoupon(String couponName) {
		Optional<Coupon>coupon=couponRepo.findById(couponName);
		return coupon.get();
	}


}
