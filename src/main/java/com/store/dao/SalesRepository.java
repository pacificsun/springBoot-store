
package com.store.dao;


import org.springframework.data.repository.CrudRepository;
import com.store.model.Sales;

public interface SalesRepository extends CrudRepository<Sales, Integer> {
    
}
