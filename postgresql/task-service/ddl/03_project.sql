-- Table: task_service.project

-- DROP TABLE IF EXISTS task_service.project;

CREATE TABLE IF NOT EXISTS task_service.project
(
    id uuid NOT NULL,
    description character varying(255),
    dt_create timestamp(3) without time zone,
    dt_update timestamp(3) without time zone,
    name character varying(255),
    status character varying(255),
    manger_id uuid,
    CONSTRAINT project_pkey PRIMARY KEY (id),
    CONSTRAINT fk_manager FOREIGN KEY (manger_id)
        REFERENCES task_service.manager (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS task_service.project
    OWNER to task_manager;