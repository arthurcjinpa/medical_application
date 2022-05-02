ALTER TABLE IF EXISTS public.application
    OWNER to tura;

DROP TABLE IF EXISTS public.users_application_history_ids;

DROP TABLE IF EXISTS public.application;

DROP SEQUENCE IF EXISTS public.application_id_seq;