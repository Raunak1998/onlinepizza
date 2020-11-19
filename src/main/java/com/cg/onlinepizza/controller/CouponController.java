package com.cg.onlinepizza.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.onlinepizza.dto.CouponDTO;
import com.cg.onlinepizza.exceptions.CouponAlreadyExistsException;
import com.cg.onlinepizza.exceptions.CouponNotFoundException;
import com.cg.onlinepizza.service.CouponService;

@RestController
@RequestMapping("/coupon")
public class CouponController {

	@Autowired
	private CouponService couponService;
	
	@PutMapping("/update")
	public ResponseEntity<List<CouponDTO>> updateCoupon(
			@RequestBody CouponDTO couponDTO)throws CouponNotFoundException{
		List<CouponDTO> coupons = couponService.updateCoupon(couponDTO);
		return new ResponseEntity<List<CouponDTO>>(coupons,HttpStatus.OK);
	}
	
	@PostMapping("/insert")
	public ResponseEntity<List<CouponDTO>> insertCoupon(
			@RequestBody CouponDTO couponDTO) throws CouponAlreadyExistsException{
		List<CouponDTO> coupons = couponService.saveCoupon(couponDTO);
		return new ResponseEntity<List<CouponDTO>>(coupons,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{couponName}")
	public ResponseEntity<List<CouponDTO>> deleteCoupon(
			@PathVariable("couponName")String couponName) throws CouponNotFoundException{
		List<CouponDTO> coupons= couponService.deleteCoupon(couponName);
		
		return new ResponseEntity<List<CouponDTO>>(coupons, HttpStatus.OK);
	}
	
	@GetMapping("/find/{couponName}")
	public ResponseEntity<CouponDTO> findCoupon(
			@PathVariable("couponName")String couponName) throws CouponNotFoundException{
		CouponDTO coupon= couponService.findCoupon(couponName);
		
		return new ResponseEntity<CouponDTO>(coupon, HttpStatus.OK);
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<List<CouponDTO>> getAllCoupon(){
		List<CouponDTO> coupons= couponService.getAllCoupon();
		
		return new ResponseEntity<List<CouponDTO>>(coupons, HttpStatus.OK);
	}
}
