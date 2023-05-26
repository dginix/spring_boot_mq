package com.example.spring_boot_mq.model;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "message")
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoredMessage extends BaseEntity {

    @Column(name = "body", nullable = false)
    private String body;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private MessageStatus status;
}
