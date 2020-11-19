package com.cg.onlinepizza.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.onlinepizza.dto.CouponDTO;
import com.cg.onlinepizza.exceptions.CouponAlreadyExistsException;
import com.cg.onlinepizza.exceptions.CouponNotFoundException;
import com.cg.onlinepizza.model.Coupon;
import com.cg.onlinepizza.repository.CouponRepository;
import com.cg.onlinepizza.service.CouponService;

@Service
public class CouponServiceImpl implements CouponService{

	@Autowired
	private CouponRepository couponRepository;
	
	static Logger log = Logger.getLogger(CouponServiceImpl.class.getName());

	

	public static CouponDTO entityToDTO(Coupon coupon)
	{
		CouponDTO couponDTO = new CouponDTO();
		couponDTO.setCouponName(coupon.getCouponName());
		couponDTO.setCouponType(coupon.getCouponType());
		couponDTO.setCouponDescription(coupon.getCouponDescription());
		return couponDTO;
	}
	
	public static Coupon DTOToEntity(CouponDTO couponDTO)
	{	
		Coupon coupon = new Coupon();
		coupon.setCouponName(couponDTO.getCouponName());
		coupon.setCouponType(couponDTO.getCouponType());
		coupon.setCouponDescription(couponDTO.getCouponDescription());
		
		return coupon;
	}
	
	@Override
	public List<CouponDTO> getAllCoupon() {
		log.info("Service Layer - Entry - get Coupons");
		List<CouponDTO> couponDTOReturn = new ArrayList<>();
		for(Coupon c:couponRepository.findAll())
		{
			couponDTOReturn.add(entityToDTO(c));
		}
		log.info("Service Layer - Exit - get Coupons");
		return couponDTOReturn;
	}

	@Override
	public List<CouponDTO> deleteCoupon(String couponName) {
		log.info("Service Layer - Entry - delete Coupons");		
		List<CouponDTO> couponDTOReturn = new ArrayList<>();
		Optional<Coupon>checkCoupon=couponRepository.findById(couponName);
		if(!checkCoupon.isPresent())
		{
		log.warn("WARN: delete coupons Started");
			throw new CouponNotFoundException("Coupon with name " + couponName + " not Found");
		}
		couponRepository.deleteById(couponName);
		
		List<Coupon>CouponEntityList=couponRepository.findAll();
		
		if(CouponEntityList==null || CouponEntityList.isEmpty())
		{
		    log.warn("WARN:  delete coupons Started");
            throw new CouponNotFoundException("No Coupons Found"); 
		}
		
		for(Coupon c:couponRepository.findAll())
		{
			couponDTOReturn.add(entityToDTO(c));
		}
		log.info("Service Layer - Exit - delete Coupons");
		return couponDTOReturn;
	}


	@Override
	public List<CouponDTO> saveCoupon(CouponDTO couponDTO) {
		log.info("Service Layer - Entry - save Coupons");	
		Coupon coupon = new Coupon();
		coupon = DTOToEntity(couponDTO);
		
		couponRepository.saveAndFlush(coupon);
		
		Optional<Coupon>returnCoupon=couponRepository.findById(coupon.getCouponName());
		if(!returnCoupon.isPresent())
		{
			log.warn("WARN:  save coupons Started");
			throw new CouponAlreadyExistsException("Coupon cannot be added");
		}
		couponRepository.saveAndFlush(coupon);
		
		List<CouponDTO> couponDTOReturn = new ArrayList<>();
		for(Coupon c:couponRepository.findAll())
		{
			couponDTOReturn.add(entityToDTO(c));
		}
		log.info("Service Layer - Exit - delete Coupons");
		return couponDTOReturn;
	}

	@Override
	public List<CouponDTO> updateCoupon(CouponDTO couponDTO) {
		log.info("Service Layer - Entry - update Coupons");
		Coupon coupon = new Coupon();
		coupon = DTOToEntity(couponDTO);
		
		Optional<Coupon>checkCoupon=couponRepository.findById(coupon.getCouponName());
		if(!checkCoupon.isPresent())
		{
			log.warn("WARN:  update coupons Started");
			throw new CouponNotFoundException("Coupon with name " + coupon.getCouponName() + " not found");
		}
		
		couponRepository.save(coupon);
		
		List<CouponDTO> couponDTOReturn = new ArrayList<>();
		for(Coupon c:couponRepository.findAll())
		{
			couponDTOReturn.add(entityToDTO(c));
		}
		log.info("Service Layer - Exit - update Coupons");
		return  couponDTOReturn;
	}

	@Override
	public CouponDTO findCoupon(String couponName) {
		log.info("Service Layer - Entry - find Coupons");
		Optional<Coupon>checkCoupon=couponRepository.findById(couponName);
		if(!checkCoupon.isPresent())
		{
			log.warn("WARN:  update coupons Started");
			throw new CouponNotFoundException("Coupon with name " + couponName + " not Found");
		}
		log.info("Service Layer - Exit - find Coupons");
		return entityToDTO(checkCoupon.get());
	}


}
