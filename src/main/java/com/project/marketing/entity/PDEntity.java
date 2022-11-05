package com.project.marketing.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@Table(name = "sample_inventory")
public class PDEntity {

    private String code;
    private String name;
    private String batch;
    private int stock;
    private int deal;
    private int free;
    private double mrp;
    private double rate;
    private Date exp;
    private String company;
    private String supplier;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int id;
}
