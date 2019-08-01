package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    OrderRepository orderRepository;

    @RequestMapping("/")
    public String homePage(Model model) {
        model.addAttribute("orders", orderRepository.findAll());
        return "home";
    }

    @GetMapping("/add")
    public String orderForm(Model model) {
        model.addAttribute("pizzaorder", new PizzaOrder());
        return "orderform";
    }

    @PostMapping("/process")
    public String processOrderForm(@Valid PizzaOrder pizzaOrder) {
//        if (result.hasErrors()) {
//            return "orderform";
//        }
        if (pizzaOrder.isGlutenFreeDough()) {
            pizzaOrder.setPrice(pizzaOrder.addUpTotal());
        }
        if (pizzaOrder.isNormalDough()) {
            pizzaOrder.setPrice(pizzaOrder.addUpTotal());
        }
        if (pizzaOrder.isRedSauce()) {
            pizzaOrder.setPrice(pizzaOrder.addUpTotal());
        }
        orderRepository.save(pizzaOrder);
        return "redirect:/";
    }

    @RequestMapping("/detail/{id}")
    public String showDetailPizza(@PathVariable("id") long id, Model model) {
        model.addAttribute("pizzaorder", orderRepository.findById(id).get());
        return "show";
    }

    @RequestMapping("/update/{id}")
    public String updateMessage(@PathVariable("id") long id, Model model) {
        model.addAttribute("pizzaorder", orderRepository.findById(id).get());
        return "orderform";
    }

    @RequestMapping("/delete/{id}")
    public String delMessage(@PathVariable("id") long id) {
        orderRepository.deleteById(id);
        return "redirect:/";
    }
}
