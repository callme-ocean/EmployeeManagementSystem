package com.callmeocean.employee.controller;

import com.callmeocean.employee.model.Employee;
import com.callmeocean.employee.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3001")
@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/add")
    public Employee createEmployee(@RequestBody Employee employee) {

        return employeeService.createEmployee(employee);
    }

    @GetMapping("/getall")
    public List<Employee> getAllEmployees() {

        return employeeService.getAllEmployees();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable("id") Long id) {
        boolean deleteStatus = false;

        deleteStatus = employeeService.deleteEmployee(id);
        Map<String, Boolean> responseMap = new HashMap<>();
        responseMap.put("deleted", deleteStatus);

        return ResponseEntity.ok(responseMap);
    }
}
