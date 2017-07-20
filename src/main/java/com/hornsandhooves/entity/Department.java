package com.hornsandhooves.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by apriseko on 19.07.2017.
 */
@Entity
@Table(name = "DEPARTMENT")
@Data
@EqualsAndHashCode(exclude = {"employees", "furnitures", "orders"})
@NoArgsConstructor
@RequiredArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Department {
    @Id
    @Column(name = "DEPARTMENT_ID")
    @GeneratedValue
    private Long id;

    @Column(name = "NAME")
    @NonNull
    private String name;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Employee> employees = new HashSet<>();

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Furniture> furnitures = new HashSet<>();

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Orders> orders = new ArrayList<>();
}
