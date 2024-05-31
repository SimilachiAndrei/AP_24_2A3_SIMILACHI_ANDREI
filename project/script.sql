BEGIN TRANSACTION;

DROP TABLE IF EXISTS pacient_treatment;
DROP TABLE IF EXISTS treatments;
DROP TABLE IF EXISTS pacients;
DROP TABLE IF EXISTS history;
DROP TABLE IF EXISTS schedule;
DROP TABLE IF EXISTS drugs;
DROP FUNCTION IF EXISTS get_drug_statistics_and_prediction;
DROP FUNCTION IF EXISTS calculate_drug_statistics();


CREATE TABLE drugs (
   id SERIAL PRIMARY KEY,
   name TEXT NOT NULL,
   quantity INTEGER,
   fullstock INTEGER
);

CREATE TABLE schedule (
  id SERIAL PRIMARY KEY,
  drug_id INTEGER,
  data DATE,
  quantity INTEGER,
  FOREIGN KEY (drug_id) REFERENCES drugs(id)
);

CREATE TABLE history (
  id SERIAL PRIMARY KEY,
  drug_id INTEGER,
  data DATE,
  quantity INTEGER,
  transaction TEXT,
  FOREIGN KEY (drug_id) REFERENCES drugs(id)
);

CREATE TABLE pacients (
  id SERIAL PRIMARY KEY,
  surname TEXT,
  firstname TEXT
);

CREATE TABLE treatments (
  id SERIAL PRIMARY KEY,
  illness TEXT,
  drug_id INTEGER,
  dailyquantity INTEGER,
  FOREIGN KEY (drug_id) REFERENCES drugs(id)
);

CREATE TABLE pacient_treatment (
  id SERIAL PRIMARY KEY,
  pacient_id INTEGER,
  treatment_id INTEGER,
  FOREIGN KEY (pacient_id) REFERENCES pacients(id),
  FOREIGN KEY (treatment_id) REFERENCES treatments(id)
);

-- Function to get drug statistics and predictions for all drugs
CREATE OR REPLACE FUNCTION get_drug_statistics_and_prediction()
RETURNS TABLE (
    drug_name TEXT,
    total_quantity INTEGER,
    average_daily_consumption NUMERIC,
    days_until_depletion NUMERIC,
    percentage NUMERIC
)
LANGUAGE plpgsql
AS $$
BEGIN
    RETURN QUERY
    WITH consumption AS (
        SELECT
            h.drug_id,
            d.name AS drug_name,
            SUM(h.quantity) AS total_consumed,
            AVG(h.quantity) AS avg_daily_consumption
        FROM history h
        JOIN drugs d ON h.drug_id = d.id
        GROUP BY h.drug_id, d.name
    ), stock AS (
        SELECT
            id AS drug_id,
            quantity AS total_quantity,
            fullstock
        FROM drugs
    )
    SELECT
        c.drug_name,
        s.total_quantity,
        c.avg_daily_consumption,
        CASE
            WHEN c.avg_daily_consumption > 0 THEN s.total_quantity / c.avg_daily_consumption
            ELSE NULL
        END AS days_until_depletion,
        (s.total_quantity::NUMERIC / s.fullstock) * 100 AS percentage
    FROM consumption c
    JOIN stock s ON c.drug_id = s.drug_id;
END;
$$;

CREATE OR REPLACE FUNCTION calculate_drug_statistics()
RETURNS TABLE (
    name TEXT,
    quantity INTEGER,
    fullstock INTEGER,
    percentage NUMERIC,
    max_capacity INTEGER,
    free_space INTEGER
) LANGUAGE plpgsql
AS $$
DECLARE
    max_capacity INTEGER;
    current_total_quantity INTEGER;
    free_space INTEGER;
