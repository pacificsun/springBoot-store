
package com.store.controller;
import com.store.model.Product;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.store.helper.Message;
import com.store.service.ProductService;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProductController {
    
    @Autowired
    private  ProductService productService;
    
    
    // list products
    
    @RequestMapping("/products")
    public String showProductList(Model model) {
        List<Product> listProducts = productService.listAllProduct();
  
        model.addAttribute("listProducts", listProducts);
        return "products";

    }
    
    
     // show add product form
    @GetMapping("/addProduct")
    public String showNewForm(Model model) {
        model.addAttribute("product", new Product());
         model.addAttribute("pageTitle", "Add New Product");

        return "product_form";
    }
    
        // Save Product 
    
    @PostMapping("/saveProduct")
    public String saveUser(Product product, Model model, HttpSession session, RedirectAttributes ra) {
        System.out.println("user::" + product);
        try {
            productService.saveProduct(product);
            model.addAttribute("product", new Product());
            model.addAttribute("pageTitle", "Add New Product");
            ra.addFlashAttribute("message", "The product has been saved successfully");
//            session.setAttribute("message", new Message("Successfull saved Successfully!!", "alert-success"));
            return "redirect:/products";
        } catch (Exception e) {

            e.printStackTrace();
            model.addAttribute("product", product);
            session.setAttribute("message", new Message("Something Went wrong !!" + e.getMessage(), "alert-danger"));
            return "product_form";
        }

    }
    
     // edit product 
    @GetMapping("/product-edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {

        try {

            Product product = productService.get(id);
            model.addAttribute("product", product);
            model.addAttribute("pageTitle", "Edit product(ID:" + id +")");

            return "product_form";

        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
             model.addAttribute("pageTitle", e.getMessage());
            return "product_form";
        }

    }
    
    // delete user
    
    @GetMapping("/product-delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes ra) {

        try {

            productService.delete(id);
//            model.addAttribute("pageTitle", "Edit User(ID:" + id +")");
            ra.addFlashAttribute("message", "The product with ID: " + id + " has been deleted");
           
        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
            ra.addFlashAttribute("message", e.getMessage());
        }
        
         return "redirect:/products";
    
    }
    
}
