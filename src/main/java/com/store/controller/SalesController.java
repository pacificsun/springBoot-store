
package com.store.controller;

import com.store.service.SalesService;
import com.store.model.Sales;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping
public class SalesController {
    @Autowired
    SalesService salesService;
    
    // save sales
    
    @RequestMapping("/sales")
    public String showSalesList(Model model){
        List<Sales> listSales = salesService.listAllSales();
        model.addAttribute("listSales", listSales);
        return"sales";
    }
    
    // show add transaction
    
    @GetMapping("/createTransaction")
    public String showSalesForm(Model model){
        model.addAttribute("sales", new Sales());
        model.addAttribute("pageTitle", "Create Transaction");
        return "transaction_form";
    }
    
    // save transaction
    
    @PostMapping("/saveTransaction")
    public String saveTransaction(Sales sales, Model model, RedirectAttributes ra){
        System.out.println("sales::"+ sales);
        try {
        salesService.saveSales(sales);
         model.addAttribute("sales", new Sales());
         model.addAttribute("pageTitle", "Add Transaction");
         ra.addFlashAttribute("message", "The Transaction has been saved successfully");
        return"redirect:/sales";
        }
        catch(UsernameNotFoundException e){
        e.printStackTrace();
        model.addAttribute("sales", sales);
//            session.setAttribute("message", new Message("Something Went wrong !!" + e.getMessage(), "alert-danger"));
        return"transaction_form";
        }
    }
    
     // edit product 
    @GetMapping("/sales-edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {

        try {

            Sales sales = salesService.getSalesById(id);
            model.addAttribute("sales", sales);
            model.addAttribute("pageTitle", "Edit sales(ID:" + id +")");

            return "transaction_form";

        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
             model.addAttribute("pageTitle", e.getMessage());
            return "transaction_form";
        }

    }
    
    // delete user
    
    @GetMapping("/sales-delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes ra) {

        try {

            salesService.delete(id);
            
            ra.addFlashAttribute("message", "The sales with ID: " + id + " has been deleted");
           
        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
            ra.addFlashAttribute("message", e.getMessage());
        }
        
         return "redirect:/sales";
    
    }
    
}