BEGIN
    -- Calculate the total maximum capacity and current total quantity
    SELECT SUM(dr.fullstock), SUM(dr.quantity) INTO max_capacity, current_total_quantity FROM drugs dr;

    -- Calculate free space
    free_space := max_capacity - current_total_quantity;

    -- Return the detailed statistics
    RETURN QUERY
    SELECT
        dr.name,
        dr.quantity,
        dr.fullstock,
        (dr.quantity::NUMERIC / max_capacity) * 100 AS percentage,
        max_capacity,
        free_space
    FROM drugs dr;
END;
$$;


COMMIT;

-- Insert data into the tables
BEGIN TRANSACTION;

-- Insert data into drugs table
-- Insert data into drugs table
INSERT INTO drugs (name, quantity, fullstock) VALUES ('Aspirin', 100, 125);
INSERT INTO drugs (name, quantity, fullstock) VALUES ('Ibuprofen', 200, 400);
INSERT INTO drugs (name, quantity, fullstock) VALUES ('Paracetamol', 150, 600);
INSERT INTO drugs (name, quantity, fullstock) VALUES ('Amoxicillin', 50, 75);
INSERT INTO drugs (name, quantity, fullstock) VALUES ('Ciprofloxacin', 80, 100);
INSERT INTO drugs (name, quantity, fullstock) VALUES ('Azithromycin', 120, 150);
INSERT INTO drugs (name, quantity, fullstock) VALUES ('Metformin', 90, 120);
INSERT INTO drugs (name, quantity, fullstock) VALUES ('Lisinopril', 110, 140);
INSERT INTO drugs (name, quantity, fullstock) VALUES ('Amlodipine', 70, 100);
INSERT INTO drugs (name, quantity, fullstock) VALUES ('Simvastatin', 85, 110);
INSERT INTO drugs (name, quantity, fullstock) VALUES ('Omeprazole', 60, 90);
INSERT INTO drugs (name, quantity, fullstock) VALUES ('Losartan', 40, 80);
INSERT INTO drugs (name, quantity, fullstock) VALUES ('Gabapentin', 95, 130);
INSERT INTO drugs (name, quantity, fullstock) VALUES ('Hydrochlorothiazide', 75, 110);
INSERT INTO drugs (name, quantity, fullstock) VALUES ('Montelukast', 65, 100);
INSERT INTO drugs (name, quantity, fullstock) VALUES ('Alprazolam', 85, 95);
INSERT INTO drugs (name, quantity, fullstock) VALUES ('Prednisone', 130, 160);
INSERT INTO drugs (name, quantity, fullstock) VALUES ('Sildenafil', 70, 90);
INSERT INTO drugs (name, quantity, fullstock) VALUES ('Fluoxetine', 55, 85);
INSERT INTO drugs (name, quantity, fullstock) VALUES ('Clonazepam', 60, 80);
INSERT INTO drugs (name, quantity, fullstock) VALUES ('Tramadol', 140, 170);
INSERT INTO drugs (name, quantity, fullstock) VALUES ('Sertraline', 125, 150);
INSERT INTO drugs (name, quantity, fullstock) VALUES ('Zolpidem', 50, 70);
INSERT INTO drugs (name, quantity, fullstock) VALUES ('Cyclobenzaprine', 100, 130);
INSERT INTO drugs (name, quantity, fullstock) VALUES ('Citalopram', 110, 140);
INSERT INTO drugs (name, quantity, fullstock) VALUES ('Trazodone', 120, 160);
INSERT INTO drugs (name, quantity, fullstock) VALUES ('Bupropion', 95, 130);
INSERT INTO drugs (name, quantity, fullstock) VALUES ('Lorazepam', 105, 135);
INSERT INTO drugs (name, quantity, fullstock) VALUES ('Propranolol', 115, 145);
INSERT INTO drugs (name, quantity, fullstock) VALUES ('Meloxicam', 125, 155);

