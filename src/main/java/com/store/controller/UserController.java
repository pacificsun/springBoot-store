
package com.store.controller;

import com.store.helper.Message;
import com.store.service.UserService;
import com.store.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @RequestMapping("/index")
    public String dashboard(){
    return "normal/user_dashboard";
    }
    
    // save user
    @PostMapping("/save")
    public String saveUser(User user, Model model, HttpSession session, RedirectAttributes ra) {
     System.out.println("user::" + user);
     try {
         userService.saveUser(user);
         model.addAttribute("user", new User());
         model.addAttribute("pageTitle", "Add New user");
         ra.addFlashAttribute("message", "The use has been saved Successfully");
         return "redirect:/admin/user";
     } catch(Exception e){
         e.printStackTrace();
         model.addAttribute("user", user);
         session.setAttribute("message", new Message("Something went wrong!!" + e.getMessage(), "alert-danger") );
     }
     return"admin/user_form";
    }
    
    
    // open user form
    @GetMapping("/add-new")
    public String showNewForm(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle", "Add new User");
        return "admin/user_form";
    }
   
    // edit user
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model){
        try{
            User user = userService.get(id);
            model.addAttribute("user", user);
            model.addAttribute("pageTitle", "Edit user(ID: " + id + ")");
            return "admin/user_form";
        } catch(UsernameNotFoundException e) {
            e.printStackTrace();
            model.addAttribute("pageTitle", e.getMessage());
            return "admin/user_form";
        }
    }
    
    
    // list all users
    @RequestMapping("/user")
    public String listAllUser(Model model) {
    List <User> listUsers = userService.listAllUser();
    System.out.println("UserList::" + listUsers);
    // push data from backend to frontend
    model.addAttribute("listUsers", listUsers);
    return"admin/users";
    }
    
    // Delete user 
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
         userService.delete(id);
         ra.addFlashAttribute("message", "The user with Id: "+ id +" has been deleted" );
        } catch(UsernameNotFoundException e) {
            e.printStackTrace();
            ra.addFlashAttribute("message", e.getMessage());
            
        }
    return"redirect:/admin/user";
    }
    
}
