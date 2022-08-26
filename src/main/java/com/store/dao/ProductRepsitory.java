package com.store.dao;


import com.store.model.Product;
import org.springframework.data.repository.CrudRepository;
public interface ProductRepsitory extends CrudRepository<Product, Integer  > {
    
}
