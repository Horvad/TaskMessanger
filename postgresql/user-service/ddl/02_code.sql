(
    mail character varying(255),
    code character varying(255),
    CONSTRAINT code_pkey PRIMARY KEY (mail)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS user_service.code
    OWNER to task_manager;