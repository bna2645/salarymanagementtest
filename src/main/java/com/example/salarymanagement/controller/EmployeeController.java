package com.example.salarymanagement.controller;

import com.example.salarymanagement.entity.Employee;
import com.example.salarymanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@Controller

public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/")
    public String listEmployees(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployees());
        model.addAttribute("employee", new Employee());
        return "index";
    }

    @PostMapping("/add")
    public String addEmployee(@ModelAttribute Employee employee, Model model) {
        try {
            employeeService.createEmployee(employee);
            model.addAttribute("message", "User created successfully");
        } catch (Exception e) {
            model.addAttribute("error", "Error while creating User: " + e.getMessage());
        }
        model.addAttribute("employees", employeeService.getAllEmployees());
        model.addAttribute("employee", new Employee());
        return "index";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Employee employee = employeeService.getEmployeeById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));
        model.addAttribute("employee", employee);
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "index";
    }

    @PostMapping("/update/{id}")
    public String updateEmployee(@PathVariable Long id, @ModelAttribute Employee employee, Model model) {
        try {
            employeeService.updateEmployee(id, employee);
            model.addAttribute("message", "User updated successfully");
        } catch (Exception e) {
            model.addAttribute("error", "Error while updating User: " + e.getMessage());
        }
        model.addAttribute("employees", employeeService.getAllEmployees());
        model.addAttribute("employee", new Employee());
        return "index";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id, Model model) {
        employeeService.deleteEmployee(id);
        model.addAttribute("message", "User deleted successfully");
        model.addAttribute("employees", employeeService.getAllEmployees());
        model.addAttribute("employee", new Employee());
        return "index";
    }
}