-- Insert data into schedule table
INSERT INTO schedule (drug_id, data, quantity) VALUES (1, '2024-05-01', 10);
INSERT INTO schedule (drug_id, data, quantity) VALUES (2, '2024-05-02', 20);
INSERT INTO schedule (drug_id, data, quantity) VALUES (3, '2024-05-03', 15);
INSERT INTO schedule (drug_id, data, quantity) VALUES (4, '2024-05-04', 5);
INSERT INTO schedule (drug_id, data, quantity) VALUES (5, '2024-05-05', 8);
INSERT INTO schedule (drug_id, data, quantity) VALUES (6, '2024-05-06', 12);
INSERT INTO schedule (drug_id, data, quantity) VALUES (7, '2024-05-07', 14);
INSERT INTO schedule (drug_id, data, quantity) VALUES (8, '2024-05-08', 18);
INSERT INTO schedule (drug_id, data, quantity) VALUES (9, '2024-05-09', 11);
INSERT INTO schedule (drug_id, data, quantity) VALUES (10, '2024-05-10', 9);
INSERT INTO schedule (drug_id, data, quantity) VALUES (11, '2024-05-11', 13);
INSERT INTO schedule (drug_id, data, quantity) VALUES (12, '2024-05-12', 6);
INSERT INTO schedule (drug_id, data, quantity) VALUES (13, '2024-05-13', 7);
INSERT INTO schedule (drug_id, data, quantity) VALUES (14, '2024-05-14', 16);
INSERT INTO schedule (drug_id, data, quantity) VALUES (15, '2024-05-15', 5);
INSERT INTO schedule (drug_id, data, quantity) VALUES (16, '2024-05-16', 9);
INSERT INTO schedule (drug_id, data, quantity) VALUES (17, '2024-05-17', 4);
INSERT INTO schedule (drug_id, data, quantity) VALUES (18, '2024-05-18', 10);
INSERT INTO schedule (drug_id, data, quantity) VALUES (19, '2024-05-19', 17);
INSERT INTO schedule (drug_id, data, quantity) VALUES (20, '2024-05-20', 5);
INSERT INTO schedule (drug_id, data, quantity) VALUES (21, '2024-05-21', 15);
INSERT INTO schedule (drug_id, data, quantity) VALUES (22, '2024-05-22', 8);
INSERT INTO schedule (drug_id, data, quantity) VALUES (23, '2024-05-23', 7);
INSERT INTO schedule (drug_id, data, quantity) VALUES (24, '2024-05-24', 14);
INSERT INTO schedule (drug_id, data, quantity) VALUES (25, '2024-05-25', 6);
INSERT INTO schedule (drug_id, data, quantity) VALUES (26, '2024-05-26', 12);
INSERT INTO schedule (drug_id, data, quantity) VALUES (27, '2024-05-27', 9);
INSERT INTO schedule (drug_id, data, quantity) VALUES (28, '2024-05-28', 11);
INSERT INTO schedule (drug_id, data, quantity) VALUES (29, '2024-05-29', 13);
INSERT INTO schedule (drug_id, data, quantity) VALUES (30, '2024-05-30', 10);

