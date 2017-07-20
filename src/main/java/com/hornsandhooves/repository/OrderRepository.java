package com.hornsandhooves.repository;

import com.hornsandhooves.entity.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by apriseko on 20.07.2017.
 */
public interface OrderRepository extends PagingAndSortingRepository<Orders, Long> {
    Page<List<Orders>> findByComplete(@Param("complete") Boolean complete, Pageable pageable);
    Page<List<Orders>> findByDepartmentId(@Param("departmentId")Long departmentId, Pageable pageable);
    Page<List<Orders>> findByEmployeeId(@Param("employeeId")Long employeeId, Pageable pageable);
}
