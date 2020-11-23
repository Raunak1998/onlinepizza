package com.cg.onlinepizza.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.onlinepizza.dto.CouponDTO;
import com.cg.onlinepizza.exceptions.CouponNotFoundException;
import com.cg.onlinepizza.model.Coupon;
import com.cg.onlinepizza.repository.CouponRepository;
import com.cg.onlinepizza.service.CouponService;


@SpringBootTest
public class CouponServiceImplTest {

	@Autowired
	private CouponService couponService;
	
	@MockBean
	CouponRepository couponRepository;
	
	//Retrieving all the coupons present
	@Test
	public void getAllCouponsPresentTest()
	{
		
		CouponDTO coupon1 = new CouponDTO("GET50", "50% OFF", "On Orders above 500Rs");
		CouponDTO coupon2 = new CouponDTO("GET50", "50% OFF", "On Orders above 500Rs");
		
		Mockito.when(couponRepository.findAll()).thenReturn(Stream.of(CouponServiceImpl.DTOToEntity(coupon1),CouponServiceImpl.DTOToEntity(coupon2)).collect(Collectors.toList()));
		List<CouponDTO> actual = couponService.getAllCoupon();
		assertEquals(2,actual.size());
	}

	//No coupons present to retrieve
	@Test
	public void getAllCouponsNotPresentTest()
	{
		Mockito.when(couponRepository.findAll()).thenThrow(new CouponNotFoundException("No coupons present in the database"));

		Exception exception = assertThrows(CouponNotFoundException.class,()->couponService.getAllCoupon());
		assertTrue(exception.getMessage().contains("No coupons present in the database"));
	}
	
	//Checks save coupon method to add new values
	@Test
	public void saveCouponPresentTest()  {
		Coupon coupon = new Coupon("GET50", "50% OFF", "On Orders above 500Rs");
		Mockito.when(couponRepository.saveAndFlush(coupon)).thenReturn(coupon);  
		Mockito.when(couponRepository.findAll()).thenReturn(Stream.of(coupon).collect(Collectors.toList()));
		List<CouponDTO> actual = couponService.getAllCoupon();
		assertEquals(1,actual.size());
	}
	
	//Checks if the coupon is present in database so as to perform update operation
	@Test
	public void updateCouponPresentTest() throws CouponNotFoundException {
		Coupon coupon = new Coupon("GET50", "50% OFF", "On Orders above 500Rs");
		Mockito.when(couponRepository.findById("GET50")).thenReturn(Optional.of(coupon));
		
        Mockito.when(couponRepository.save(coupon)).thenReturn(coupon);
		Mockito.when(couponRepository.findAll()).thenReturn(Stream.of(coupon).collect(Collectors.toList()));

		List<CouponDTO> actual = couponService.updateCoupon(CouponServiceImpl.entityToDTO(coupon));
		assertEquals(1,actual.size());
	}
	/*
	@Test
	public void deleteCouponPresent() {
		Coupon coupon = new Coupon("GET50", "50% OFF", "On Orders above 500Rs");
		Mockito.when(couponRepository.findById("GET50")).thenReturn(Optional.of(coupon));
        Mockito.doNothing().when(couponRepository).delete(coupon);
        @SuppressWarnings("unused")
		List<CouponDTO> couponDTO = couponService.deleteCoupon("GET50");
		Mockito.verify(couponRepository,times(1)).delete(coupon);
		Mockito.verifyNoMoreInteractions(couponRepository);
		
	}
	*/
	
	//Checks if the coupon passed to update is not found in database and returns an appropriate error
	@Test
	public void updateCouponNotPresentTest()
	{
		
		Coupon coupon = new Coupon("GET50", "50% OFF", "On Orders above 500Rs");
		
		//coupon.setCouponName(null);
		Mockito.when(couponRepository.findById("GET50")).thenReturn(null);
		assertThrows(NullPointerException.class, () -> couponService.updateCoupon(CouponServiceImpl.entityToDTO(coupon)));
	}
	
	//Checks if the coupon is not found in database and returns an appropriate error
	@Test
	public void findCouponNotPresentTest()
	{
		@SuppressWarnings("unused")
		Coupon coupon = new Coupon("GET50", "50% OFF", "On Orders above 500Rs");
		Mockito.when(couponRepository.findById("GET60")).thenThrow(new CouponNotFoundException("Coupon not present in the database"));
		Exception exception = assertThrows(CouponNotFoundException.class,()->couponService.findCoupon("GET60"));
		assertTrue(exception.getMessage().contains("Coupon not present in the database"));
	}
	
	//Checks if the coupon is present in database
	@Test
	public void findCouponPresentTest()
	{
		
		Coupon coupon1 = new Coupon("GET50", "50% OFF", "On Orders above 500Rs"); 
		
		coupon1.setCouponName(null);

		Mockito.when(couponRepository.findById(coupon1.getCouponName())).thenReturn(Optional.of(coupon1));

		CouponDTO actual = couponService.findCoupon(coupon1.getCouponName());
		assertEquals(CouponServiceImpl.entityToDTO(coupon1),actual);
	}

	//Check the delete coupon method for null values
	@Test
	public void deleteCouponNullTest()
	{
		String couponName= null;
		List<CouponDTO> actual = couponService.deleteCoupon(couponName);
		assertNull(actual);
	}

	//Check the update coupon method for null values
	@Test
	public void updateCouponNullTest()
	{
		CouponDTO coupon1 = null;
		List<CouponDTO> actual = couponService.updateCoupon(coupon1);
		assertNull(actual);
	}
	
	//Check the save coupon method for null values
	@Test
	public void saveCouponNullTest()
	{
		CouponDTO coupon1 = null;
		List<CouponDTO> actual = couponService.saveCoupon(coupon1);
		assertNull(actual);
	}

}