-- Insert data into history table
INSERT INTO history (drug_id, data, quantity, transaction) VALUES (1, '2024-05-01', 10, 'Dispensed');
INSERT INTO history (drug_id, data, quantity, transaction) VALUES (2, '2024-05-02', 20, 'Received');
INSERT INTO history (drug_id, data, quantity, transaction) VALUES (3, '2024-05-03', 15, 'Dispensed');
INSERT INTO history (drug_id, data, quantity, transaction) VALUES (4, '2024-05-04', 5, 'Received');
INSERT INTO history (drug_id, data, quantity, transaction) VALUES (5, '2024-05-05', 8, 'Dispensed');
INSERT INTO history (drug_id, data, quantity, transaction) VALUES (6, '2024-05-06', 12, 'Received');
INSERT INTO history (drug_id, data, quantity, transaction) VALUES (7, '2024-05-07', 14, 'Dispensed');
INSERT INTO history (drug_id, data, quantity, transaction) VALUES (8, '2024-05-08', 18, 'Received');
INSERT INTO history (drug_id, data, quantity, transaction) VALUES (9, '2024-05-09', 11, 'Dispensed');
INSERT INTO history (drug_id, data, quantity, transaction) VALUES (10, '2024-05-10', 9, 'Received');
INSERT INTO history (drug_id, data, quantity, transaction) VALUES (11, '2024-05-11', 13, 'Dispensed');
INSERT INTO history (drug_id, data, quantity, transaction) VALUES (12, '2024-05-12', 6, 'Received');
INSERT INTO history (drug_id, data, quantity, transaction) VALUES (13, '2024-05-13', 7, 'Dispensed');
INSERT INTO history (drug_id, data, quantity, transaction) VALUES (14, '2024-05-14', 16, 'Received');
INSERT INTO history (drug_id, data, quantity, transaction) VALUES (15, '2024-05-15', 5, 'Dispensed');
INSERT INTO history (drug_id, data, quantity, transaction) VALUES (16, '2024-05-16', 9, 'Received');
INSERT INTO history (drug_id, data, quantity, transaction) VALUES (17, '2024-05-17', 4, 'Dispensed');
INSERT INTO history (drug_id, data, quantity, transaction) VALUES (18, '2024-05-18', 10, 'Received');
INSERT INTO history (drug_id, data, quantity, transaction) VALUES (19, '2024-05-19', 17, 'Dispensed');
INSERT INTO history (drug_id, data, quantity, transaction) VALUES (20, '2024-05-20', 5, 'Received');
INSERT INTO history (drug_id, data, quantity, transaction) VALUES (21, '2024-05-21', 15, 'Dispensed');
INSERT INTO history (drug_id, data, quantity, transaction) VALUES (22, '2024-05-22', 8, 'Received');
INSERT INTO history (drug_id, data, quantity, transaction) VALUES (23, '2024-05-23', 7, 'Dispensed');
INSERT INTO history (drug_id, data, quantity, transaction) VALUES (24, '2024-05-24', 14, 'Received');
INSERT INTO history (drug_id, data, quantity, transaction) VALUES (25, '2024-05-25', 6, 'Dispensed');
INSERT INTO history (drug_id, data, quantity, transaction) VALUES (26, '2024-05-26', 12, 'Received');
INSERT INTO history (drug_id, data, quantity, transaction) VALUES (27, '2024-05-27', 9, 'Dispensed');
INSERT INTO history (drug_id, data, quantity, transaction) VALUES (28, '2024-05-28', 11, 'Received');
INSERT INTO history (drug_id, data, quantity, transaction) VALUES (29, '2024-05-29', 13, 'Dispensed');
INSERT INTO history (drug_id, data, quantity, transaction) VALUES (30, '2024-05-30', 10, 'Received');

