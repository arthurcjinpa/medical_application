ALTER TABLE IF EXISTS public.users
    OWNER to tura;

DROP TABLE IF EXISTS public.users_application_history_ids;

DROP TABLE IF EXISTS public.users;

DROP SEQUENCE IF EXISTS public.user_id_seq;
