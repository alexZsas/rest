package com.hornsandhooves.repository;

import com.hornsandhooves.entity.Furniture;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by apriseko on 20.07.2017.
 */
public interface FurnitureRepository extends PagingAndSortingRepository<Furniture, Long> {
    Furniture findByName(String name);
}
