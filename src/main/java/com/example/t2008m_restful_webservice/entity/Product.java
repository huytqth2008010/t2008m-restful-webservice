package com.example.t2008m_restful_webservice.entity;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Product {
    private int id;
    private String name;
    private Double price;
}
