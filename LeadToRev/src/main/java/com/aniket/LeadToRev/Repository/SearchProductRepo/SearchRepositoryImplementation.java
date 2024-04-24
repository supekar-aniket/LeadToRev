package com.aniket.LeadToRev.Repository.SearchProductRepo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

import com.aniket.LeadToRev.Model.Product;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Component
public class SearchRepositoryImplementation implements SearchRepository{

	@Autowired
	MongoClient client;
	
	@Autowired
	MongoConverter converter;
	
	@Override
	public List<Product> findByText(String text) {

		final List<Product> products = new ArrayList<>();
		
		MongoDatabase database = client.getDatabase("LeadToRev");
		MongoCollection<Document> collection = database.getCollection("Products");
		
		AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search", 
		    new Document("text", 
		    new Document("query", text)
		    .append("path", Arrays.asList("name", "description", "categories")))), 
		    new Document("$sort", 
		    new Document("ratings", -1L))));
		
		result.forEach(doc -> products.add(converter.read(Product.class, doc)));
		
		return products;
	}

}
