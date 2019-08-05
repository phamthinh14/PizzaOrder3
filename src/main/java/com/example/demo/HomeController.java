package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/")
    public String land() {
        return "landing";
    }

    @RequestMapping("/order")
    public String homePage(Model model) {
        model.addAttribute("pizzaorders", orderRepository.findAllByUser(userService.getUser()));
        return "home";
    }

    @RequestMapping("/adminview")
    public String adminView(Model model) {
//List Customers information
        model.addAttribute("customersInfo", userRepository.findAll());
        return "admindisplayCustomersInfo";
    }

    @RequestMapping("/adminedit")
    public String adminEdit(Model model) {
//        Update Detail and Delete a customer's order
        double sumOfTotalSales = totalSales();

        model.addAttribute("pizzaorders", orderRepository.findAll());
        model.addAttribute("sum", sumOfTotalSales);
        return "adminEditCustomersOrder";
    }

    private String findTopToppings() {
        String result = "";
        int countMushRoom = 0;
        int countOnions = 0;
        int countGreenpepper = 0;
        int countBacon = 0;
        int countPepperoni = 0;
        int countSausage = 0;

        for (PizzaOrder order : orderRepository.findAll()) {
            if (order.isMushroom()) {
                countMushRoom++;
            }
            if (order.isOnions()) {
                countOnions++;
            }
            if (order.isGreenPepper()) {
                countGreenpepper++;
            }
            if (order.isBacon()) {
                countBacon++;
            }
            if (order.isPepperoni()) {
                countPepperoni++;
            }
            if (order.isSausage()) {
                countSausage++;
            }
        }

        return null;
    }

    private double totalSales() {
        double sumOfTotalSales = 0;
        List<Double> salesContainer = new ArrayList<>();
        orderRepository.findAll().forEach(pizzaOrder -> salesContainer.add(pizzaOrder.getPrice()));
        for (int i = 0; i < salesContainer.size(); i++) {
            sumOfTotalSales += salesContainer.get(i);
        }
        return sumOfTotalSales;
    }

    @RequestMapping("/inputusername")
    public String userNameInputForm() {
//        This method will allow an admin to input a String of username to find
        return "userNameInputForm";
    }

    @RequestMapping("/processinputusername")
    public String findUserName(@RequestParam("nameToFind") String nameToFind, Model model) {
//        When the admin submit that username(String) it will be pass to this method to find that user
        //When we found a User, it will display first and last name, phone number, and email of that user
        //If the user is not found in the database, it will print "User is not found"
        User tempUser = userRepository.findByUsername(nameToFind);
        model.addAttribute("users", tempUser);
        return "listUserFoundByName";
    }

    @GetMapping("/add")
    public String orderForm(Model model) {
        model.addAttribute("pizzaorder", new PizzaOrder());
        return "orderform";
    }

    @PostMapping("/process")
    public String processOrderForm(@Valid PizzaOrder pizzaOrder) {

        if (pizzaOrder.isGlutenFreeDough()) {
            pizzaOrder.setPrice(pizzaOrder.addUpTotal());
        }
        if (pizzaOrder.isNormalDough()) {
            pizzaOrder.setPrice(pizzaOrder.addUpTotal());
        }
        if (pizzaOrder.isRedSauce()) {
            pizzaOrder.setPrice(pizzaOrder.addUpTotal());
        }
        if (pizzaOrder.isWhiteSauce()) {
            pizzaOrder.setPrice(pizzaOrder.addUpTotal());
        }
        if (pizzaOrder.isCheese()) {
            pizzaOrder.setPrice(pizzaOrder.addUpTotal());
        }
        if (pizzaOrder.isMushroom()) {
            pizzaOrder.setPrice(pizzaOrder.addUpTotal());
        }
        if (pizzaOrder.isOnions()) {
            pizzaOrder.setPrice(pizzaOrder.addUpTotal());
        }
        if (pizzaOrder.isGreenPepper()) {
            pizzaOrder.setPrice(pizzaOrder.addUpTotal());
        }
        if (pizzaOrder.isBacon()) {
            pizzaOrder.setPrice(pizzaOrder.addUpTotal());
        }
        if (pizzaOrder.isPepperoni()) {
            pizzaOrder.setPrice(pizzaOrder.addUpTotal());
        }
        if (pizzaOrder.isSausage()) {
            pizzaOrder.setPrice(pizzaOrder.addUpTotal());
        }
        if (pizzaOrder.isSmallSize()) {
            pizzaOrder.setPrice(pizzaOrder.addUpTotal());
        }
        if (pizzaOrder.isMediumSize()) {
            pizzaOrder.setPrice(pizzaOrder.addUpTotal() + 1);
        }
        if (pizzaOrder.isLargeSize()) {
            pizzaOrder.setPrice(pizzaOrder.addUpTotal() + 2);
        }

//        User currentUser = ;
        if (pizzaOrder.getUser() != null) {
            pizzaOrder.setUser(pizzaOrder.getUser());
        } else {
            pizzaOrder.setUser(userService.getUser());
        }

        orderRepository.save(pizzaOrder);
        return "redirect:/order";
    }

    @RequestMapping("/detail/{id}")
    public String showDetailPizza(@PathVariable("id") long id, Model model) {
        model.addAttribute("pizzaorder", orderRepository.findById(id).get());
        return "show";
    }

    @RequestMapping("/update/{id}")
    public String updateMessage(@PathVariable("id") long id, Model model) {
        model.addAttribute("pizzaorder", orderRepository.findById(id).get());
        return "updateorderform";
    }

    @RequestMapping("/delete/{id}")
    public String delMessage(@PathVariable("id") long id) {
        orderRepository.deleteById(id);
        return "redirect:/order";
    }
}
