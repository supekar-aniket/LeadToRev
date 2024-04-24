package com.aniket.LeadToRev.Repository.SearchProductRepo;

import java.util.List;

import com.aniket.LeadToRev.Model.Product;

public interface SearchRepository {
	
	List<Product> findByText(String text);

}
