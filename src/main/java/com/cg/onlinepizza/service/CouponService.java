package com.cg.onlinepizza.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.onlinepizza.dto.CouponDTO;

@Service
public interface CouponService {
	
	public List<CouponDTO> getAllCoupon();

	public CouponDTO findCoupon(String couponName);

	public List<CouponDTO> deleteCoupon(String couponName);

	public List<CouponDTO> saveCoupon(CouponDTO couponDTO);

	public List<CouponDTO> updateCoupon(CouponDTO couponDTO);

}
