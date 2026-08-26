package com.kaiccesar.task_api.controller;


import com.kaiccesar.task_api.dto.TaskRequestDTO;
import com.kaiccesar.task_api.model.Task;
import com.kaiccesar.task_api.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> allTasks(){
        return taskService.allTasks();
    }

    @PostMapping
    public void createTask(@RequestBody TaskRequestDTO taskDto){
        taskService.create(taskDto);
    }
}
