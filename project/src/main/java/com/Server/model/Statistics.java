package com.Server.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Statistics {
    private String drugName;
    private int totalQuantity;
    private int averageDailyConsumption;
    private int daysUntilDepletion;
    private int percentage;

    public Statistics(String drugName, int totalQuantity, int averageDailyConsumption, int daysUntilDepletion, int percentage) {
        this.drugName = drugName;
        this.totalQuantity = totalQuantity;
        this.averageDailyConsumption = averageDailyConsumption;
        this.daysUntilDepletion = daysUntilDepletion;
        this.percentage = percentage;
    }
}
