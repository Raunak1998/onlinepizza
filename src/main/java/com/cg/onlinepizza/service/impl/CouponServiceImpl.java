package com.cg.onlinepizza.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
		List<CouponDTO> couponDTOReturn = new ArrayList<>();
		for(Coupon c:couponRepository.findAll())
		{
			couponDTOReturn.add(entityToDTO(c));
		}
		
		return couponDTOReturn;
	}

	@Override
	public List<CouponDTO> deleteCoupon(String couponName) throws CouponNotFoundException{
		List<CouponDTO> couponDTOReturn = new ArrayList<>();
		Optional<Coupon>checkCoupon=couponRepository.findById(couponName);
		if(!checkCoupon.isPresent())
		{
			throw new CouponNotFoundException("Coupon with name " + couponName + " not Found");
		}
		couponRepository.deleteById(couponName);
		
		List<Coupon>CouponEntityList=couponRepository.findAll();
		
		if(CouponEntityList==null || CouponEntityList.isEmpty())
		{
			throw new CouponNotFoundException("No Coupons Found"); 
		}
		
		for(Coupon c:couponRepository.findAll())
		{
			couponDTOReturn.add(entityToDTO(c));
		}
		return couponDTOReturn;
	}

	@Override
	public List<CouponDTO> saveCoupon(CouponDTO couponDTO) throws CouponAlreadyExistsException{
		Coupon coupon = new Coupon();
		coupon = DTOToEntity(couponDTO);
		
		couponRepository.saveAndFlush(coupon);
		
		Optional<Coupon>returnCoupon=couponRepository.findById(coupon.getCouponName());
		if(!returnCoupon.isPresent())
		{
			throw new CouponAlreadyExistsException("Coupon cannot be added");
		}
		List<CouponDTO> couponDTOReturn = new ArrayList<>();
		for(Coupon c:couponRepository.findAll())
		{
			couponDTOReturn.add(entityToDTO(c));
		}
		return couponDTOReturn;
	}

	@Override
	public List<CouponDTO> updateCoupon(CouponDTO couponDTO) throws CouponNotFoundException{
		Coupon coupon = new Coupon();
		coupon = DTOToEntity(couponDTO);
		
		Optional<Coupon>checkCoupon=couponRepository.findById(coupon.getCouponName());
		if(!checkCoupon.isPresent())
		{
			throw new CouponNotFoundException("Coupon with name " + coupon.getCouponName() + " not Found");
		}
		
		couponRepository.save(coupon);
		
		List<CouponDTO> couponDTOReturn = new ArrayList<>();
		for(Coupon c:couponRepository.findAll())
		{
			couponDTOReturn.add(entityToDTO(c));
		}
		
		return  couponDTOReturn;
	}

	@Override
	public CouponDTO findCoupon(String couponName) throws CouponNotFoundException{
		Optional<Coupon>checkCoupon=couponRepository.findById(couponName);
		if(!checkCoupon.isPresent())
		{
			throw new CouponNotFoundException("Coupon with name " + couponName + " not Found");
		}
		return entityToDTO(checkCoupon.get());
	}


}
