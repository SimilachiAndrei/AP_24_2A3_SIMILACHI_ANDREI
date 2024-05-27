package com.Server.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Schedule {
    private int id;
    private int drug_id;
    private LocalDate data;
    private int quantity;

    public Schedule(int drug_id, int quantity, LocalDate data) {
        this.drug_id = drug_id;
        this.quantity = quantity;
        this.data = data;
    }
}
