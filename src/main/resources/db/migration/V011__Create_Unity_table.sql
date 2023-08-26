CREATE TABLE IF NOT EXISTS unity (
   id UUID NOT NULL,
   name VARCHAR(50) NOT NULL,
   CONSTRAINT pk_unity PRIMARY KEY (id)
);

INSERT INTO unity (id, name) VALUES('2658c930-0aab-436b-aca7-65d176e4e1af', 'UNITY');
INSERT INTO unity (id, name) VALUES('a7781b9a-1127-4676-8f45-b3929bc9ee67', 'LITERS');
INSERT INTO unity (id, name) VALUES('d8e3ad10-6c60-4f20-8411-2935b675b5b3', 'MILLILITERS');
INSERT INTO unity (id, name) VALUES('cec3be2c-651d-4faf-ac73-b1d2d898922a', 'MILLIGRAMS');
INSERT INTO unity (id, name) VALUES('d57adc6a-1c26-4086-a438-289dbb69bc1d', 'GRAMS');
INSERT INTO unity (id, name) VALUES('ea5c1a7c-b33d-4722-8d80-dd6ba8a4339d', 'KILOS');
INSERT INTO unity (id, name) VALUES('2768d137-fc63-47d0-93f6-bc42c188e65b', 'TONS');