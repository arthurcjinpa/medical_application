ALTER TABLE IF EXISTS public.application
    OWNER to myuser;

DROP TABLE IF EXISTS public.application;

ALTER SEQUENCE IF EXISTS public.application_id_seq RESTART WITH 1;