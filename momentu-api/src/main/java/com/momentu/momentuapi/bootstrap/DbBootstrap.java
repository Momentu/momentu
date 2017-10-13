package com.momentu.momentuapi.bootstrap;

import com.momentu.momentuapi.entities.User;
import com.momentu.momentuapi.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DbBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        User user1 = new User();
        user1.setUsername("jsmith040");
        user1.setFirstName("John");
        user1.setLastName("Smith");
        user1.setEmail("johnsmith040@gmail.com");
        user1.setPassword("TODO:needtoencryptthisinthefuture");
        user1.setGender("Male");

        user1 = userRepository.save(user1);

        User user2 = new User();
        user2.setUsername("janedoe5");
        user2.setFirstName("Jane");
        user2.setLastName("Doe");
        user2.setEmail("jdoe5000@gmail.com");
        user2.setPassword("TODO:needtoencryptthisinthefuture2");
        user2.setGender("Female");

        user2 = userRepository.save(user2);
    }

}
