package com.hornsandhooves.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by apriseko on 19.07.2017.
 */
@Entity
@Table(name = "FURNITURE")
@Data
@EqualsAndHashCode(exclude = {"department", "orders"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Furniture {
    @Id
    @Column(name = "FURNITURE_ID")
    @GeneratedValue
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne
    @JoinColumn(name = "DEPARTMENT_ID")
    private Department department;

    @OneToMany(mappedBy = "furniture", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Orders> orders = new ArrayList<>();
}
