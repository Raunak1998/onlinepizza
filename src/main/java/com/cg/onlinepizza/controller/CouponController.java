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

import com.cg.onlinepizza.model.Coupon;
import com.cg.onlinepizza.service.CouponService;

@RestController
@RequestMapping("/coupon")
public class CouponController {

	@Autowired
	private CouponService couponService;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PutMapping("/update")
	public ResponseEntity<List<Coupon>> updateCoupon(
			@RequestBody Coupon coupon){
		List<Coupon> coupons = couponService.updateCoupon(coupon);
		if(coupons.isEmpty())
		{
			return new ResponseEntity("Sorry! Coupons not available!",HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Coupon>>(coupons,HttpStatus.OK);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping("/insert")
	public ResponseEntity<List<Coupon>> insertCoupon(
			@RequestBody Coupon coupon){
		List<Coupon> coupons = couponService.saveCoupon(coupon);
		if(coupons.isEmpty())
		{
			return new ResponseEntity("Sorry! Coupons could not be inserted!",HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<Coupon>>(coupons,HttpStatus.OK);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@DeleteMapping("/delete/{couponName}")
	public ResponseEntity<List<Coupon>> deleteCoupon(
			@PathVariable("couponName")String couponName){
		List<Coupon> coupons= couponService.deleteCoupon(couponName);
		if(coupons.isEmpty() || coupons==null) {
			return new ResponseEntity("Sorry! Coupon Name not available!", 
					HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<Coupon>>(coupons, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/find/{couponName}")
	public ResponseEntity<Coupon> findCoupon(
			@PathVariable("couponName")String couponName){
		Coupon coupon= couponService.findCoupon(couponName);
		if(coupon==null) {
			return new ResponseEntity("Sorry! Coupon not found!", 
					HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Coupon>(coupon, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("/findAll")
	public ResponseEntity<List<Coupon>> getAllCoupon(){
		List<Coupon> coupons= couponService.getAllCoupon();
		if(coupons.isEmpty()) {
			return new ResponseEntity("Sorry! Coupons not found!", 
					HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<Coupon>>(coupons, HttpStatus.OK);
	}
}
