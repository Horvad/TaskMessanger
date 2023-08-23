CREATE TABLE IF NOT EXISTS audit_service.audit
(
    id uuid NOT NULL,
    dt_create timestamp(6) without time zone,
    id_string character varying(255) COLLATE pg_catalog."default",
    role character varying(255) COLLATE pg_catalog."default",
    text character varying(255) COLLATE pg_catalog."default",
    user_entity_id uuid,
    CONSTRAINT audit_pkey PRIMARY KEY (id),
    CONSTRAINT userKey FOREIGN KEY (user_entity_id)
    REFERENCES audit_service."user" (id) MATCH SIMPLE
                           ON UPDATE NO ACTION
                           ON DELETE NO ACTION
    )

    TABLESPACE pg_default;