CREATE TABLE IF NOT EXISTS audit_service."user"
(
    id uuid NOT NULL,
    fio character varying(255) COLLATE pg_catalog."default",
    mail character varying(255) COLLATE pg_catalog."default",
    role character varying(255) COLLATE pg_catalog."default",
    status character varying(255) COLLATE pg_catalog."default",
    id_string character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT user_pkey PRIMARY KEY (id)
)

ALTER TABLE IF EXISTS audit_service."user"
    OWNER to task_manager;