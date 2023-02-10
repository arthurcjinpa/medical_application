ALTER TABLE IF EXISTS public.users
    OWNER to myuser;

DROP TABLE IF EXISTS public.users;

ALTER SEQUENCE IF EXISTS public.users_id_seq RESTART WITH 1;
