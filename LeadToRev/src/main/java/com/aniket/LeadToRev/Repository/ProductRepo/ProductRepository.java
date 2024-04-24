package com.aniket.LeadToRev.Repository.ProductRepo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.aniket.LeadToRev.Model.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product,String>{

}
