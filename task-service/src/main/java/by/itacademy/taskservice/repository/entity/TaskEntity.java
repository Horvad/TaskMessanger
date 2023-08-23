package by.itacademy.taskservice.repository.entity;

import by.itacademy.taskservice.core.enums.TaskStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "task")
public class TaskEntity {
    @Id
    @Column(name = "id")
    private UUID uuid;

    @Column(name = "dt_create", precision = 3)
    @CreationTimestamp
    private LocalDateTime dtCreate;

    @Column(name = "dt_update", precision = 3)
    @Version
    @UpdateTimestamp
    private LocalDateTime dtUpdate;

    @ManyToOne
    @JoinTable(
            name = "task_project_cross",
            joinColumns =
            @JoinColumn(name = "id_project", referencedColumnName = "ID"),
            inverseJoinColumns =
            @JoinColumn(name = "id_task", referencedColumnName = "ID")
    )
    private ProjectEntity project;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @ManyToOne
    @JoinColumn(name="implementer_id")
    private ImplementerEntity implementer;

    public TaskEntity() {
    }

    public TaskEntity(UUID uuid,
                      LocalDateTime dtCreate,
                      LocalDateTime dtUpdate,
                      ProjectEntity project,
                      String title,
                      String description,
                      TaskStatus status,
                      ImplementerEntity implementer) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.project = project;
        this.title = title;
        this.description = description;
        this.status = status;
        this.implementer = implementer;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
    }

    public LocalDateTime getDtUpdate() {
        return dtUpdate;
    }

    public void setDtUpdate(LocalDateTime dtUpdate) {
        this.dtUpdate = dtUpdate;
    }

    public ProjectEntity getProject() {
        return project;
    }

    public void setProject(ProjectEntity project) {
        this.project = project;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public ImplementerEntity getImplementer() {
        return implementer;
    }

    public void setImplementer(ImplementerEntity implementer) {
        this.implementer = implementer;
    }
}
