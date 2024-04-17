package edu.ssc.hrs.entity.service;

import edu.ssc.hrs.entity.Customer;
import edu.ssc.hrs.entity.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Iterable<Customer> findAll() {
        return customerRepository.findAll(Sort.by("customerID"));
    }

    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    public void deleteAll() {
        customerRepository.deleteAll();
    }

    public long count() {
        return customerRepository.count();
    }

    public void saveAll(Iterable<Customer> customers) {
        customerRepository.saveAll(customers);
    }

    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }

    // Get a customer by ID
    public Customer getCustomerById(Long id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);

        if (customerOptional.isPresent()) {
            return customerOptional.get();
        } else {
            throw new RuntimeException("Customer not found with ID: " + id);
        }
    }

    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    // Update a customer
    public void updateCustomer(Customer customer) {
        // Check if the customer exists in the database
        if (customerRepository.existsById(customer.getCustomerID())) {
            // Update the customer details
            customerRepository.save(customer);
        } else {
            // Handle the case where the customer does not exist
            throw new RuntimeException("Customer not found with ID: " + customer.getCustomerID());
        }
    }
}
