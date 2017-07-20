package com.hornsandhooves.controller;

import com.hornsandhooves.entity.Department;
import com.hornsandhooves.entity.Employee;
import com.hornsandhooves.entity.Furniture;
import com.hornsandhooves.entity.Orders;
import com.hornsandhooves.repository.EmployeeRepository;
import com.hornsandhooves.repository.FurnitureRepository;
import com.hornsandhooves.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


/**
 * Created by apriseko on 20.07.2017.
 */
@RestController
@BasePathAwareController
public class OrderController {
    @Autowired
    private OrderRepository ordersRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private FurnitureRepository furnitureRepository;

    @RequestMapping(value = "add/order", method = RequestMethod.POST)
    public Orders addOrder(@RequestBody Orders orders) {
        Furniture f = orders.getFurniture();
        Furniture furniture = furnitureRepository.findByName(f.getName());
        orders.setFurniture(furniture);

        if(orders.getEmployee() == null) {
            orders.setDepartment(furniture.getDepartment());
        } else {
            orders.setDepartment(null);
            Employee e = orders.getEmployee();
            Employee employee = employeeRepository.findByFirstNameAndLastName(e.getFirstName(), e.getLastName());
            orders.setEmployee(employee);
        }

        orders.setOrderDate(new Date());
        orders.setComplete(false);
        ordersRepository.save(orders);

        return orders;
    }

    @RequestMapping(value = "orderses/{id}", method = RequestMethod.DELETE)
    public Orders deleteEmployeeFromOrder(@PathVariable Long id) {
        Orders orders = ordersRepository.findOne(id);
        orders.setEmployee(null);
        Department department = orders.getFurniture().getDepartment();
        orders.setDepartment(department);
        ordersRepository.save(orders);

        return orders;
    }
}
