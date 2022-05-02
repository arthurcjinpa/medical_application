ALTER TABLE public.users
    DROP COLUMN email;

DROP SEQUENCE IF EXISTS public.user_id_seq;
