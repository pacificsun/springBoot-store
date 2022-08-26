
package com.store.service;

import com.store.dao.SalesRepository;
import com.store.model.Sales;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SalesService {
   @Autowired
   SalesRepository salesRepo;
   
   // Save a Transaction
   public void saveSales(Sales sales){
       salesRepo.save(sales);
   }
   // list sales
   public List<Sales> listAllSales(){
   return (List<Sales>) salesRepo.findAll();
   }
     // Get Sales by Id
     public Sales getSalesById(Integer id) {
       Optional<Sales> result = salesRepo.findById(id);
       if(result.isPresent()){
           
           return result.get();
       }
       throw new UsernameNotFoundException("Could not find any Product with ID" + id);
   
   }
     
     // delete sales
    public void delete(Integer id){
//        Long count = productRepo.countById(id);
        Optional<Sales> result = salesRepo.findById(id);
       if(!result.isPresent()){
       throw new UsernameNotFoundException("Could not found user with id:" +id);
       }      
      salesRepo.deleteById(id);
    }
}
