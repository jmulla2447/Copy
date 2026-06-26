package com.kamlesh.bhavcopy.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "jobq")
public class JobQueue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private UUID reqid;

    @Column(name = "addedat", nullable = false, updatable = false)
    private LocalDateTime addedat;

    private LocalDateTime startedat;
    private LocalDateTime endedat;
    private Duration duration;

    @Column(columnDefinition = "jsonb")
    private String params;

    @Column(columnDefinition = "jsonb")
    private String response;

    @Column(nullable = false)
    private String status;
}
