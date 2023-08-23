CREATE TABLE IF NOT EXISTS task_service.task_project_cross
(
    id_task uuid,
    id_project uuid NOT NULL,
    CONSTRAINT fk_project_task_cross FOREIGN KEY (id_project)
    REFERENCES task_service.task (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION,
    CONSTRAINT fk_tast_tast_cross FOREIGN KEY (id_task)
    REFERENCES task_service.project (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    )