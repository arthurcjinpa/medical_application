CREATE TABLE IF NOT EXISTS public.application
(
    id bigint NOT NULL,
    context character varying(255) COLLATE pg_catalog."default",
    create_date date,
    status character varying(255) COLLATE pg_catalog."default",
    applicant_id bigint,
    CONSTRAINT application_pkey PRIMARY KEY (id),
    CONSTRAINT fk8toj00jihg6ykh6o5lgrh4myh FOREIGN KEY (applicant_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

ALTER TABLE IF EXISTS public.application
    OWNER to tura;