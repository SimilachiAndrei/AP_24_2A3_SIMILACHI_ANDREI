package com.Server.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Treatment {
    private int id;
    private String illness;
    private int drug_id;
    private int dailyquantity;

    public Treatment(String illness, int drug_id,int dailyquantity) {
        this.illness = illness;
        this.drug_id = drug_id;
        this.dailyquantity=dailyquantity;
    }
}

