BEGIN TRANSACTION;

DROP TABLE IF EXISTS pacient_treatment;
DROP TABLE IF EXISTS treatments;
DROP TABLE IF EXISTS pacients;
DROP TABLE IF EXISTS history;
DROP TABLE IF EXISTS schedule;
DROP TABLE IF EXISTS drugs;


CREATE TABLE drugs (
   id SERIAL PRIMARY KEY,
   name TEXT NOT NULL,
   quantity INTEGER
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

COMMIT;

-- Insert data into the tables
BEGIN TRANSACTION;

-- Insert data into drugs table
INSERT INTO drugs (name, quantity) VALUES ('Aspirin', 100);
INSERT INTO drugs (name, quantity) VALUES ('Ibuprofen', 200);
INSERT INTO drugs (name, quantity) VALUES ('Paracetamol', 150);

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
