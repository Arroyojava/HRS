package edu.ssc.hrs.entity.service;

import edu.ssc.hrs.entity.Customer;
import edu.ssc.hrs.entity.Employee;
import edu.ssc.hrs.entity.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Iterable<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public long count() {
        return employeeRepository.count();
    }


    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }

    public Employee getEmployeeById(Long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);

        if (employeeOptional.isPresent()) {
            return employeeOptional.get();
        } else {
            throw new RuntimeException("Employee not found with ID: " + id);
        }
    }

    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    // Update a customer
    public void updateEmployee(Employee employee) {
        // Check if the customer exists in the database
        if (employeeRepository.existsById(employee.getEmployeeID())) {
            // Update the customer details
            employeeRepository.save(employee);
        } else {
            // Handle the case where the customer does not exist
            throw new RuntimeException("Employee not found with ID: " + employee.getEmployeeID());
        }
    }
}
