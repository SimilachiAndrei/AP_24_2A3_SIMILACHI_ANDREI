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
public class History {
    private int id;
    private int drug_id;
    private int quantity;
    private LocalDate data;
    private String transaction;

    public History(int drug_id, int quantity, String transaction, LocalDate data) {
        this.drug_id = drug_id;
        this.quantity = quantity;
        this.transaction = transaction;
        this.data = data;
    }
}
