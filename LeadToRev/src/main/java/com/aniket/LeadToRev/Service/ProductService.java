package com.aniket.LeadToRev.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.aniket.LeadToRev.Model.Product;
import com.aniket.LeadToRev.Repository.ProductRepo.ProductRepository;
import com.aniket.LeadToRev.Repository.SearchProductRepo.SearchRepository;

@Service
public class ProductService {
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	SearchRepository searchRepository;
	
	public List<Product> getAllProducts(){
		return productRepository.findAll();
	}
	
	public List<Product> searchProduct(@PathVariable String text){
		return searchRepository.findByText(text);
	}
	
	public Product addProduct(@RequestBody Product product) {
		return productRepository.save(product);
	}
	
	public Product updateProduct(Product product) {

		Product existingProduct = productRepository.findById(product.getId()).orElse(null);
	    if (existingProduct != null) {

	    	existingProduct.setName(product.getName());
	        existingProduct.setDescription(product.getDescription());
	        existingProduct.setPrice(product.getPrice());
	        existingProduct.setCategories(product.getCategories());
	        existingProduct.setAttributes(product.getAttributes());
	        existingProduct.setAvailability(product.getAvailability());
	        existingProduct.setRatings(product.getRatings());
	        
	        return productRepository.save(existingProduct);
	    } else {
	        return null;
	    }
	}

	
	public void deleteProduct(@RequestParam String id) {
	    productRepository.deleteById(id);
	}

	

}














