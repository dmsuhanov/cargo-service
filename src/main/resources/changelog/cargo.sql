--liquibase formatted sql

--changeset suhanov-du:20220217-02 failOnError:true
CREATE TABLE public.cargo
(
    id uuid NOT NULL DEFAULT gen_random_uuid(),
    create_at timestamp with time zone NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public.cargo
    OWNER to postgres;

CREATE TABLE public.cargo_customer_role
(
    id uuid NOT NULL DEFAULT gen_random_uuid(),
    cargo_id uuid NOT NULL,
    customer_id uuid NOT NULL,
    role_id uuid NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT FK_CCR_CARGO FOREIGN KEY (cargo_id) REFERENCES public.cargo (id),
    CONSTRAINT FK_CCR_CUSTOMER FOREIGN KEY (customer_id) REFERENCES public.customer (id),
    CONSTRAINT FK_CCR_ROLE FOREIGN KEY (role_id) REFERENCES public.role (id)
);

ALTER TABLE IF EXISTS public.cargo_customer_role
    OWNER to postgres;

--changeset suhanov-du:20220218-01 failOnError:true
ALTER TABLE public.cargo ADD COLUMN goal_location_id uuid CONSTRAINT FK_CARGO_GOAL_LOCATION REFERENCES public.location (id);

