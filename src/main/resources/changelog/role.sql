--liquibase formatted sql

--changeset suhanov-du:20220217-01 failOnError:true
CREATE TABLE public.role
(
    id uuid NOT NULL DEFAULT gen_random_uuid(),
    name character varying (50) NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public.role
    OWNER to postgres;

INSERT INTO public.role (name) VALUES ('sender');
INSERT INTO public.role (name) VALUES ('recipient');
