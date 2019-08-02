package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    OrderRepository orderRepository; //add for PizzaOrder

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String... strings) throws Exception{
        //add next line for final project
        PizzaOrder pizzaOrder = new PizzaOrder();


        roleRepository.save(new Role("USER"));
        roleRepository.save(new Role("ADMIN"));

        Role adminRole= roleRepository.findByRole("ADMIN");
        Role userRole=roleRepository.findByRole("USER");

        User user = new User("jim@jim.com","password", "jim",
                "Jimmerson", true, "jim");
        user.setRoles(Arrays.asList(userRole));
        userRepository.save(user);

        user = new User("admin@admin.com","password", "Admin",
                "User", true,"admin");
        user.setRoles(Arrays.asList(adminRole));
        userRepository.save(user);

        /*Not need default value for Pizza now, otherwise to update the following code
        Message message = new Message("Hi", "welcome to use the message apps", "admin", user);
        message.setUser(user); // admin
        messageRepository.save(message);

        message = new Message("Hi", "welcome to use the message apps", "admin", user);
        message.setUser(user); // admin
        messageRepository.save(message);

        message = new Message("Hi", "welcome to use the message apps", "admin", user);
        message.setUser(user); // admin
        messageRepository.save(message);

        // add jim as user
        user = userRepository.findByUsername("jim");
        message.setUser(user);

        messageRepository.save(message);
        */
    }
}
