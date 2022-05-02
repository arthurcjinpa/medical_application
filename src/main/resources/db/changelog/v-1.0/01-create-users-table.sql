CREATE TABLE IF NOT EXISTS public.users
(
    id bigint NOT NULL,
    age integer,
    first_name character varying(255) COLLATE pg_catalog."default",
    last_name character varying(255) COLLATE pg_catalog."default",
    sex character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT users_pkey PRIMARY KEY (id)
);



ALTER TABLE IF EXISTS public.users
    OWNER to tura;