-- Insert data into pacients table
INSERT INTO pacients (surname, firstname) VALUES ('Doe', 'John');
INSERT INTO pacients (surname, firstname) VALUES ('Smith', 'Jane');
INSERT INTO pacients (surname, firstname) VALUES ('Brown', 'Charlie');
INSERT INTO pacients (surname, firstname) VALUES ('Davis', 'Alice');
INSERT INTO pacients (surname, firstname) VALUES ('Johnson', 'Bob');
INSERT INTO pacients (surname, firstname) VALUES ('White', 'Megan');
INSERT INTO pacients (surname, firstname) VALUES ('Clark', 'Anna');
INSERT INTO pacients (surname, firstname) VALUES ('King', 'Henry');
INSERT INTO pacients (surname, firstname) VALUES ('Wright', 'Oliver');
INSERT INTO pacients (surname, firstname) VALUES ('Hall', 'Sophie');
INSERT INTO pacients (surname, firstname) VALUES ('Green', 'Emily');
INSERT INTO pacients (surname, firstname) VALUES ('Adams', 'Michael');
INSERT INTO pacients (surname, firstname) VALUES ('Baker', 'Chloe');
INSERT INTO pacients (surname, firstname) VALUES ('Harris', 'David');
INSERT INTO pacients (surname, firstname) VALUES ('Nelson', 'Grace');
INSERT INTO pacients (surname, firstname) VALUES ('Carter', 'Samuel');
INSERT INTO pacients (surname, firstname) VALUES ('Mitchell', 'Ella');
INSERT INTO pacients (surname, firstname) VALUES ('Perez', 'William');
INSERT INTO pacients (surname, firstname) VALUES ('Roberts', 'Ava');
INSERT INTO pacients (surname, firstname) VALUES ('Morris', 'Liam');
INSERT INTO pacients (surname, firstname) VALUES ('Murphy', 'Sophia');
INSERT INTO pacients (surname, firstname) VALUES ('Cook', 'Mason');
INSERT INTO pacients (surname, firstname) VALUES ('Rogers', 'Isabella');
INSERT INTO pacients (surname, firstname) VALUES ('Reed', 'James');
INSERT INTO pacients (surname, firstname) VALUES ('Bailey', 'Charlotte');
INSERT INTO pacients (surname, firstname) VALUES ('Cooper', 'Lucas');
INSERT INTO pacients (surname, firstname) VALUES ('Bell', 'Amelia');
INSERT INTO pacients (surname, firstname) VALUES ('Kelly', 'Benjamin');
INSERT INTO pacients (surname, firstname) VALUES ('Howard', 'Evelyn');
INSERT INTO pacients (surname, firstname) VALUES ('Ward', 'Daniel');

-- Insert data into treatments table
INSERT INTO treatments (illness, drug_id, dailyquantity) VALUES ('Headache', 1, 2);
INSERT INTO treatments (illness, drug_id, dailyquantity) VALUES ('Inflammation', 2, 3);
INSERT INTO treatments (illness, drug_id, dailyquantity) VALUES ('Fever', 3, 1);
INSERT INTO treatments (illness, drug_id, dailyquantity) VALUES ('Infection', 4, 2);
INSERT INTO treatments (illness, drug_id, dailyquantity) VALUES ('Bacterial Infection', 5, 3);
INSERT INTO treatments (illness, drug_id, dailyquantity) VALUES ('Throat Infection', 6, 2);
INSERT INTO treatments (illness, drug_id, dailyquantity) VALUES ('Diabetes', 7, 1);
INSERT INTO treatments (illness, drug_id, dailyquantity) VALUES ('High Blood Pressure', 8, 2);
INSERT INTO treatments (illness, drug_id, dailyquantity) VALUES ('Cholesterol', 9, 1);
INSERT INTO treatments (illness, drug_id, dailyquantity) VALUES ('Stomach Ulcer', 10, 2);
INSERT INTO treatments (illness, drug_id, dailyquantity) VALUES ('Anxiety', 11, 3);
INSERT INTO treatments (illness, drug_id, dailyquantity) VALUES ('Seizures', 12, 2);
INSERT INTO treatments (illness, drug_id, dailyquantity) VALUES ('Fluid Retention', 13, 1);
INSERT INTO treatments (illness, drug_id, dailyquantity) VALUES ('Asthma', 14, 2);
INSERT INTO treatments (illness, drug_id, dailyquantity) VALUES ('Muscle Pain', 15, 1);
INSERT INTO treatments (illness, drug_id, dailyquantity) VALUES ('Skin Allergy', 16, 2);
INSERT INTO treatments (illness, drug_id, dailyquantity) VALUES ('Impotence', 17, 1);
INSERT INTO treatments (illness, drug_id, dailyquantity) VALUES ('Depression', 18, 2);
INSERT INTO treatments (illness, drug_id, dailyquantity) VALUES ('Sleep Disorder', 19, 1);
INSERT INTO treatments (illness, drug_id, dailyquantity) VALUES ('Back Pain', 20, 2);
INSERT INTO treatments (illness, drug_id, dailyquantity) VALUES ('Anxiety', 21, 3);
INSERT INTO treatments (illness, drug_id, dailyquantity) VALUES ('Insomnia', 22, 2);
INSERT INTO treatments (illness, drug_id, dailyquantity) VALUES ('Muscle Spasm', 23, 1);
INSERT INTO treatments (illness, drug_id, dailyquantity) VALUES ('Depression', 24, 2);
INSERT INTO treatments (illness, drug_id, dailyquantity) VALUES ('Anxiety', 25, 3);
INSERT INTO treatments (illness, drug_id, dailyquantity) VALUES ('Sleep Disorder', 26, 2);
INSERT INTO treatments (illness, drug_id, dailyquantity) VALUES ('High Blood Pressure', 27, 1);
INSERT INTO treatments (illness, drug_id, dailyquantity) VALUES ('Pain', 28, 2);
INSERT INTO treatments (illness, drug_id, dailyquantity) VALUES ('Anxiety', 29, 3);
INSERT INTO treatments (illness, drug_id, dailyquantity) VALUES ('Arthritis', 30, 2);

