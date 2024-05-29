BEGIN TRANSACTION;

DROP TABLE IF EXISTS pacient_treatment;
DROP TABLE IF EXISTS treatments;
DROP TABLE IF EXISTS pacients;
DROP TABLE IF EXISTS history;
DROP TABLE IF EXISTS schedule;
DROP TABLE IF EXISTS drugs;
DROP FUNCTION IF EXISTS get_drug_statistics_and_prediction;

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

COMMIT;

-- Insert data into the tables
BEGIN TRANSACTION;

-- Insert data into drugs table
INSERT INTO drugs (name, quantity, fullstock) VALUES ('Aspirin', 100, 125);
INSERT INTO drugs (name, quantity, fullstock) VALUES ('Ibuprofen', 200, 400);
INSERT INTO drugs (name, quantity, fullstock) VALUES ('Paracetamol', 150, 600);

-- Insert data into schedule table
INSERT INTO schedule (drug_id, data, quantity) VALUES (1, '2024-05-01', 10);
INSERT INTO schedule (drug_id, data, quantity) VALUES (2, '2024-05-02', 20);
INSERT INTO schedule (drug_id, data, quantity) VALUES (3, '2024-05-03', 15);

-- Insert data into history table
INSERT INTO history (drug_id, data, quantity, transaction) VALUES (1, '2024-05-01', 10, 'Dispensed');
INSERT INTO history (drug_id, data, quantity, transaction) VALUES (2, '2024-05-02', 20, 'Received');
INSERT INTO history (drug_id, data, quantity, transaction) VALUES (3, '2024-05-03', 15, 'Dispensed');

-- Insert data into pacients table
INSERT INTO pacients (surname, firstname) VALUES ('Doe', 'John');
INSERT INTO pacients (surname, firstname) VALUES ('Smith', 'Jane');
INSERT INTO pacients (surname, firstname) VALUES ('Brown', 'Charlie');

-- Insert data into treatments table
INSERT INTO treatments (illness, drug_id, dailyquantity) VALUES ('Headache', 1, 2);
INSERT INTO treatments (illness, drug_id, dailyquantity) VALUES ('Inflammation', 2, 3);
INSERT INTO treatments (illness, drug_id, dailyquantity) VALUES ('Fever', 3, 1);

-- Insert data into pacient_treatment table
INSERT INTO pacient_treatment (pacient_id, treatment_id) VALUES (1, 1);
INSERT INTO pacient_treatment (pacient_id, treatment_id) VALUES (2, 2);
INSERT INTO pacient_treatment (pacient_id, treatment_id) VALUES (3, 3);

COMMIT;

-- Call the function to get statistics for all drugs
