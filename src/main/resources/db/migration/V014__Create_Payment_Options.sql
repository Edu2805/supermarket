CREATE TABLE IF NOT EXISTS payment_options (
   id UUID NOT NULL,
   name VARCHAR(50) NOT NULL,
   CONSTRAINT pk_payment_options PRIMARY KEY (id)
);

INSERT INTO payment_options (id, name) VALUES('20cb977b-a32e-4839-9782-eab11d1bd89f', 'MONEY');
INSERT INTO payment_options (id, name) VALUES('f46596ac-d82f-43cf-aa47-b917caf58009', 'CREDIT_CARD');
INSERT INTO payment_options (id, name) VALUES('49d27473-83ec-4058-b6ac-2a7c69bf0ef7', 'DEBIT_CARD');
INSERT INTO payment_options (id, name) VALUES('9df50c91-5f5e-486d-bfaf-e76ab8c6fa17', 'MEAL_TICKET');
INSERT INTO payment_options (id, name) VALUES('1218fdde-6cc6-490a-909e-5d06af4c992f', 'FOOD_VOUCHER');
INSERT INTO payment_options (id, name) VALUES('cdde12f9-1bf4-4e02-80cd-7270d40c285c', 'PIX');
INSERT INTO payment_options (id, name) VALUES('e48671c0-5690-4eb6-955d-038edd169213', 'OPENED');