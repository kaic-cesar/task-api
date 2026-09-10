package com.kaiccesar.task_api.dto;


import com.kaiccesar.task_api.model.TaskStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class TaskResponseDTO {
    private Long id;
    private String title;
    private String description;
    private TaskStatus completed;
    private LocalDate createAt;
}