-- Insert data into pacient_treatment table
INSERT INTO pacient_treatment (pacient_id, treatment_id) VALUES (1, 1);
INSERT INTO pacient_treatment (pacient_id, treatment_id) VALUES (2, 2);
INSERT INTO pacient_treatment (pacient_id, treatment_id) VALUES (3, 3);
INSERT INTO pacient_treatment (pacient_id, treatment_id) VALUES (4, 4);
INSERT INTO pacient_treatment (pacient_id, treatment_id) VALUES (5, 5);
INSERT INTO pacient_treatment (pacient_id, treatment_id) VALUES (6, 6);
INSERT INTO pacient_treatment (pacient_id, treatment_id) VALUES (7, 7);
INSERT INTO pacient_treatment (pacient_id, treatment_id) VALUES (8, 8);
INSERT INTO pacient_treatment (pacient_id, treatment_id) VALUES (9, 9);
INSERT INTO pacient_treatment (pacient_id, treatment_id) VALUES (10, 10);
INSERT INTO pacient_treatment (pacient_id, treatment_id) VALUES (11, 11);
INSERT INTO pacient_treatment (pacient_id, treatment_id) VALUES (12, 12);
INSERT INTO pacient_treatment (pacient_id, treatment_id) VALUES (13, 13);
INSERT INTO pacient_treatment (pacient_id, treatment_id) VALUES (14, 14);
INSERT INTO pacient_treatment (pacient_id, treatment_id) VALUES (15, 15);
INSERT INTO pacient_treatment (pacient_id, treatment_id) VALUES (16, 16);
INSERT INTO pacient_treatment (pacient_id, treatment_id) VALUES (17, 17);
INSERT INTO pacient_treatment (pacient_id, treatment_id) VALUES (18, 18);
INSERT INTO pacient_treatment (pacient_id, treatment_id) VALUES (19, 19);
INSERT INTO pacient_treatment (pacient_id, treatment_id) VALUES (20, 20);
INSERT INTO pacient_treatment (pacient_id, treatment_id) VALUES (21, 21);
INSERT INTO pacient_treatment (pacient_id, treatment_id) VALUES (22, 22);
INSERT INTO pacient_treatment (pacient_id, treatment_id) VALUES (23, 23);
INSERT INTO pacient_treatment (pacient_id, treatment_id) VALUES (24, 24);
INSERT INTO pacient_treatment (pacient_id, treatment_id) VALUES (25, 25);
INSERT INTO pacient_treatment (pacient_id, treatment_id) VALUES (26, 26);
INSERT INTO pacient_treatment (pacient_id, treatment_id) VALUES (27, 27);
INSERT INTO pacient_treatment (pacient_id, treatment_id) VALUES (28, 28);
INSERT INTO pacient_treatment (pacient_id, treatment_id) VALUES (29, 29);
INSERT INTO pacient_treatment (pacient_id, treatment_id) VALUES (30, 30);

COMMIT;
