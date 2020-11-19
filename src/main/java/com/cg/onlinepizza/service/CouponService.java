package com.cg.onlinepizza.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.onlinepizza.dto.CouponDTO;
import com.cg.onlinepizza.exceptions.CouponAlreadyExistsException;
import com.cg.onlinepizza.exceptions.CouponNotFoundException;

@Service
public interface CouponService {
	
	public List<CouponDTO> getAllCoupon();

	public CouponDTO findCoupon(String couponName) throws CouponNotFoundException;

	public List<CouponDTO> deleteCoupon(String couponName) throws CouponNotFoundException;

	public List<CouponDTO> saveCoupon(CouponDTO couponDTO) throws CouponAlreadyExistsException;

	public List<CouponDTO> updateCoupon(CouponDTO couponDTO) throws CouponNotFoundException;

}
