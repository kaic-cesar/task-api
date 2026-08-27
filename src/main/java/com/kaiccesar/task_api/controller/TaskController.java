package com.kaiccesar.task_api.controller;


import com.kaiccesar.task_api.dto.TaskRequestDTO;
import com.kaiccesar.task_api.dto.TaskResponseDTO;
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

    @GetMapping("/{id}")
    public Task taskById(@PathVariable Long id){
        return taskService.taskById(id);
    }

    @PostMapping
    public void createTask(@RequestBody TaskRequestDTO taskDto){
        taskService.create(taskDto);
    }

    @PutMapping("/{id}")
    public Task update(@PathVariable Long id, @RequestBody TaskRequestDTO taskDto){
        return taskService.update(id, taskDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        taskService.delete(id);
    }

    @PatchMapping("/{id}")
    public String completedTask(@PathVariable Long id){
        return taskService.completedTask(id);
    }

}
