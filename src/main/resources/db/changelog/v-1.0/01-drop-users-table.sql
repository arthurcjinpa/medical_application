ALTER TABLE IF EXISTS public.users
    OWNER to tura;

DROP TABLE IF EXISTS public.users;

ALTER SEQUENCE IF EXISTS public.user_id_seq RESTART WITH 1;
