package hu.webuni.airport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.airport.config.AirportConfigProperties;

@Service
public class SpecialDiscountService implements DiscountService {

//	@Value("${airport.discount.special.limit}")
//	private int limit;
//	
//	@Value("${airport.discount.special.percent}")
//	private int specialPercent;
//	
//	@Value("${airport.discount.default.percent}")
//	private int defaultPercent;
	
	@Autowired
	AirportConfigProperties config;
	
	@Override
	public int getDiscountPercent(int totalPrice) {
//		return totalPrice > limit ? specialPercent : defaultPercent;
		return totalPrice > config.getDiscount().getSpecial().getLimit()
				? config.getDiscount().getSpecial().getPercent()
				: config.getDiscount().getDef().getPercent();
	}

}
