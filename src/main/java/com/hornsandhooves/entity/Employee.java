package com.hornsandhooves.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by apriseko on 19.07.2017.
 */
@Entity
@Table(name = "EMPLOYEE")
@Data
@EqualsAndHashCode(exclude = {"department", "orders"})
@NoArgsConstructor
@RequiredArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Employee {
    @Id
    @GeneratedValue
    @Column(name = "EMPLOYEE_ID")
    @JsonProperty(value = "id", required = false)
    private Long id;

    @Column(name = "FIRST_NAME", nullable = false)
    @NonNull
    private String firstName;

    @Column(name = "MIDDLE_NAME")
    private String middleName;

    @Column(name = "LAST_NAME", nullable = false)
    @NonNull
    private String lastName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DEPARTMENT_ID")
    private Department department;

    @OneToMany(mappedBy = "employee")
    @JsonIgnore
    private List<Orders> orders = new ArrayList<>();
}
