CREATE TABLE public.customer
(
    id uuid NOT NULL DEFAULT gen_random_uuid(),
    name character varying (50) NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public.customer
    OWNER to postgres;

CREATE TABLE public.cargo
(
    id uuid NOT NULL DEFAULT gen_random_uuid(),
    PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.cargo
    OWNER to postgres;

ALTER TABLE IF EXISTS public.cargo
    ADD COLUMN create_at timestamp with time zone NOT NULL;

CREATE TABLE public.role
(
    id uuid NOT NULL DEFAULT gen_random_uuid(),
    name character varying (50) NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public.role
    OWNER to postgres;

CREATE TABLE public.cargo_customer_role
(
    id uuid NOT NULL DEFAULT gen_random_uuid(),
    cargo_id uuid NOT NULL,
    customer_id uuid NOT NULL,
    role_id uuid NOT NULL,
    PRIMARY KEY (id),
--ALTER TABLE IF EXISTS public.cargo_customer_role
--      RENAME CONSTRAINT "FK_CCR_CARGO" TO "FK_CCR_CARGO1";
--
--  ALTER TABLE IF EXISTS public.cargo_customer_role
--      RENAME CONSTRAINT "FK_CCR_CUSTOMER" TO "FK_CCR_CUSTOMER1";
--
--  ALTER TABLE IF EXISTS public.cargo_customer_role
--      RENAME CONSTRAINT "FK_CCR_ROLE" TO "FK_CCR_ROLE1";
);

ALTER TABLE IF EXISTS public.role
    OWNER to postgres;