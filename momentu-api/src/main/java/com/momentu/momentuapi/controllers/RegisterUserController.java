package com.momentu.momentuapi.controllers;
//
//import com.momentu.momentuapi.entities.Role;
//import com.momentu.momentuapi.entities.User;
//import com.momentu.momentuapi.entities.UserRole;
//import com.momentu.momentuapi.repos.UserRepository;
//import com.momentu.momentuapi.repos.UserRoleRepository;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.rest.webmvc.PersistentEntityResource;
//import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
//import org.springframework.data.rest.webmvc.RepositoryRestController;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Optional;
//
//@RepositoryRestController
//@RequestMapping("/api")
//public class RegisterUserController {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private UserRoleRepository userRoleRepository;
//
//    @Autowired
//    public void setUserRepository(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @RequestMapping(value = "/register", method = RequestMethod.POST)
//    public ResponseEntity<User> register(@RequestBody User user)
//                                                          //  ,PersistentEntityResourceAssembler persistentEntityResourceAssembler)
//    {
//
////        if(StringUtils.isBlank(user.getUsername())) {
////            throw new IllegalArgumentException("Missing value for registration");
////        }
////
////        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());
////        if(!existingUser.equals(Optional.empty())) {
////            throw new IllegalArgumentException("User already exists1");
////        }
//
////        existingUser = userRepository.findByEmail(user.getEmail());
////        if(!existingUser.equals(Optional.empty())) {
////            throw new IllegalArgumentException("User already exists2");
////        }
//
//        User newUser = userRepository.save(user);
////        UserRole roleAssociation = new UserRole(user.getId(), Role.MEMBER);
////        userRoleRepository.save(roleAssociation);
//
//       // return ResponseEntity.ok(persistentEntityResourceAssembler.toResource(user));
//        return new ResponseEntity<User>(newUser, HttpStatus.OK);
//    }
//}
import com.momentu.momentuapi.entities.User;
import com.momentu.momentuapi.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterUserController {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/")
    public ResponseEntity<User> get(){

        User user1 = new User();
        user1.setFirstName("Ibrahim");
        user1.setLastName("Alawwad");
        user1.setEmail("Ialawwad@depaul.edu");
        user1.setGender("Male");

        return new ResponseEntity<User>(user1, HttpStatus.OK);
    }
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<User> registerNewUser(@RequestBody User newUser){
        userRepository.save(newUser);
        System.out.println("Got in register method and got the user: " + newUser.getLastName());
        return new ResponseEntity<User>(newUser, HttpStatus.OK);
    }
}