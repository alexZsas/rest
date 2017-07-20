package com.hornsandhooves.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by apriseko on 20.07.2017.
 */
@Entity
@Table(name = "ORDERS")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Orders {
    @Id
    @Column(name = "ORDER_ID")
    @GeneratedValue
    private Long id;

    @Column(name = "ORDER_NAME", nullable = false)
    private String orderName;

    @ManyToOne
    @JoinColumn(name = "DEPARTMENT_ID")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "FURNITURE_ID")
    private Furniture furniture;

    @ManyToOne
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;

    @Column(name = "ORDER_DATE", nullable = false)
    @JsonDeserialize(using = DateDeserializers.DateDeserializer.class)
    @JsonSerialize(using = DateSerializer.class)
    @JsonFormat(pattern = "dd.MM.yyyy hh:mm")
    private Date orderDate;

    @Column(name = "COMPLETE_DATE", nullable = false)
    @JsonDeserialize(using = DateDeserializers.DateDeserializer.class)
    @JsonSerialize(using = DateSerializer.class)
    @JsonFormat(pattern = "dd.MM.yyyy hh:mm")
    private Date completeDate;

    @Column(name = "COMPLETE")
    private Boolean complete;

    @Transient
    private Integer remainingDays;

    @Transient
    private Integer remainingHours;

    public void setRemainingDays() {
        long reminingTime = getRemainingTime();
        this.remainingDays = (int) (reminingTime / (24 * 60 * 60 * 1000));
    }

    public void setRemainingHours() {
        long reminingTime = getRemainingTime();
        this.remainingHours = (int) (reminingTime / (60 * 60 * 1000) % 24);
    }

    public Integer getRemainingDays() {
        if(this.remainingDays == null) {
            if(isComplete()) {
                setRemainingDays(0);
            } else {
                setRemainingDays();
            }
        }
        return remainingDays;
    }

    public Integer getRemainingHours() {
        if(this.remainingHours == null) {
            if(isComplete()) {
                setRemainingHours(0);
            } else {
                setRemainingHours();
            }
        }
        return remainingHours;
    }

    public boolean isComplete() {
        return complete;
    }

    private long getRemainingTime() {
        return getCompleteDate().getTime() - System.currentTimeMillis();
    }
}
