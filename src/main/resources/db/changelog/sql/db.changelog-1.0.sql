-- Liquibase formatted sql

-- changeset libra:001
-- comment: Create the uuid-ossp extension
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- changeset libra:002
-- comment: Create the t_vehicles table and its related dependencies
CREATE SEQUENCE t_vehicles_sequence
    INCREMENT BY 1
    START WITH 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE t_vehicles (
    id                          BIGINT                      PRIMARY KEY DEFAULT nextval('t_vehicles_sequence'),
    uuid                        uuid                        NOT NULL UNIQUE,
    make                        VARCHAR(50)                 NOT NULL,
    model                       VARCHAR(50)                 NOT NULL,
    registration_number         VARCHAR(255)                NOT NULL UNIQUE,
    mileage                     INTEGER                     NOT NULL,
    created_at                  TIMESTAMP WITH TIME ZONE    DEFAULT CURRENT_TIMESTAMP,
    last_updated_at             TIMESTAMP WITH TIME ZONE    DEFAULT CURRENT_TIMESTAMP,
    deleted_at                  TIMESTAMP WITH TIME ZONE    DEFAULT NULL
);

ALTER SEQUENCE t_vehicles_sequence OWNED BY t_vehicles.id;

COMMENT ON TABLE t_vehicles IS 'The table used to store the vehicles of the company.';

COMMENT ON COLUMN t_vehicles.id                  IS 'Primary surrogate key for internal database use.';
COMMENT ON COLUMN t_vehicles.uuid                IS 'Public unique identifier for external exposure.';
COMMENT ON COLUMN t_vehicles.make                IS 'Manufacturer of the vehicle (e.g., Toyota, Dacia, Mercedes).';
COMMENT ON COLUMN t_vehicles.model               IS 'Model of the vehicle (e.g., Corolla, Duster, S-Class).';
COMMENT ON COLUMN t_vehicles.registration_number IS 'Unique vehicle registration/license plate number.';
COMMENT ON COLUMN t_vehicles.mileage             IS 'Current vehicle mileage measured in kilometers.';
COMMENT ON COLUMN t_vehicles.created_at          IS 'Timestamp when the vehicle record was created.';
COMMENT ON COLUMN t_vehicles.last_updated_at     IS 'Timestamp of the last update to the vehicle record.';
COMMENT ON COLUMN t_vehicles.deleted_at          IS 'Timestamp indicating soft deletion. NULL means the record is active.';
