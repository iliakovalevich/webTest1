package org.example.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.example.domain.Order;
import org.example.service.OrderServiceImpl;


@Controller
public class OrderController {

    private OrderServiceImpl orderService = new OrderServiceImpl();

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getOrderPage(Model model) {
        List<Order> orders = orderService.getAll();
        model.addAttribute("orderList", orders);
        return "order";
    }

    @RequestMapping(value = "/add-new-order", method = RequestMethod.GET)
    public String addNewOrderPage(Model model) {
        List<Order> menu = orderService.getMenu();
        model.addAttribute("menuList", menu);
        return "addNewOrder";
    }

    @RequestMapping(value = "/add-new-order", method = RequestMethod.POST)
    public String addNewOrder(@RequestParam(value = "title") String title, @RequestParam(value = "price") Double price) {
        Order order = new Order();
        order.setTitle(title);
        order.setPrice(price);
        orderService.save(order);
        return "redirect:/";
    }

    @RequestMapping("addMenu/{id}")
    public String addItemFromMenu(@PathVariable Integer id) {
        orderService.addItemFromMenu(id);
        return "redirect:/";
    }

    @RequestMapping("delete/{id}")
    public String deleteItem(@PathVariable Integer id) {
        orderService.delete(id);
        return "redirect:/";
    }
}