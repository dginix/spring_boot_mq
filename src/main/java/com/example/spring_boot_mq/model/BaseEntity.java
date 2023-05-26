package com.example.spring_boot_mq.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "create_timestamp")
    protected LocalDateTime createTimestamp;

    @Column(name = "update_timestamp")
    protected LocalDateTime updateTimestamp;

    @PrePersist
    public void prePersist() {
        createTimestamp = LocalDateTime.now();
        updateTimestamp = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updateTimestamp = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BaseEntity that = (BaseEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
