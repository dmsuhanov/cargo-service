--liquibase formatted sql

--changeset suhanov-du:20220211-01 failOnError:true
CREATE TABLE public.customer
(
    id uuid NOT NULL DEFAULT gen_random_uuid(),
    name character varying (50) NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public.customer
    OWNER to postgres;
