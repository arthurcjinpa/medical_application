CREATE TABLE IF NOT EXISTS public.application
(
    id bigserial NOT NULL,
    context character varying(255),
    status character varying(255),
    symptoms character varying(255)[],
    applicant_id bigint,
    create_date date,
    CONSTRAINT application_pkey PRIMARY KEY (id),
    CONSTRAINT fk_users FOREIGN KEY (applicant_id)
        REFERENCES public.users (id)
);

ALTER TABLE IF EXISTS public.application
    OWNER to tura;