
package com.store.service;

import com.store.dao.UserRepository;
import com.store.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @Autowired
    private UserRepository userRepo;
    
    // save user
    public void saveUser(User user){
        user.setRole("ROLE_USER");
        user.setPassword(passwordEncoder.encode(user.getPassword()));   
        userRepo.save(user);
    }
    // find user by id
    public User get(Integer id){
        Optional<User> result = userRepo.findById(id);
        if(result.isPresent()){
        return result.get();
        }
        throw new UsernameNotFoundException("Could not find the user with ID" + id);
    }
    
    
    //find all user
    public List<User> listAllUser() {
     return (List<User>) userRepo.findAll();
    }
    
    // delete user
    public void delete(Integer id) {
        Long count = userRepo.countById(id);
        if(count == null || count == 0){
        throw new UsernameNotFoundException("Could not find user with ID:" + id);
        }
        userRepo.deleteById(id);
    }
    
}
