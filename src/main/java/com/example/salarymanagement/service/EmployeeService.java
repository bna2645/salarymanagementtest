package com.example.salarymanagement.service;

import com.example.salarymanagement.entity.Employee;
import com.example.salarymanagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee createEmployee(Employee employee) throws Exception {
        if(employeeRepository.findByName(employee.getName()) != null) {
            throw new Exception("Unable to create. A User with name" + employee.getName() + " already exists");
        }
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public Employee updateEmployee(Long id, Employee employeeDetails) throws Exception {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new Exception("Unable to update. Employee not found"));
        employee.setName(employeeDetails.getName());
        employee.setAge(employeeDetails.getAge());
        employee.setSalary(employeeDetails.getSalary());

        return employeeRepository.save(employee);

    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    public Employee searchEmployeeByName(String name) {
        return employeeRepository.findByName(name);
    }
}
