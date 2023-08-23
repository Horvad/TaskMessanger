package by.itacademy.taskservice.repository.entity;

import by.itacademy.taskservice.core.enums.ProjectStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "project")
public class ProjectEntity {
    @Id
    @Column(name = "id")
    private UUID id;

    @CreationTimestamp
    @Column(name = "dt_create", precision = 3)
    private LocalDateTime dtCreate;

    @UpdateTimestamp
    @Version
    @Column(name = "dt_update", precision = 3)
    private LocalDateTime dtUpdate;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name="manger_id", updatable=false)
    private ManagerEntity manager;

    @ManyToMany
    @JoinTable(
            name = "staff_cross",
            joinColumns =
            @JoinColumn(name = "id_project", referencedColumnName = "ID"),
            inverseJoinColumns =
            @JoinColumn(name = "id_staff", referencedColumnName = "ID")
    )
    private List<StaffEntity> staff;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    public ProjectEntity() {
    }

    public ProjectEntity(UUID id,
                         LocalDateTime dtCreate,
                         LocalDateTime dtUpdate,
                         String name,
                         String description,
                         ManagerEntity manager,
                         List<StaffEntity> staff,
                         ProjectStatus status) {
        this.id = id;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.name = name;
        this.description = description;
        this.manager = manager;
        this.staff = staff;
        this.status = status;
    }

    public ProjectEntity(String name,
                         String description,
                         ProjectStatus status) {
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ManagerEntity getManager() {
        return manager;
    }

    public void setManager(ManagerEntity manager) {
        this.manager = manager;
    }

    public List<StaffEntity> getStaff() {
        return staff;
    }

    public void setStaff(List<StaffEntity> staff) {
        this.staff = staff;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ProjectEntity{" +
                "id=" + id +
                ", dtCreate=" + dtCreate +
                ", dtUpdate=" + dtUpdate +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", manager=" + manager +
                ", staff=" + staff +
                ", status=" + status +
                '}';
    }
}
