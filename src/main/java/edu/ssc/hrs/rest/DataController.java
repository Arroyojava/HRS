package edu.ssc.hrs.rest;

import edu.ssc.hrs.entity.Customer;
import edu.ssc.hrs.entity.repository.CustomerRepository;
import edu.ssc.hrs.entity.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/")
public class DataController {
    private final CustomerRepository customerRepository;
    private final LocationService locationService;
    private final InventoryService inventoryService;
    private final EmployeeService employeeService;
    private final OrderService orderService;
    private final CustomerService customerService;

    public DataController(CustomerService customerService, CustomerRepository customerRepository, LocationService locationService, InventoryService inventoryService, EmployeeService employeeService, OrderService orderService) {
        this.customerRepository = customerRepository;
        this.customerService = customerService;
        this.locationService = locationService;
        this.inventoryService = inventoryService;
        this.employeeService = employeeService;
        this.orderService = orderService;
    }

    @GetMapping(value = "")
    public String index(Model model) {
        model.addAttribute("customerCount", customerService.count());
        model.addAttribute("locationCount", locationService.count());
        model.addAttribute("employeeCount", employeeService.count());
        model.addAttribute("inventoryCount", inventoryService.count());
        model.addAttribute("orderCount", orderService.count());
        return "index";
    }


//    @PostMapping("/upload")
//    public String uploadAll() {
//
//        List<Customer> customerList = JsonService.jsonToList("customer.json", Customer[].class);
//        List<Location> locationList = JsonService.jsonToList("location.json", Location[].class);
//
//        customerService.saveAll(customerList);
//        locationService.saveAll(locationList);
//
//        return "redirect:/";
//    }
//
//    @PostMapping("/delete")
//    public String deleteAll() {
//        customerService.deleteAll();
//
//        return "redirect:/";
//    }
}