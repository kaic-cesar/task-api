package com.kaiccesar.task_api.service;

import com.kaiccesar.task_api.dto.TaskRequestDTO;
import com.kaiccesar.task_api.model.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    List<Task> tasks = new ArrayList<>();
    private Long nextid = 1L;

    public List<Task> allTasks(){
        return tasks;
    }

    public void create(TaskRequestDTO taskDto){
        Task task = new Task();

        task.setId(nextid);
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setCompleted(false);

        tasks.add(task);
    }
}
