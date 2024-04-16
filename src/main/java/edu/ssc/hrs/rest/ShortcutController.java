package edu.ssc.hrs.rest;

import edu.ssc.hrs.entity.Order;
import edu.ssc.hrs.entity.service.DeliveryService;
import edu.ssc.hrs.entity.service.InventoryService;
import edu.ssc.hrs.entity.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/shortcuts")
public class ShortcutController {

    private final InventoryService inventoryService;
    private final OrderService orderService;

    public ShortcutController(InventoryService inventoryService, OrderService orderService, DeliveryService deliveryService) {
        this.inventoryService = inventoryService;
        this.orderService = orderService;
    }

    @GetMapping(value = "")
    public String index(Model model) {
        return "shortcut-panel";
    }

    @GetMapping("/inventory")
    public String listInventory(Model model) {
        model.addAttribute("inventoryServices", inventoryService.findAll());
        return "management-panels/inventory-management";
    }

    @PostMapping("/createOrder")
    public String createOrder(@RequestParam("productServiceIds") List<Long> productServiceIds) {
        Order order = orderService.createOrder(productServiceIds);
        return "redirect:/customer/order/" + order.getOrderID();
    }

    @GetMapping("/order/{orderId}")
    public String viewOrder(@PathVariable Long orderId, Model model) {
        model.addAttribute("order", orderService.findById(orderId));
        return "management-panels/order-management";
    }

    @GetMapping("/order-management/{orderId}")
    public String trackDelivery(@PathVariable Long orderId, Model model) {
        model.addAttribute("delivery",orderService.findById(orderId));
        return "management-panels/order-management";
    }
}
