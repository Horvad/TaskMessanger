CREATE TABLE IF NOT EXISTS task_service.staff_cross
(
    id_project uuid NOT NULL,
    id_staff uuid NOT NULL,
    CONSTRAINT fk_project FOREIGN KEY (id_project)
        REFERENCES task_service.project (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_staff FOREIGN KEY (id_staff)
        REFERENCES task_service.staff (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)