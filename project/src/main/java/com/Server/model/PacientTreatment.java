package com.Server.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PacientTreatment {
    private int id;
    private String surname;
    private String firstname;
    private int pacientId;
    private int treatmentId;
    private String illness;
    private int drugId;
    private String drugName;
    private int dailyquantity;

    public PacientTreatment(String surname, String firstname, int pacientId, int treatmentId, String illness,
                            int drugId, String drugName, int dailyquantity) {
        this.surname = surname;
        this.firstname = firstname;
        this.pacientId = pacientId;
        this.treatmentId = treatmentId;
        this.illness = illness;
        this.drugId = drugId;
        this.drugName = drugName;
        this.dailyquantity = dailyquantity;
    }
}
