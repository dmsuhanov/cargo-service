--liquibase formatted sql

--changeset suhanov-du:20220218-01 failOnError:true
CREATE TABLE public.location
(
    id uuid NOT NULL DEFAULT gen_random_uuid(),
    name character varying (50) NOT NULL,
    description character varying (50) NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public.location
    OWNER to postgres;
