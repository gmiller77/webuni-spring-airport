package hu.webuni.airport.service;

import org.springframework.stereotype.Service;

@Service
public class PriceService {
	
	private DiscountService discountService;
	
	//az alábbi konstruktorban a paraméteres bean injektálása van a DiscountService-nek, mivel
	// csak 1 konstruktor van ebben az osztályban, és emiatt a SpringBoot automatikusan injektál
	public PriceService(DiscountService discountService) {		
		this.discountService = discountService;
	}

	public int getFinalPrice(int price) {
		return (int)(price / 100.0 * (100 - discountService.getDiscountPercent(price)));
	}
}
