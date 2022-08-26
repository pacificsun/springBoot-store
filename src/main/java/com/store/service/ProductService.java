
package com.store.service;

import com.store.dao.ProductRepsitory;
import com.store.model.Product;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class ProductService {
    
    @Autowired
    private ProductRepsitory productRepo;
    
    // Save Product
    public void saveProduct(Product product){
        productRepo.save(product);
    }
    // list all product
     public List<Product> listAllProduct(){
   
       return (List<Product>) productRepo.findAll();
   }
   
    // Get Product by Id
     public Product get(Integer id) {
       Optional<Product> result = productRepo.findById(id);
       if(result.isPresent()){
           
           return result.get();
       }
       throw new UsernameNotFoundException("Could not find any Product with ID" + id);
   
   }
     
     // delete product
    public void delete(Integer id){
//        Long count = productRepo.countById(id);
        Optional<Product> result = productRepo.findById(id);
       if(!result.isPresent()){
       throw new UsernameNotFoundException("Could not found user with id:" +id);
       }      
      productRepo.deleteById(id);
    }
 
}
