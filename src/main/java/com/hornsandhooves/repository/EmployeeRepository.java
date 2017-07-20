package com.hornsandhooves.repository;

import com.hornsandhooves.entity.Employee;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by apriseko on 19.07.2017.
 */
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {
    Employee findByFirstNameAndLastName(String firstName, String lastName);
}
