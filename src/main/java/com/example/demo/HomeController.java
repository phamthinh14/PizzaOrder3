package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Consumer;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SimpleEmailController simpleEmailController;

    @RequestMapping("/")
    public String land() {
        return "landing";
    }

    @RequestMapping("/order")
    public String homePage(Model model) {
        model.addAttribute("pizzaorders", orderRepository.findAllByUser(userService.getUser()));
        return "home";
    }

    @RequestMapping("/sendemail")
    public String sendEmail() {

        String content = "";
        for (PizzaOrder pizzaOrder : orderRepository.findAllByUser(userService.getUser())) {
            content += pizzaOrder.toString();
        }

        simpleEmailController.SendSimpleEmail(userService.getUser().getEmail(), content);
        return "mailTemplate";
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
        String customerTopThree = findTopToppings();
        model.addAttribute("pizzaorders", orderRepository.findAll());
        model.addAttribute("sum", sumOfTotalSales);
        model.addAttribute("toppings", customerTopThree);
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
        HashMap<String, Integer> toppingsList = new HashMap<>();

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

        toppingsList.put("Mushroom", countMushRoom);
        toppingsList.put("Onions", countOnions);
        toppingsList.put("GreenPepper", countGreenpepper);
        toppingsList.put("Bacon", countBacon);
        toppingsList.put("Pepperoni", countPepperoni);
        toppingsList.put("Sausage", countSausage);

        Map<String, Integer> hm1 = sortByValue(toppingsList);
        int count = 0;
        for (Map.Entry<String, Integer> en : hm1.entrySet()) {
            System.out.println("Key = " + en.getKey() +
                    ", Value = " + en.getValue());
            if (count < 3) {
                result += en.getKey() + " ";
                count++;
            }
        }
        return result;
    }

    public HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm) {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Integer>> list =
                new LinkedList<Map.Entry<String, Integer>>(hm.entrySet());

        // Sort the list
        Collections.sort(list, (o1, o2) -> (o2.getValue()).compareTo(o1.getValue()));

        // put data from sorted list to hashmap
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
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

    @RequestMapping("/favoritepizzas/{id}")
    public String showFavoritePizzaPage(@PathVariable("id") long id, Model model) {
        List<PizzaOrder> favoriteList = new ArrayList<>();
        favoriteList.add(orderRepository.findById(id).get());
        model.addAttribute("pizzaorders", favoriteList);
        return "favoritePizzaPage";
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
