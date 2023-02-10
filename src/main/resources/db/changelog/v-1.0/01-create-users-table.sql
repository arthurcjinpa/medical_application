CREATE TABLE IF NOT EXISTS public.users
(
    id bigserial NOT NULL PRIMARY KEY,
    first_name character varying(255),
    last_name character varying(255),
    age integer,
    sex character varying(255)
);



ALTER TABLE IF EXISTS public.users
    OWNER to myuser;