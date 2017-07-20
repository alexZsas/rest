package com.hornsandhooves.repository;

import com.hornsandhooves.entity.Department;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by apriseko on 19.07.2017.
 */
public interface DepartmentRepository extends PagingAndSortingRepository<Department, Long> {
    Department findByName(String name);
}
