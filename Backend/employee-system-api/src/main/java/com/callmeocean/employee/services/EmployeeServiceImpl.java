package com.callmeocean.employee.services;

import com.callmeocean.employee.entity.EmployeeEntity;
import com.callmeocean.employee.model.Employee;
import com.callmeocean.employee.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee createEmployee(Employee employee) {
        EmployeeEntity employeeEntity = new EmployeeEntity();

        /*employeeEntity.setId(employee.getId());
        employeeEntity.setFirstName(employee.getFirstName());
        employeeEntity.setLastName(employee.getLastName());
        employeeEntity.setEmail(employee.getEmail());*/

        /*Spring BeanUtils copyProperties method can only be used when source and destination objects have
         same type of properties and properties have primitive data types*/
        BeanUtils.copyProperties(employee, employeeEntity);

        employeeRepository.save(employeeEntity);

        return employee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();

        List<Employee> employees = new ArrayList<>();

        for (EmployeeEntity employeeEntity : employeeEntities) {
            Employee employee = new Employee();

            BeanUtils.copyProperties(employeeEntity, employee);
            employees.add(employee);
        }

        return employees;
    }

    @Override
    public boolean deleteEmployee(Long id) {

        try {
            EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
            employeeRepository.delete(employeeEntity);
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
