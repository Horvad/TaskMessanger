CREATE TABLE IF NOT EXISTS report_service.file_report
(
    id uuid NOT NULL,
    file_name character varying(255) COLLATE pg_catalog."default",
    status character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT file_report_entity_pkey PRIMARY KEY (id)
)