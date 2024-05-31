package com.Server.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class DrugStatistics {
    private String name;
    private int quantity;
    private int fullstock;
    private double percentage;
    private int maxCapacity;
    private int freeSpace;

    public DrugStatistics(String drugName, int quantity, int fullstock, double percentage, int maxCapacity, int freeSpace) {
        this.name = drugName;
        this.quantity = quantity;
        this.fullstock = fullstock;
        this.percentage = percentage;
        this.maxCapacity = maxCapacity;
        this.freeSpace = freeSpace;
    }
}
