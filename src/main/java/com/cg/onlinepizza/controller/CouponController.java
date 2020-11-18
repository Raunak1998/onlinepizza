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
import com.cg.onlinepizza.service.CouponService;

@RestController
@RequestMapping("/coupon")
public class CouponController {

	@Autowired
	private CouponService couponService;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PutMapping("/update")
	public ResponseEntity<List<CouponDTO>> updateCoupon(
			@RequestBody CouponDTO couponDTO){
		List<CouponDTO> coupons = couponService.updateCoupon(couponDTO);
		if(coupons.isEmpty())
		{
			return new ResponseEntity("Sorry! Coupons not available!",HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<CouponDTO>>(coupons,HttpStatus.OK);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping("/insert")
	public ResponseEntity<List<CouponDTO>> insertCoupon(
			@RequestBody CouponDTO couponDTO){
		List<CouponDTO> coupons = couponService.saveCoupon(couponDTO);
		if(coupons.isEmpty())
		{
			return new ResponseEntity("Sorry! Coupons could not be inserted!",HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<CouponDTO>>(coupons,HttpStatus.OK);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@DeleteMapping("/delete/{couponName}")
	public ResponseEntity<List<CouponDTO>> deleteCoupon(
			@PathVariable("couponName")String couponName){
		List<CouponDTO> coupons= couponService.deleteCoupon(couponName);
		if(coupons.isEmpty() || coupons==null) {
			return new ResponseEntity("Sorry! Coupon Name not available!", 
					HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<CouponDTO>>(coupons, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/find/{couponName}")
	public ResponseEntity<CouponDTO> findCoupon(
			@PathVariable("couponName")String couponName){
		CouponDTO coupon= couponService.findCoupon(couponName);
		if(coupon==null) {
			return new ResponseEntity("Sorry! Coupon not found!", 
					HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<CouponDTO>(coupon, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("/findAll")
	public ResponseEntity<List<CouponDTO>> getAllCoupon(){
		List<CouponDTO> coupons= couponService.getAllCoupon();
		if(coupons.isEmpty()) {
			return new ResponseEntity("Sorry! Coupons not found!", 
					HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<CouponDTO>>(coupons, HttpStatus.OK);
	}
}
