CREATE TABLE IF NOT EXISTS report_service.report
(
    uuid uuid NOT NULL,
    date_from date,
    date_to date,
    description character varying(255) COLLATE pg_catalog."default",
    dt_create timestamp(6) without time zone,
    dt_update timestamp(6) without time zone,
    type character varying(255) COLLATE pg_catalog."default",
    uuid_create uuid,
    file_report_entity_id uuid,
    CONSTRAINT report_entity_pkey PRIMARY KEY (uuid),
    CONSTRAINT fk_file FOREIGN KEY (file_report_entity_id)
    REFERENCES report_service.file_report_entity (id) MATCH SIMPLE
                           ON UPDATE NO ACTION
                           ON DELETE NO ACTION
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS report_service.report_entity
    OWNER to task_manager;