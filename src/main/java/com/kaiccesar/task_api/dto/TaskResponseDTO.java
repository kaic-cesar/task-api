package com.kaiccesar.task_api.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter

public class TaskResponseDTO {
    private String msg;
    private ZonedDateTime createAt;
}
