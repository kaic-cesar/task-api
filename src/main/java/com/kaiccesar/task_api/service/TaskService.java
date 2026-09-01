package com.kaiccesar.task_api.service;

import com.kaiccesar.task_api.dto.TaskRequestDTO;
import com.kaiccesar.task_api.dto.TaskResponseDTO;
import com.kaiccesar.task_api.exception.TaskNotFoundException;
import com.kaiccesar.task_api.model.Task;
import com.kaiccesar.task_api.model.TaskStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    List<Task> tasks = new ArrayList<>();
    private Long nextid = 1L;

    public List<Task> allTasks(){
        return tasks;
    }

    public Task taskById(Long id){
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new TaskNotFoundException("Get Error: Task not found"));
    }

    public List<Task> getTasks(@RequestParam Boolean completed){

        return tasks.stream()
                .filter(task -> task.getCompleted() == TaskStatus.COMPLETED)
                .toList();
    }

    public TaskResponseDTO create(TaskRequestDTO taskDto){
        Task task = new Task();

        task.setId(nextid++);
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setCompleted(TaskStatus.PENDING);
        task.setCreateAt(ZonedDateTime.now());

        boolean r = tasks.add(task);

        TaskResponseDTO taskResposeDto = new TaskResponseDTO();

        if(r){
            taskResposeDto.setMsg("Tarefa inserida");
            taskResposeDto.setCreateAt(task.getCreateAt());

            return taskResposeDto;
        }

        taskResposeDto.setMsg("Houve algum erro");
        return taskResposeDto;
    }


    public Task update(Long id, TaskRequestDTO newTask){
        for(Task task : tasks){
            if(task.getId().equals(id)){
                task.setTitle(newTask.getTitle());
                task.setDescription(newTask.getDescription());
            }
            return task;
        }

        return null;
    }

    public void delete(Long id){
        boolean response = tasks.removeIf(task -> task.getId().equals(id));

        if(!response){
            throw new TaskNotFoundException("Delete Error: Task not found");
        }

    }

    public String completedTask(Long id){
        for(Task task : tasks){
            if(task.getId().equals(id)){
                task.setCompleted(TaskStatus.COMPLETED);

                return "Tarefa concluida";
            }
        }
        throw new TaskNotFoundException("Completed Error: Task not found");
    }

}
