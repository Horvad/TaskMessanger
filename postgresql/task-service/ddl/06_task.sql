CREATE TABLE IF NOT EXISTS task_service.task
(
    id uuid NOT NULL,
    description character varying(255),
    dt_create timestamp(3) without time zone,
    dt_update timestamp(3) without time zone,
    status character varying(255),
    title character varying(255),
    implementer_id uuid,
    CONSTRAINT task_pkey PRIMARY KEY (id),
    CONSTRAINT fk_implementer FOREIGN KEY (implementer_id)
    REFERENCES task_service.implementer (id) MATCH SIMPLE
                           ON UPDATE NO ACTION
                           ON DELETE NO ACTION
    )