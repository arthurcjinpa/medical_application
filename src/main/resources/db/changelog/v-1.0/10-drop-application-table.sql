ALTER TABLE IF EXISTS public.application
    OWNER to tura;

DROP TABLE IF EXISTS public.application;

ALTER SEQUENCE IF EXISTS public.application_id_seq RESTART WITH 1;