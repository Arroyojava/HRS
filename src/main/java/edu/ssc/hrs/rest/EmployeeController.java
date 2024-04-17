package edu.ssc.hrs.rest;

import edu.ssc.hrs.entity.Customer;
import edu.ssc.hrs.entity.Employee;
import edu.ssc.hrs.entity.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    private final InventoryService inventoryService;
    private final OrderService orderService;
    private final CustomerService customerService;
    private final EmployeeService employeeService;
    private final LocationService locationService;

    public EmployeeController(InventoryService inventoryService, OrderService orderService,
                              CustomerService customerService, EmployeeService employeeService,
                              LocationService locationService) {
        this.inventoryService = inventoryService;
        this.orderService = orderService;
        this.customerService = customerService;
        this.employeeService = employeeService;
        this.locationService = locationService;
    }

    @GetMapping(value = "")
    public String index(Model model) {
        return "employee-panel";
    }

    @GetMapping("/inventory")
    public String manageInventory(Model model) {
        model.addAttribute("inventoryList", inventoryService.findAll());
        return "management-panels/inventory-management";
    }

    @GetMapping("/orders")
    public String manageOrders(Model model) {
        model.addAttribute("orderList", orderService.findAll());
        return "management-panels/order-management";
    }

    @GetMapping("/customers")
    public String manageCustomers(Model model) {
        model.addAttribute("customerList", customerService.findAll());
        return "management-panels/customer-management";
    }

    @GetMapping("customers/edit/{id}")
    public String showEditCustomerForm(@PathVariable Long id, Model model) {
        Customer customer = customerService.getCustomerById(id);
        model.addAttribute("customer", customer);
        return "edit-customer-form";
    }

    @PostMapping("customers/update")
    public String updateCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.updateCustomer(customer);
        return "redirect:/employee/customers";
    }

    @GetMapping("/employees")
    public String manageEmployees(Model model) {
        model.addAttribute("employeeList", employeeService.findAll());
        return "management-panels/employees-management";
    }

    @GetMapping("/employees/edit/{id}")
    public String showEditEmployeeForm(@PathVariable Long id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "/edit-employee-form";
    }

    @PostMapping("/employees/update")
    public String updateEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.updateEmployee(employee);
        return "redirect:/employee/employees";
    }

    @GetMapping("/locations")
    public String manageLocations(Model model) {
        model.addAttribute("locationList", locationService.findAll());
        return "management-panels/location-management";
    }

    @PostMapping("/orders/updateStatus")
    public String updateOrderStatus(
            @RequestParam("orderId") Long orderId,
            @RequestParam("newStatus") String newStatus
    ) {
        orderService.updateOrderStatus(orderId, newStatus);
        return "redirect:/employee/orders";
    }


}
